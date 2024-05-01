package com.ein.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    //    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{menuUri}")
    public String open(@PathVariable final String menuUri, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/" + menuUri;
    }

    //    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{menuUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String menuUri,
                                        @PathVariable final String pageUri,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }

        return "user/" + menuUri + "/" + pageUri;
    }


    //    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{firstUri}/{secondUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String firstUri,
                                        @PathVariable final String secondUri,
                                        @PathVariable final String pageUri,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";
        }

        return "user/" + firstUri + "/" + secondUri + "/" + pageUri;
    }

}
