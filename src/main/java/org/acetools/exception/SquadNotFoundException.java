package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SquadNotFoundException extends ResponseStatusException {
    public SquadNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find squad with id: '" + id + "'.");
    }
}
