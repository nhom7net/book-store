package spring_boot.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_boot.dto.UserDTO;
import spring_boot.dto.UserUpdate;
import spring_boot.entity.UserEntity;
import spring_boot.exception.AddException;
import spring_boot.exception.ErrorCode;
import spring_boot.mapper.UserMapper;
import spring_boot.repository.UserRepository;
import spring_boot.response.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

     UserRepository userRepository;
     UserMapper userMapper;

    public UserEntity createUser(UserDTO request){
        // usname da ton tai
        if(userRepository.existsByUserName(request.getUserName()))
            throw new AddException(ErrorCode.USER_EXISTED);


        UserEntity user = userMapper.toUser(request);

        // bcrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);

    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    // lay danh sach dua tren id
    public UserResponse getUser(long IdUser){
        return userMapper.toResponse(userRepository.findById(IdUser).
                orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(long userId , UserUpdate request){
        UserEntity user = userRepository.findById(userId). orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(long userId){
       userRepository.deleteById(userId);
    }
}
