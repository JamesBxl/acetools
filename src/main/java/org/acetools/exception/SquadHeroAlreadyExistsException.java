package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SquadHeroAlreadyExistsException extends ResponseStatusException {
    public SquadHeroAlreadyExistsException(int id) {
        super(HttpStatus.CONFLICT, "Squad hero with id: '" + id + "' already exists.");
    }
}
