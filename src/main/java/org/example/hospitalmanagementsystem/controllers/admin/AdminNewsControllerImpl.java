package org.example.hospitalmanagementsystem.controllers.admin;

import jakarta.validation.Valid;
import org.example.hospitalmanagementsystem.dto.NewsEditDto;
import org.example.hospitalmanagementsystem.dto.NewsInputDto;
import org.example.hospitalmanagementsystem.services.NewsService;
import org.example.hospitalmanagementsystemview.controllers.admin.AdminNewsController;
import org.example.hospitalmanagementsystemview.input.NewsCreateForm;
import org.example.hospitalmanagementsystemview.input.NewsEditForm;
import org.example.hospitalmanagementsystemview.input.NewsSearchForm;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.NewsCreateViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.NewsEditViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.NewsDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.NewsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.NewsListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsControllerImpl implements AdminNewsController {

    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    @GetMapping
    public String listNews(@ModelAttribute("newsForm") NewsSearchForm newsForm, Model model) {

        String newsSearchTerm = newsForm != null && newsForm.searchTerm() != null ? newsForm.searchTerm() : "";
        int newsPage = newsForm != null && newsForm.page() != null ? newsForm.page() : 1;
        int newsSize = newsForm != null && newsForm.size() != null ? newsForm.size() : 3;
        newsForm = new NewsSearchForm(newsSearchTerm, newsPage, newsSize);

        var newssPage = newsService.getNews(newsSearchTerm, newsPage, newsSize);
        var newsViewModels = newssPage.stream()
                .map(news -> new NewsViewModel(news.getTitle(), news.getDescription(), news.getPublicationDate()))
                .toList();

        var newsViewModel = new NewsListViewModel(
                newsViewModels, newssPage.getTotalPages()
        );

        model.addAttribute("model", newsViewModel);
        model.addAttribute("newsForm", newsForm);

        return "news-list";
    }

    @Override
    @GetMapping("/{id}")
    public String detailsNews(Integer id, Model model) {
        var news = newsService.getNewsById(id);
        var viewModel = new NewsDetailsViewModel(
                createBaseViewModel("детал новост"),
                new NewsViewModel(news.getTitle(), news.getDescription(), news.getPublicationDate())
        );
        model.addAttribute("model", viewModel);
        return "news-details";
    }

    @Override
    @GetMapping("/create")
    public String createNewsForm(Model model) {
        var viewModel = new NewsCreateViewModel(
                createBaseViewModel("Создание книги")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new NewsCreateForm("", ""));
        return "news-create";
    }

    @Override
    @PostMapping("/create")
    public String createNews(NewsCreateForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new NewsCreateViewModel(
                    createBaseViewModel("создат новаст")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "news-create";
        }

        newsService.createNews(new NewsInputDto(form.title(), form.description()));
        return "redirect:/admin/news";
    }

    @Override
    @GetMapping("/news/{id}/edit")
    public String editNewsForm(@PathVariable Integer id, Model model) {
        var news = newsService.getNewsById(id);
        var viewModel = new NewsEditViewModel(
                createBaseViewModel("менят новаст")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new NewsEditForm(news.getTitle(), news.getDescription(), news.getPublicationDate()));
        return "news-edit";
    }

    @Override
    @PostMapping("/news/{id}/edit")
    public String editNews(@PathVariable Integer id, @Valid @ModelAttribute("form") NewsEditForm form,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new NewsEditViewModel(
                    createBaseViewModel("Редактирование новости")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "news-edit";
        }

        newsService.updateNews(new NewsEditDto(id, form.title(), form.description(), form.publicationDate()));
        return "redirect:/admin/" + id;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
