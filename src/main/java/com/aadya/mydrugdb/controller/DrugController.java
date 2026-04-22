package com.aadya.mydrugdb.controller;

import com.aadya.mydrugdb.model.Drug;
import com.aadya.mydrugdb.repository.DrugRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {

    private final DrugRepository drugRepository;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @GetMapping
    public String listDrugs(@RequestParam(required = false) String keyword, Model model) {
        List<Drug> drugs;

        if (keyword != null && !keyword.trim().isEmpty()) {
            drugs = drugRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            drugs = drugRepository.findAll();
        }

        model.addAttribute("drugs", drugs);
        model.addAttribute("keyword", keyword);

        return "drugs";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("drug", new Drug());
        return "add-drug";
    }

    @PostMapping
    public String addDrug(@ModelAttribute Drug drug) {
        drugRepository.save(drug);
        return "redirect:/drugs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drug Id: " + id));

        model.addAttribute("drug", drug);
        return "add-drug";
    }

    @GetMapping("/delete/{id}")
    public String deleteDrug(@PathVariable Long id) {
        drugRepository.deleteById(id);
        return "redirect:/drugs";
    }
}