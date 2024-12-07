package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.NewsDto;
import org.example.hospitalmanagementsystem.dto.NewsEditDto;
import org.example.hospitalmanagementsystem.dto.NewsInputDto;
import org.springframework.data.domain.Page;

public interface NewsService {
    void createNews(NewsInputDto newsDto);
    void updateNews(NewsEditDto newsDto);
    NewsDto getNewsById(Integer id);
    Page<NewsDto> getNews(int page, int size);
    Page<NewsDto> getNews(String searchTerm, int page, int size);
}
