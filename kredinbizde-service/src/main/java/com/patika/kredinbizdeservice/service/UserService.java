package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.dto.request.UserCreateRequest;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Address;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.AddressRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final NotificationProducer notificationProducer;
    private final byte[] salt = new byte[]{15, 46, 28, 20, 124, 39, 127, 42, 36, 15, 21, 104, 103, 92, 19, 24};

    /*  every reboot of the application, the salt will be different, so hashes are wrong.
    @PostConstruct
    public void init() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
    } */

    public String hashPassword(String password) { 
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-512"); // Singleton Design Pattern
            mDigest.update(salt);
            byte[] result = mDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< result.length ;i++){
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed on user creation");
        } 
    }

    public User save(UserCreateRequest request) { 

        Address new_address = addressRepository.save(
            Address.builder()
                .addressTitle(request.getAddressTitle())
                .addressDescription(request.getAddressDescription())
                .province(request.getProvince())
                .build()
        );

        try {
            User new_user = userRepository.save(
                User.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .birthDate(request.getBirthDate())
                    .email(request.getEmail())
                    .password(hashPassword(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .address(new_address)
                    .isActive(true)
                    .build()
                    
            );
            notificationProducer.sendNotification(prepareNotificationDTO(NotificationType.EMAIL, new_user.getEmail()));

            return new_user;
        } catch (Exception e) {
            throw new KredinbizdeException(ExceptionMessages.USED_EMAIL);
        }  
    }

    private NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi.")
                .notificationType(notificationType)
                .email(email)
                .build();
    }


    public List<User> getAll() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.findAll();
    }


    public User getByEmail(String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));

        if (foundUser.isPresent()) {
            user = foundUser.get();
        }

       return user;
    }

    public User getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));
    }
}
