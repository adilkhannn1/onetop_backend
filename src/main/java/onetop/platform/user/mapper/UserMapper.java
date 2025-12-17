package onetop.platform.user.mapper;

import onetop.platform.user.dto.UserProfileDTO;
import onetop.platform.user.dto.UserDTO;
import onetop.platform.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserEntity toUserEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.email(),
                userDTO.name(),
                userDTO.age(),
                userDTO.city(),
                userDTO.password()
        );
    }
    public UserDTO toUserDTO(UserEntity userEntity){
        return new UserDTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getAge(),
                userEntity.getCity(),
                userEntity.getPassword()
        );
    }

    public List<UserDTO> toListUserDTO(List<UserEntity> userEntities){
        return userEntities.stream().map(userEntity -> new UserDTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getAge(),
                userEntity.getCity(),
                userEntity.getPassword()
        )
        ).toList();
    }

    public UserProfileDTO toUserProfileDTO(UserEntity userEntity){
        return new UserProfileDTO(
                userEntity.getName(),
                userEntity.getCity(),
                userEntity.getAge()
        );
    }

//    public List<UserEntity> toListUserEntity(List<UserDTO> userDTOS){
//        return userDTOS.stream().map(userDTO -> new UserEntity(
//                        userDTO.id(),
//                        userDTO.email(),
//                        userDTO.name(),
//                        userDTO.age(),
//                        userDTO.password()
//                )
//        ).toList();
//    }

}



