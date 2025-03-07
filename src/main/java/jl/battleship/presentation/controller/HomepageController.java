package jl.battleship.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    private static final String HOMEPAGE_URL = "/";

    private static final String HOMEPAGE_VIEW = "homepage";

    @GetMapping(HOMEPAGE_URL)
    public String render() {
        return HOMEPAGE_VIEW;
    }
}
