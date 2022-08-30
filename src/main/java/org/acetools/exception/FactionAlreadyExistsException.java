package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FactionAlreadyExistsException extends ResponseStatusException {
    public FactionAlreadyExistsException(int id) {
        super(HttpStatus.CONFLICT, "Faction with id: '" + id + "' already exists.");
    }
}
