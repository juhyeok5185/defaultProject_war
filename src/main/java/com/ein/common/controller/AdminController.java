package com.ein.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{menuUri}")
    public String open(@PathVariable final String menuUri, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "admin/" + menuUri;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{menuUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String menuUri,
                                        @PathVariable final String pageUri,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }

        return "admin/" + menuUri + "/" + pageUri;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{firstUri}/{secondUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String firstUri,
                                        @PathVariable final String secondUri,
                                        @PathVariable final String pageUri,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }

        return "admin/" + firstUri + "/" + secondUri + "/" + pageUri;
    }

}
