package org.acetools.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HeroAlreadyExistsException extends ResponseStatusException {
    public HeroAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, "Hero with id: '" + id + "' already exists.");
    }
}
