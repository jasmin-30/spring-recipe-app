package com.example.recipe.controllers;

import com.example.recipe.domain.Category;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import com.example.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String indexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

//        categoryOptional.ifPresent(category -> System.out.println(category.getId()));
//        unitOfMeasureOptional.ifPresent(unitOfMeasure -> System.out.println(unitOfMeasure.getId()));

        if (categoryOptional.isPresent())
            System.out.println(categoryOptional.get().getId());
        else
            System.out.println("No Category for American found");

        if (unitOfMeasureOptional.isPresent())
            System.out.println(unitOfMeasureOptional.get().getId());
        else
            System.out.println("No UnitOfMeasure found for Teaspoon");

        return "index";
    }

    @RequestMapping({"/recipes"})
    public String recipePage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());

        return "recipe";
    }
}
