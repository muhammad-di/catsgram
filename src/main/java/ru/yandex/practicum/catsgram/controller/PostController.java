package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PostController {
    //    private final static Logger log = LoggerFactory.getLogger(PostController.class);
    private final List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> findAll() {
        log.info("Получаем все посты в количестве: {}", posts.size());
        return posts;
    }

    @PostMapping("/post")
    public void create(@RequestBody Post post) {
        posts.add(post);
        log.debug("Создаем пост: {}", post);
    }
}