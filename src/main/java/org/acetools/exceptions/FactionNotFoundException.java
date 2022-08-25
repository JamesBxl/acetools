package org.acetools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FactionNotFoundException extends ResponseStatusException {
    public FactionNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Could not find faction with id: '" + id + "'.");
    }
}
