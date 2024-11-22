package org.example.hospitalmanagementsystem.repository.base;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRepo<T> {
    @Transactional
    void update(T t);
}
