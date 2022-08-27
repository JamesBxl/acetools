package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HeroNotFoundException extends ResponseStatusException {
    public HeroNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find hero with id: '" + id + "'.");
    }
}
