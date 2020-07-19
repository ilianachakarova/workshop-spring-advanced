package org.softuni.workshopspringadvanced.users.repository;

import org.softuni.workshopspringadvanced.users.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRole(String role);
}
