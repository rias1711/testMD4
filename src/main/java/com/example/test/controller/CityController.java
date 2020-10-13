package com.example.test.controller;

import com.example.test.model.City;
import com.example.test.model.Country;
import com.example.test.service.CityService;
import com.example.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @ModelAttribute(name = "countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    @GetMapping("/")
    public ModelAndView showCountries() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("add-city")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("add-city");
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("city", new City());
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @PostMapping("add-city")
    public ModelAndView addCity(@Validated @ModelAttribute("city")City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("add-city");
            modelAndView.addObject("countries", countryService.findAll());
            return modelAndView;
        }
        cityService.save(city);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit-city");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("edit-city")
    public ModelAndView editCity(@Validated @ModelAttribute("city")City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit-city");
            modelAndView.addObject("country", countryService.findAll());
            return modelAndView;
        }
        cityService.save(city);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("delete-city");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("delete-city")
    public String deleteCity(@ModelAttribute("city")City city) {
        cityService.delete(city);
        return "redirect:/";
    }

    @GetMapping("view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
}
