package org.softuni.workshopspringadvanced;

import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementEntity;
import org.softuni.workshopspringadvanced.annoucement.repository.AnnouncementRepository;
import org.softuni.workshopspringadvanced.users.model.RoleEntity;
import org.softuni.workshopspringadvanced.users.model.UserEntity;
import org.softuni.workshopspringadvanced.users.repository.RoleRepository;
import org.softuni.workshopspringadvanced.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class TabulaApplicationInit implements CommandLineRunner {
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public TabulaApplicationInit(AnnouncementRepository announcementRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count() == 0) {
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle("Hello, Softuni!");
            announcementEntity.setDescription("Welcome to the Srping advacned course!");
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
            announcementRepository.save(announcementEntity);
        }

        if (userRepository.count() == 0) {
            // admin
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@example.com");
            admin.setPasswordHash(passwordEncoder.encode("topsecret"));

            RoleEntity adminAdminRole = new RoleEntity();
            adminAdminRole.setRole("ROLE_ADMIN");

            RoleEntity adminUserRole = new RoleEntity();
            adminUserRole.setRole("ROLE_USER");

            admin.setRoles(List.of(adminAdminRole, adminUserRole));

            userRepository.save(admin);

            // user
            UserEntity user = new UserEntity();
            user.setEmail("user@example.com");
            user.setPasswordHash(passwordEncoder.encode("topsecret"));

            RoleEntity userUserRole = new RoleEntity();
            userUserRole.setRole("ROLE_USER");

            user.setRoles(List.of(userUserRole));

            userRepository.save(user);
        }
    }
}
