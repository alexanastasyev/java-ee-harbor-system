package ru.rsreu.harbor.datalayer.util;

import java.util.List;
import java.util.Optional;

public class OptionalCreator {
    public static <T> Optional<T> createOptionalObjectFromList(List<T> userList) {
        if (userList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userList.get(0));
    }
}
