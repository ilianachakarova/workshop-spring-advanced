package org.softuni.workshopspringadvanced.annoucement.web;

import org.softuni.workshopspringadvanced.annoucement.model.AnnouncementDTO;
import org.softuni.workshopspringadvanced.annoucement.service.AnnouncementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public String announcement(Model model){
        model.addAttribute("announcements",this.announcementService.findAll());
        return "announcement/announcements";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String newAnnouncement(Model model){

        model.addAttribute("formData",new AnnouncementDTO());

        return "redirect:/announcement/new";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("formData") AnnouncementDTO announcementDTO,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("formData",announcementDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.formData",bindingResult);
            return "announcement/new";
        }
        announcementService.createOrUpdateAnnouncement(announcementDTO);
        return "redirect:/announcements";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public String delete(@ModelAttribute(name="deleteId")Long deleteId){
        this.announcementService.deleteAnnouncement(deleteId);
        return "redirect:/announcements";
    }


}
