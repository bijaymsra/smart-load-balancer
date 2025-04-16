package com.smartload.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardMessage() {
        return "📊 Welcome to the Dashboard!";
    }
}
