package ru.yandex.practicum.catsgram.exception;

import lombok.Getter;

@Getter
public class CustomEnumConversionException extends RuntimeException {

    public CustomEnumConversionException(final String message) {
        super(message);
    }
}