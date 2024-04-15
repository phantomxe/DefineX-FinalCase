package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.dto.request.UserCreateRequest;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*;
 
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    /* @Autowired*/
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody UserCreateRequest request) { 
        return userService.save(request).getId();
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }
}
