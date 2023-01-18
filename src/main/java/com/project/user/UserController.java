package com.project.user;

import com.project.security.dto.SignupRequest;
import com.project.user.dto.UserDetailsImpl;
import com.project.user.dto.UserListDTO;
import com.project.UrlMapping;
import com.project.user.dto.UserMinimalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.UrlMapping.*;

@RestController
@RequestMapping(UrlMapping.API_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(USERS)
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PatchMapping(USER_EDIT)
    public UserMinimalDTO edit(@PathVariable Long id, @RequestBody SignupRequest user) {
        UserDetailsImpl usr= new UserDetailsImpl(id,user.getUsername(),user.getEmail(),user.getPassword(),null);
        userService.edit(id,usr);
        return null;
    }

    @DeleteMapping(USER_DEL)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
