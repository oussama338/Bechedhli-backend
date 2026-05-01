package app.bechedhli.solar.graphql;

import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.exceptions.UserNotFoundException;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphQLController {

    private final UserRepository userRepository;

    public UserGraphQLController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @QueryMapping
    public List<User> usersByRole(@Argument String role) {
        return userRepository.findByRoleEquals(User.Role.valueOf(role.toUpperCase()));
    }

    @MutationMapping
    public User createUser(@Argument String nom,
                          @Argument String prenom,
                          @Argument String username,
                          @Argument String password,
                          @Argument String email,
                          @Argument String telephone,
                          @Argument String role) {
        User user = User.builder()
                .nom(nom)
                .prenom(prenom)
                .username(username)
                .password(password)
                .email(email)
                .telephone(telephone)
                .role(User.Role.valueOf(role.toUpperCase()))
                .actif(true)
                .build();
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument Long id,
                          @Argument String nom,
                          @Argument String prenom,
                          @Argument String email,
                          @Argument String telephone) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (nom != null) existing.setNom(nom);
        if (prenom != null) existing.setPrenom(prenom);
        if (email != null) existing.setEmail(email);
        if (telephone != null) existing.setTelephone(telephone);

        return userRepository.save(existing);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return true;
    }
}