package org.softuni.workshopspringadvanced.annoucement.repository;

import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity,Long> {
}
