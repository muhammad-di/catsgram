package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final UserService userService;

    public Post findById(Integer postId) {
        Optional<Post> optional = Optional.ofNullable(posts.get(postId));
        return optional
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост с id %s не найден", postId)));
    }

    public Collection<Post> findAll() {
        log.debug("Получаем все посты в количестве (service): {}", posts.size());
        return posts.values();
    }

    public Post create(Post post) {
        userService.findUserByEmail(post.getAuthor());
        post.setId(Post.generateId());
        posts.put(post.getId(), post);
        log.debug("Создаем пост (service): {}", post);

        return post;
    }


}
