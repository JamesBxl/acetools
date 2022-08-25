package org.acetools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RarityNotFoundException extends ResponseStatusException {
    public RarityNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find rarity with id: '" + id + "'.");
    }
}
