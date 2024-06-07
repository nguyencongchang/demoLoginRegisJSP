package org.example.loginregister.controller;

import lombok.AllArgsConstructor;
import org.example.loginregister.dto.UserDto;
import org.example.loginregister.entity.User;
import org.example.loginregister.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
    private UserService userService;
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }
    @PostMapping("/login")
    public String Login(@ModelAttribute("userdto") UserDto userDto){
        if(!userService.checkUserbyEmail(userDto.getEmail())){
            return "redirect:/login?emailwrong";
        }
        User user = userService.getUserbyEmail(userDto.getEmail());
        if(user.getRole().equals("ADMIN")){
            return  "redirect:/admin_home";
        }
        if(userService.checkPasswordUser(userDto.getEmail(),userDto.getPassword())){
            return "redirect:/home?success";
        }

        return "redirect:/login?passwordwrong";
    }
}
