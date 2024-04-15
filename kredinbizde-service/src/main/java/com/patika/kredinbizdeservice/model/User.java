package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate; 

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "phoneNumber", unique = false, nullable = true)
    private String phoneNumber;

    @Column(name = "is_active", unique = false, nullable = true)
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address", nullable = true, unique = false)
    private Address address;
}