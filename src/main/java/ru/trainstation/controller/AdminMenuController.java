package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/admin")
public class AdminMenuController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadingAdminPage() {
        return "admin/adminMenu";
    }
}
