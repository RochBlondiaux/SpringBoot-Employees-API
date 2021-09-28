package com.roch.employees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(String message) {
        super(message);
    }
}
