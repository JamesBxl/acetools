package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.User;
import org.acetools.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/user")
@RestController
public class UserController {
    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public User getUserInfo(OAuth2AuthenticationToken authentication) {
        String username = authentication.getPrincipal().getName();
        userRepository.deleteAll();
        User currentUser = userRepository.findUserByUsername(username);
        if (null == currentUser) {
            logger.debug(authentication.getDetails().getClass().toString());
            String authorizedClientRegistrationId = authentication.getAuthorizedClientRegistrationId();
            currentUser = new User();
            logger.debug(authentication.toString());
            currentUser.setUsername(username);
            currentUser.setAttributes(authentication.getPrincipal().getAttributes().toString());
            for (GrantedAuthority grantedAuthority : authentication.getPrincipal().getAuthorities()) {
                currentUser.addGrantedAuthority(grantedAuthority);
                logger.debug(grantedAuthority.toString());
            }
            userRepository.save(currentUser);
        }
        return currentUser;
    }
}
