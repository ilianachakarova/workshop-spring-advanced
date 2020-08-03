package org.softuni.workshopspringadvanced.annoucement.service;

import org.modelmapper.ModelMapper;
import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementDTO;
import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementEntity;
import org.softuni.workshopspringadvanced.annoucement.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final ModelMapper modelMapper;

    public AnnouncementService(AnnouncementRepository announcementRepository, ModelMapper modelMapper) {
        this.announcementRepository = announcementRepository;
        this.modelMapper = modelMapper;
    }

    public List<AnnouncementDTO> findAll() {
        return this.announcementRepository.findAll().stream()
                .map(a->this.modelMapper.map(a,AnnouncementDTO.class))
                .collect(Collectors.toList());
    }

    public void cleanupOldAnnouncements(){
        Instant endTime = Instant.now().minus(7, ChronoUnit.DAYS);
        announcementRepository.deleteByUpdatedOnBefore(endTime);
    }

    public void createOrUpdateAnnouncement(AnnouncementDTO announcementDTO){
        AnnouncementEntity entity = this.modelMapper.map(announcementDTO,AnnouncementEntity.class);
        entity.setUpdatedOn(Instant.now());
        entity.setCreatedOn(Instant.now());
        announcementRepository.save(entity);
    }

    public void deleteAnnouncement(Long id){
        announcementRepository.deleteById(id);
    }
}
