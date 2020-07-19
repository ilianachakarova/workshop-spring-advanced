package org.softuni.workshopspringadvanced.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.workshopspringadvanced.users.model.UserEntity;
import org.softuni.workshopspringadvanced.users.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;


    public UserServiceDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceDetailsImpl.class);



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = userRepository.
                findOneByEmail(username);

        LOGGER.debug("Trying to load user {}. Successful? {}",
                username, userOpt.isPresent());

        return userOpt.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("No such user " + username));
    }

    private User map(UserEntity user) {
        List<GrantedAuthority> authorities = user.
                getRoles().
                stream().
                map(r -> new SimpleGrantedAuthority(r.getRole())).
                collect(Collectors.toList());

        User result = new User(
                user.getEmail(),
                user.getPasswordHash() != null ? user.getPasswordHash() : "",
                authorities);

        //todo - explain
        if (user.getPasswordHash() == null) {
            result.eraseCredentials();
        }

        return result;
    }
}
