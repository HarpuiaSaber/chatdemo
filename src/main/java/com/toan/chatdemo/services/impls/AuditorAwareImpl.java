package com.toan.chatdemo.services.impls;

import java.util.Optional;

import com.toan.chatdemo.entities.User;
import com.toan.chatdemo.models.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditorAwareImpl implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return Optional.of(new User(currentUser.getId()));
        }
        return Optional.ofNullable(null);
    }

}
