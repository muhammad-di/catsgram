package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.PostOrders;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.constraints.Min;
import java.util.Collection;

@Slf4j
@Validated
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
    public Collection<Post> findAll(@RequestParam(defaultValue = "desc") PostOrders sort,
                                    @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                    @RequestParam(defaultValue = "10") @Min(1) Integer size) {
        log.debug("Получаем все посты в количестве (controller)");
        return postService.findAll(sort, page, size);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        log.debug("Создаем пост (controller): {}", post);
        return postService.create(post);

    }
}