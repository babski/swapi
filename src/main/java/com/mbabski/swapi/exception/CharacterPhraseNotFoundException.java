package com.mbabski.swapi.exception;

import lombok.Getter;

@Getter
public class CharacterPhraseNotFoundException extends RuntimeException {
    public CharacterPhraseNotFoundException(String message) {
        super(message);
    }
}
