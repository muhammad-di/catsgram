package ru.yandex.practicum.catsgram.config;

import org.springframework.core.convert.converter.Converter;
import ru.yandex.practicum.catsgram.exception.CustomEnumConversionException;
import ru.yandex.practicum.catsgram.model.PostOrders;

public class StringToEnumConverter implements Converter<String, PostOrders> {
    @Override
    public PostOrders convert(String source) {
        try {
            return PostOrders.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomEnumConversionException("sort request query parameter can only be 'desc' or 'asc'");
        }
    }
}
