package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FactionNotFoundException extends ResponseStatusException {
    public FactionNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find faction with id: '" + id + "'.");
    }
}
