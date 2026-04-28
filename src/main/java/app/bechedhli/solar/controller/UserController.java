package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.exceptions.UserNotFoundException;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNom(newUser.getNom());
                    user.setPrenom(newUser.getPrenom());
                    user.setEmail(newUser.getEmail());
                    user.setTelephone(newUser.getTelephone());
                    user.setUsername(newUser.getUsername());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/userByUsername")
    public User getUserByUsername(@RequestParam("username") String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException((long) 0));
    }

    @GetMapping("/userByEmail")
    public User getUserByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException((long) 0));
    }
}