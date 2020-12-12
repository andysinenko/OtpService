package ua.kiev.sinenko.otpservice.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.kiev.sinenko.otpservice.model.entity.AccessUserEntity;

public class CustomUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private AccessUserService accessUserService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        logger.debug("loadUserByUsername: " + login);
        UserDetails userDetails = null;

        AccessUserEntity accessUser = null;
        try {
            accessUser = accessUserService.findByLogin(login);
            logger.debug("accessUser: " + accessUser);

            if (accessUser != null) {
                boolean accountExpired = false;
                boolean accountDisabled = false;
                boolean accountCredentialsExpired = false;
                boolean accountLocked = false;

                String role = accessUser.getAccessGroupEntity().getName().toUpperCase();

                userDetails = User.withUsername(accessUser.getLogin())
                        .password(accessUser.getPassword())
                        .roles(role.toUpperCase())
                        .accountExpired(accountExpired)
                        .accountLocked(accountLocked)
                        .credentialsExpired(accountCredentialsExpired)
                        .disabled(accountDisabled)
                        .authorities(role, accessUser.getAccessGroupEntity().getName())
                        .build();
            }
        } catch (Throwable throwable) {
            logger.error("user not found: " + login);
            throw new UsernameNotFoundException("username " + login + " not found");
        }
        return userDetails;
    }

    public AccessUserService getAccessUserService() {
        return accessUserService;
    }

    @Autowired
    public void setAccessUserService(AccessUserService accessUserService) {
        this.accessUserService = accessUserService;
    }

}
