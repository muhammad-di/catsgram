package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> usersList = new HashSet<>();

    @GetMapping
    public Set<User> findAll() {
        log.debug("Get all users: {}", usersList.size());
        return usersList;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        validateEmail(user);
        usersList.add(user);

        log.debug("This users was saved: {}", user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        isEmailValid(user);
        usersList.add(user);

        return user;
    }

    private void isEmailTaken(User user) {
        if (usersList.contains(user)) {
            String message = String.format("A user with the email: %S already exists", user.getEmail());
            throw new UserAlreadyExistException(message);
        }
    }

    private void isEmailValid(User user) {
        if (!StringUtils.hasText(user.getEmail())) {
            String message = String.format("An email: %S is not valid", user.getEmail());
            throw new InvalidEmailException(message);
        }
    }

    private void validateEmail(User user) {
        isEmailValid(user);
        isEmailTaken(user);
    }
}
