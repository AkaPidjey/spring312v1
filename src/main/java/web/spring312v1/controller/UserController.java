package web.spring312v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.spring312v1.model.Role;
import web.spring312v1.model.User;
import web.spring312v1.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
//    @Autowired
//    private UserService userService;

//    @GetMapping(value = {"/", "/login"})
//    public String login() {
//        return "loginBS";
//    }

//    @GetMapping("/new_user")
//    public String newUser() {
//        return "new_user";
//    }
//
//    @PostMapping("/new_user")
//    public String createNewUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
//        user.setPasswordReal(user.getPassword());
//        Set<Role> roleSet = new HashSet<>();
//        if (role.equals("ROLE_ADMIN")){
//            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        } else {
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        }
//        user.setRoles(roleSet);
//        userService.createNewUser(user);
//        return "redirect:/new_user";
//    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userBS");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
