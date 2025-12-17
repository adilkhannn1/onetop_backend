package onetop.platform.user.service;

import onetop.platform.user.dto.UserPasswordDTO;
import onetop.platform.user.dto.UserProfileDTO;
import onetop.platform.user.dto.UserDTO;
import onetop.platform.user.mapper.UserMapper;
import onetop.platform.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        var entity = userMapper.toUserEntity(userDTO);
        userRepository.save(entity);
        return userMapper.toUserDTO(entity);
    }

    public List<UserDTO> getUsers(){
        var usersEntity = userRepository.findAll();
        return userMapper.toListUserDTO(usersEntity);
    }

    public UserDTO getUser(Long id){
        var entity = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        return userMapper.toUserDTO(entity);
    }
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO){
        var entity = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        entity.setAge(userProfileDTO.age());
        entity.setName(userProfileDTO.name());
        entity.setCity(userProfileDTO.city());
        userRepository.save(entity);
        return userMapper.toUserProfileDTO(entity);
    }

    public String updateUserPassword(Long id, UserPasswordDTO userPasswordDTO){
        var entity = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        if(!entity.getPassword().equals(userPasswordDTO.oldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect");
        }
        entity.setPassword(userPasswordDTO.newPassword());
        return "Your password is succesully changed";
    }



}
