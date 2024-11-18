package ru.yandex.practicum.catsgram.model;

import lombok.Setter;

import java.time.Instant;

public class Post {

    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    @Setter
    private String description; // описание
    @Setter
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

}