package com.devtyagi.maalgaadi.exception;

import org.springframework.http.HttpStatus;

public class DealerNotFoundException extends BaseException{

    public DealerNotFoundException() {
        super("Dealer not found!", HttpStatus.NOT_FOUND);
    }

}
