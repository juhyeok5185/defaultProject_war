package com.ein.common.util;

import com.ein.common.security.MyUserDetails;
import org.springframework.security.core.Authentication;

public class Utils {

    public static Long getMemberId(Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getId();
    }

}
