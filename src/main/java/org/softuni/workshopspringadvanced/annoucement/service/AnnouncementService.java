package org.softuni.workshopspringadvanced.annoucement.service;

import org.modelmapper.ModelMapper;
import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementDTO;
import org.softuni.workshopspringadvanced.annoucement.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

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
}
