//package com.ein.common.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@RequiredArgsConstructor
//public class PageController {
//
//    @PreAuthorize("isAnonymous()")
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/join")
//    public String join() {
//        return "join";
//    }
//
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/dashboard")
//    public String dashboard() {
//        return "dashboard";
//    }
//
////    @GetMapping("/{menuUri}")
////    public String open(@PathVariable final String menuUri) {
////        return  menuUri;
////    }
//
////    @GetMapping("/{menuUri}/{pageUri}")
////    public String openManagerPageByMenu(@PathVariable final String menuUri,
////                                        @PathVariable final String pageUri,
////                                        @RequestParam(required = false) final String pageType) {
////
////        return StringUtils.isBlank(pageType)
////                ? menuUri + "/" + pageUri
////                : menuUri + "/" + pageUri + "/" + pageType;
////    }
////
////    @GetMapping("/{firstUri}/{secondUri}/{pageUri}")
////    public String openManagerPageByMenu(@PathVariable final String firstUri,
////                                        @PathVariable final String secondUri,
////                                        @PathVariable final String pageUri,
////                                        @RequestParam(required = false) final String pageType) {
////
////        return firstUri + "/" + secondUri + "/" + pageUri;
////    }
//
//}
