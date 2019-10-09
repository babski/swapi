package com.mbabski.swapi.exception;

import lombok.Getter;

@Getter
public class PlanetPhraseNotFoundException extends RuntimeException {
    public PlanetPhraseNotFoundException(String message) {
        super(message);
    }
}
