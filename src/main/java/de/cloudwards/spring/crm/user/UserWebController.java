package de.cloudwards.spring.crm.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWebController {

    /**
     * show index
     *
     * @return String
     */
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * show login
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
