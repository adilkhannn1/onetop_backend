package onetop.platform.user.controller;

import onetop.platform.user.dto.UserPasswordDTO;
import onetop.platform.user.dto.UserProfileDTO;
import onetop.platform.user.dto.UserDTO;
import onetop.platform.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        var user = userService.createUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        var user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO){
        var userProfile = userService.updateUserProfile(id, userProfileDTO);
        return ResponseEntity.ok(userProfile);
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<String> updateUserPassword(
            @PathVariable Long id,
            @RequestBody UserPasswordDTO userPasswordDTO
    ){
        var userPassword = userService.updateUserPassword(id, userPasswordDTO);
        return ResponseEntity.ok(userPassword);
    }
    
}
