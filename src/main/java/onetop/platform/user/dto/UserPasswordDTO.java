package onetop.platform.user.dto;

public record UserPasswordDTO(
        String oldPassword,
        String newPassword
) {
}
