package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/{postId}")
    public Post findById(@PathVariable Integer postId) {
        return postService.findById(postId);
    }

    @GetMapping
    public Collection<Post> findAll() {
        log.debug("Получаем все посты в количестве (controller): {}", postService.findAll().size());
        return postService.findAll();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        log.debug("Создаем пост (controller): {}", post);
        return postService.create(post);

    }
}