package com.example.recipe.controllers;

import com.example.recipe.domain.Category;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/index"})
    public String indexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("TeaSpoon");

//        categoryOptional.ifPresent(category -> System.out.println(category.getId()));
//        unitOfMeasureOptional.ifPresent(unitOfMeasure -> System.out.println(unitOfMeasure.getId()));

        if (categoryOptional.isPresent())
            System.out.println(categoryOptional.get().getId());
        else
            System.out.println("No Category for American found");

        if (unitOfMeasureOptional.isPresent())
            System.out.println(unitOfMeasureOptional.get().getId());
        else
            System.out.println("No UnitOfMeasure found for tablespoon");

        return "index";
    }
}
