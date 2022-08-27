package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GearSetNotFoundException extends ResponseStatusException {
    public GearSetNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find gear set with id: '" + id + "'.");
    }
}
