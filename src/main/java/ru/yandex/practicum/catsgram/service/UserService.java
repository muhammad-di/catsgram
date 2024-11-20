package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final Set<User> usersList = new HashSet<>();

    public Set<User> findAll() {
        log.debug("Get all users (service): {}", usersList.size());
        return usersList;
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = usersList.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        return optionalUser
                .orElseThrow(() -> new UserNotFoundException(String.format("Пользователь %s не найден", email)));
    }

    public User create(User user) {
        validateEmail(user);
        usersList.add(user);

        log.debug("This users was saved (service): {}", user);
        return user;
    }

    public User update(User user) {
        isEmailValid(user);
        usersList.add(user);

        log.debug("This users was updated (service): {}", user);
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
