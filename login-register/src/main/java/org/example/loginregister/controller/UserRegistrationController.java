package org.example.loginregister.controller;

import lombok.AllArgsConstructor;
import org.example.loginregister.dto.UserDto;
import org.example.loginregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userResgistrationDto(){
        return new UserDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(){
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userdto") UserDto userDto){
        if(userService.checkUserbyEmail(userDto.getEmail())){
            return "redirect:/registration?emailexist";
        }
        if(!userDto.getPassword().equals(userDto.getCheckPass())){
            return "redirect:/registration?checkpass";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }
}
