package org.acetools.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HeroNotFoundException extends ResponseStatusException {
    public HeroNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Could not find hero with id: '" + id + "'.");
    }
}
