package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.NewsDto;
import org.example.hospitalmanagementsystem.dto.NewsInputDto;
import org.springframework.data.domain.Page;

public interface NewsService {
    void createNews(NewsInputDto newsDto);
    Page<NewsDto> getAllNews(int page, int size);
}
