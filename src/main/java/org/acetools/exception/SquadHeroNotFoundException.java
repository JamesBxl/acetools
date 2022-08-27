package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SquadHeroNotFoundException extends ResponseStatusException {
    public SquadHeroNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find squad hero with id: '" + id + "'.");
    }
}
