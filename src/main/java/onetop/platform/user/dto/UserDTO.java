package onetop.platform.user.dto;

public record UserDTO(
         Long id,
         String email,
         String name,
         Integer age,
         String city,
         String password
) {

}
