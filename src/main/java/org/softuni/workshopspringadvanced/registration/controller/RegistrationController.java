package org.softuni.workshopspringadvanced.registration.controller;

import org.softuni.workshopspringadvanced.registration.model.RegistrationDTO;
import org.softuni.workshopspringadvanced.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegister(Model model){
        model.addAttribute("formData",new RegistrationDTO());
        return "registration/registration";
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("formData") RegistrationDTO registrationDTO,
                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "registration/registration";
        }
        //check if user exists
        if(this.userService.existsUser(registrationDTO.getEmail())){
            bindingResult.rejectValue("email","error.email","An account with this email exists");
        }
        this.userService.createAndLoginUser(registrationDTO.getEmail(),registrationDTO.getPassword());
        return "redirect:/home";
    }


}
