package web.spring312v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.spring312v1.service.UserService;

@Controller
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "loginBS";
    }

}
