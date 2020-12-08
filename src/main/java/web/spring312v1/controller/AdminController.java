package web.spring312v1.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.spring312v1.model.Role;
import web.spring312v1.model.User;
import web.spring312v1.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Data
@Controller
//@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ModelAndView getAllUsers() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.addObject(user);
        return modelAndView;
    }

//    @GetMapping("/all_users")
//    public ModelAndView getAllUsers() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("all_users");
//        modelAndView.addObject("users", userService.getAllUsers());
//        return modelAndView;
//    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "redirect:/admin";
    }

//    @GetMapping("/{id}")
//    public String getUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id).get());
//        return "edit_page";
//    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        Set<Role> roleSet = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        } else {
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        }
        user.setRoles(roleSet);
        userService.editUser(user);
        return "redirect:/admin";
    }

//    @PostMapping("/{id}")
//    public String editUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
//        Set<Role> roleSet = new HashSet<>();
//        if (role.equals("ROLE_ADMIN")){
//            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        } else {
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        }
//        user.setRoles(roleSet);
//        userService.editUser(user);
//        return "edit_page";
//    }


//    @GetMapping("/delete/{id}")
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

//    @GetMapping("/all_users/remove_user/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/admin/all_users";
//    }

    @GetMapping("/create")
    public String newUser() {
        return "redirect:/admin";
    }

//    @GetMapping("/new_user")
//    public String newUser() {
//        return "new_user";
//    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute("user") User user, @RequestParam("newRoles") String role) {
        user.setPasswordReal(user.getPassword());
        Set<Role> roleSet = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        } else {
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        }
        user.setRoles(roleSet);
        userService.createNewUser(user);
        return "redirect:/admin";
    }

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

}
