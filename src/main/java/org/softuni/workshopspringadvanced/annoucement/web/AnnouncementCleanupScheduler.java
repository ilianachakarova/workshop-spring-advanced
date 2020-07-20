package org.softuni.workshopspringadvanced.annoucement.web;

import org.softuni.workshopspringadvanced.annoucement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementCleanupScheduler {

    private final AnnouncementService announcementService;
    @Autowired
    public AnnouncementCleanupScheduler(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    //cleans up old announcements
    @Scheduled(cron = "0 0 2 ? * SUN")
    public void cleanupAnnouncements(){
        announcementService.cleanupOldAnnouncements();
    }
}
