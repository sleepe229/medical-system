package org.example.hospitalmanagementsystem.repository.base;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadRepo<T> {
    @Transactional
    T findById(Integer id);
    @Transactional
    List<T> findAll();
}