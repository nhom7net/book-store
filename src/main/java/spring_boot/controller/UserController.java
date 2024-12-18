package spring_boot.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring_boot.dto.ApiResponse;
import spring_boot.dto.UserDTO;
import spring_boot.dto.UserUpdate;
import spring_boot.entity.UserEntity;
import spring_boot.response.UserResponse;
import spring_boot.service.impl.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiResponse<UserEntity> createUser(@RequestBody @Valid UserDTO request) {
        ApiResponse<UserEntity> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }
    // LẤY DANH SÁCH USER
    @GetMapping
    List<UserEntity>getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") long userId, @RequestBody UserUpdate request) {
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
        String deleteUser(@PathVariable long userId){
         userService.deleteUser(userId);
        return "deleted susscessfully";

    }


}
