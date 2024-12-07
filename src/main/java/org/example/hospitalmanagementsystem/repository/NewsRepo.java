package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.entities.News;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsRepo extends BaseCRUDRepo<News, Integer> {
    Page<News> findByTitle(String title, Pageable pageable);
}
