package org.softuni.workshopspringadvanced.annoucement.model;

import org.mapstruct.factory.Mappers;

//@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    AnnouncementEntity mapAnnouncementDTOtoEntity(AnnouncementDTO dto);
    AnnouncementDTO mapAnnouncementEntityToDto(AnnouncementEntity entity);
}
