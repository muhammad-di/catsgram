package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Post {
    private final String author; // автор
    private final Instant creationDate; // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(final String author, String description, String photoUrl) {
        this.author = author;
        this.creationDate = Instant.now();
        this.description = description;
        this.photoUrl = photoUrl;
    }
}