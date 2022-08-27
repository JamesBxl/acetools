package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ElementNotFoundException extends ResponseStatusException {
    public ElementNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find element with id: '" + id + "'.");
    }
}
