package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BattleNotFoundException extends ResponseStatusException {
    public BattleNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Could not find battle with id: '" + id + "'.");
    }
}
