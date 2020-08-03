package org.softuni.workshopspringadvanced.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.workshopspringadvanced.users.model.RoleEntity;
import org.softuni.workshopspringadvanced.users.model.UserEntity;
import org.softuni.workshopspringadvanced.users.repository.RoleRepository;
import org.softuni.workshopspringadvanced.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
@Autowired
    public UserService(UserRepository userRepository, UserDetailsService userDetailsService, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    this.userDetailsService = userDetailsService;

    this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsUser(String email){
    return userRepository.findOneByEmail(email).isPresent();
    }

    public UserEntity getOrCreateUser(String email){
       Optional<UserEntity>userEntityOptional = userRepository.findOneByEmail(email);
       return userEntityOptional.orElseGet(()->createUser(email));

    }

    public void createAndLoginUser(String userEmail, String userPassword){
    UserEntity newUser = this.createUser(userEmail,userPassword);
    //todo
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(newUser.getEmail());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserEntity createUser(String userEmail, String userPassword) {
        LOGGER.info("Creating a new user with email [PROTECTED].");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEmail);
        if (userPassword != null) {
            userEntity.setPasswordHash(passwordEncoder.encode(userPassword));
        }

        RoleEntity userRole = new RoleEntity("ROLE_USER");
        userEntity.setRoles(List.of(userRole));

        return userRepository.save(userEntity);
    }

    private UserEntity createUser(String email) {
        return this.createUser(email, null);
    }
}
