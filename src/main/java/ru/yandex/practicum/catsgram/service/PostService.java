package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        log.debug("Получаем все посты в количестве (service): {}", posts.size());
        return posts;
    }

    public void create(Post post) {
        posts.add(post);
        log.debug("Создаем пост (service): {}", post);
    }
}
