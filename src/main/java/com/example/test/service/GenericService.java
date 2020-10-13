package com.example.test.service;

import java.util.Iterator;
import java.util.Optional;

public interface GenericService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void deleteById(Long id);

    void delete(T t);
}
