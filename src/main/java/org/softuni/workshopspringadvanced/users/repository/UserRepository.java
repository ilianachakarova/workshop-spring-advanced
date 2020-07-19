package org.softuni.workshopspringadvanced.users.repository;

import org.softuni.workshopspringadvanced.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findOneByEmail(String username);
}
