package com.ein.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PublicController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/{menuUri}")
    public String open(@PathVariable final String menuUri) {
        return menuUri;
    }

    @GetMapping("/{menuUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String menuUri,
                                        @PathVariable final String pageUri) {

        return menuUri + "/" + pageUri;
    }

    @GetMapping("/{firstUri}/{secondUri}/{pageUri}")
    public String openManagerPageByMenu(@PathVariable final String firstUri,
                                        @PathVariable final String secondUri,
                                        @PathVariable final String pageUri) {

        return firstUri + "/" + secondUri + "/" + pageUri;
    }

}
