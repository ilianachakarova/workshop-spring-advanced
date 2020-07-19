package org.softuni.workshopspringadvanced.annoucement.web;

import org.softuni.workshopspringadvanced.annoucement.service.AnnouncementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }
    //todo:role user
    @GetMapping()
    public String announcement(Model model){
        model.addAttribute("announcements",this.announcementService.findAll());
        return "announcement/announcements";
    }

}
