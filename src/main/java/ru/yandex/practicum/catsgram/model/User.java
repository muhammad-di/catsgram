package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;
}
