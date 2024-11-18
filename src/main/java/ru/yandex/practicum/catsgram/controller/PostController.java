package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> findAll() {
        log.debug("Получаем все посты в количестве (controller): {}", postService.findAll().size());
        return postService.findAll();
    }

    @PostMapping("/post")
    public void create(@RequestBody Post post) {
        postService.findAll().add(post);
        log.debug("Создаем пост (controller): {}", post);
    }
}