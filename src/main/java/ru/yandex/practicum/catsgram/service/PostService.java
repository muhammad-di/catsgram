package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.PostOrders;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    public Collection<Post> findAll(PostOrders sort, int page, int size) {
        log.debug("Получаем все посты в количестве (service): {}", posts.size());
        int from = page * size;

        int numberOfPages = posts.size() / size;
        Collection<Post> potsList = posts.values();
        return potsList.stream()
                .sorted(
                        (p1, p2) -> {
                            int comp = p1.getCreationDate().compareTo(p2.getCreationDate());
                            if (sort.equals(PostOrders.DESC)) {
                                return -1 * comp;
                            }
                            return comp;
                        }
                )
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        userService.findUserByEmail(post.getAuthor());
        post.setId(Post.generateId());
        posts.put(post.getId(), post);
        log.debug("Создаем пост (service): {}", post);

        return post;
    }


}
