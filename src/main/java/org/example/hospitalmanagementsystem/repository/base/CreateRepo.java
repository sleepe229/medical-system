package org.example.hospitalmanagementsystem.repository.base;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateRepo<T> {
    @Transactional
    void save(T t);
}
