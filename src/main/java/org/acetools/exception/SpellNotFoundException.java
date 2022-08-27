package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SpellNotFoundException extends ResponseStatusException {
    public SpellNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find spell with id: '" + id + "'.");
    }
}
