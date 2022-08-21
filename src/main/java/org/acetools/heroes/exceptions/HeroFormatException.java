package org.acetools.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HeroFormatException extends ResponseStatusException {
    public HeroFormatException() {
        super(HttpStatus.BAD_REQUEST, "Hero format invalid.");
    }
}
