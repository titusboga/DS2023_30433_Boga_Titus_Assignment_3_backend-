package com.project.user;

import com.project.user.dto.UserDetailsImpl;
import com.project.user.dto.UserListDTO;
import com.project.user.dto.UserMinimalDTO;
import com.project.user.mapper.UserMapper;
import com.project.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public void edit(Long id, UserDetailsImpl userDetails) {
        System.out.println(userDetails.getRoles());
        User user = findById(id);
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPassword(encoder.encode(userDetails.getPassword()));
        //(userDetails.getAuthorities());
        userRepository.save(user);

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
