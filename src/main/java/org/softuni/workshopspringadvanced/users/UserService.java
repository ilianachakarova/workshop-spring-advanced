package org.softuni.workshopspringadvanced.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.workshopspringadvanced.users.model.RoleEntity;
import org.softuni.workshopspringadvanced.users.model.UserEntity;
import org.softuni.workshopspringadvanced.users.repository.RoleRepository;
import org.softuni.workshopspringadvanced.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
@Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getOrCreateUser(String email){
       Optional<UserEntity>userEntityOptional = userRepository.findOneByEmail(email);
       return userEntityOptional.orElseGet(()->createUser(email));

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
