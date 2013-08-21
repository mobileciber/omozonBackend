package com.ciber.adm.poc.hybridmobile.util;

import com.ciber.adm.poc.hybridmobile.domain.HybridmobileUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    public HybridmobileUserDetails getCurrentUser() {
        HybridmobileUserDetails user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            user = (HybridmobileUserDetails) authentication.getPrincipal();
        }
        return user;
    }

}
