package org.softuni.workshopspringadvanced.stats;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String stats(Model model){
        model.addAttribute("requestCount",statsService.getRequestCount());
        model.addAttribute("startedOn",statsService.getStartedOn());
        return "stats/stats";
    }
}
