package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.NewsDto;
import org.example.hospitalmanagementsystem.dto.NewsInputDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
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
        System.out.println("ужас" + news);
        try {
            this.newsRepo.save(news);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании новости", e);
        }
    }




    @Override
    public Page<NewsDto> getAllNews(int page, int size) {
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

}
