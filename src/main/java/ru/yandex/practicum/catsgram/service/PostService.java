package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    public List<Post> findAll() {
        log.debug("Получаем все посты в количестве (service): {}", posts.size());
        return posts;
    }

    public Post create(Post post) {
        userService.findUserByEmail(post.getAuthor());
        posts.add(post);
        log.debug("Создаем пост (service): {}", post);

        return post;
    }
}
