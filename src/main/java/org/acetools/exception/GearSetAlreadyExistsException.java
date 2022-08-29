package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GearSetAlreadyExistsException extends ResponseStatusException {
    public GearSetAlreadyExistsException(int id) {
        super(HttpStatus.CONFLICT, "Gear set with id: '" + id + "' already exists.");
    }
}
