package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.*;
import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.entities.News;
import org.example.hospitalmanagementsystem.entities.Offer;
import org.example.hospitalmanagementsystem.repository.NewsRepo;
import org.example.hospitalmanagementsystem.services.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsRepo newsRepo, ModelMapper modelMapper) {
        this.newsRepo = newsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createNews(NewsInputDto newsInputDto) {
//        News news = this.modelMapper.map(newsInputDto, News.class);
        News news = new News(newsInputDto.getTitle(), newsInputDto.getDescription());
        try {
            this.newsRepo.save(news);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании новости", e);
        }
    }

    @Override
    public void updateNews(NewsEditDto newsEditDto) {
        News updatedNews = newsRepo.findById(newsEditDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Offer with ID " + newsEditDto.getId() + " not found"));
        newsRepo.save(updatedNews);
    }

    @Override
    public NewsDto getNewsById(Integer id) {
        News news = newsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Новость с ID " + id + " не найдена"));
        return modelMapper.map(news, NewsDto.class);
    }


    @Override
    public Page<NewsDto> getNews(int page, int size) {
        if (page < 1) {
            throw new IllegalArgumentException("Номер страницы должен быть больше или равен 1");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Размер страницы должен быть больше или равен 1");
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("title"));
        Page<News> newsPage = newsRepo.findAll(pageable);
        return newsPage.map(news -> modelMapper.map(news, NewsDto.class));
    }

    @Override
    public Page<NewsDto> getNews(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> newsPage = (searchTerm != null && !searchTerm.isEmpty())
                ? newsRepo.findByTitle(searchTerm, pageable)
                : newsRepo.findAll(pageable);
        return newsPage.map(news -> new NewsDto(news.getTitle(), news.getDescription(), news.getPublicationDate()));

    }

}
