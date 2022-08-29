package org.acetools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BattleAlreadyExistsException extends ResponseStatusException {
    public BattleAlreadyExistsException(int id) {
        super(HttpStatus.CONFLICT, "Battle with id: '" + id + "' already exists.");
    }
}
