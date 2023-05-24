package com.journal.repository;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(long id);

    List<T> findAll();

    T update(T t);

    T create(T t);

    int delete(T t);
}
