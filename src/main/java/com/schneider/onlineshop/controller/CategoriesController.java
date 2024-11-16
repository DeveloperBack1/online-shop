package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Category;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    private List<Category> categoryList;

    @PostConstruct
    void init() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Lebensmittel"));
        categoryList.add(new Category(2, "Haushaltschemikalien>"));
        categoryList.add(new Category(3, "Funktechnik"));
        categoryList.add(new Category(4, "Spielzeugen"));
        categoryList.add(new Category(5, "Kleider"));
        categoryList.add(new Category(6, "Other"));



        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }

    @GetMapping
    List<Category> getAllCategories() {
        return categoryList;
    }

    @GetMapping(value = "/find/{id}")
    Category getCategoryById(@PathVariable Long id) { ///categories/find/3
        return categoryList.stream()
                .filter(category -> category.getCategoryID()==id)
                .findFirst()
                .orElse(null);
    }

    // Экранирование кириллицы для url - https://planetcalc.ru/683/
    @GetMapping(value = "/get")
    Category getCategoryByName(@RequestParam String name) { ///categories/get?name=Other,k=2
        return categoryList.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

@PostMapping
public boolean createCategories(@RequestBody Category newCategory) {
        return categoryList.add(newCategory);
}

    @PutMapping
    public Category updateCategories(@RequestBody Category updCategory) { //update
        Category result = categoryList.stream()
                .filter(category -> category.getCategoryID() == updCategory.getCategoryID())
                .findFirst()
                .orElse(null);
        if(result!=null) {
            result.setName(updCategory.getName());
        }
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategories(@PathVariable Long id) { //delete
        Iterator<Category> it = categoryList.iterator();
        while (it.hasNext()) {
            Category current = it.next();
            if(current.getCategoryID()==id) {
                it.remove();
            }
        }
    }
    @PreDestroy
    void destroy() {
        System.out.println("Выполняем логику при окончании работы с  объектом "+this.getClass().getName());
    }
}



