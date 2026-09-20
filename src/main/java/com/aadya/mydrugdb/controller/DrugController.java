package com.aadya.mydrugdb.controller;

import com.aadya.mydrugdb.model.Drug;
import com.aadya.mydrugdb.repository.DrugRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.aadya.mydrugdb.service.MedicationDataService;
import com.aadya.mydrugdb.dto.MedicationSearchResult;

import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {

    private final DrugRepository drugRepository;
    private final MedicationDataService medicationDataService;

    public DrugController(DrugRepository drugRepository,
                          MedicationDataService medicationDataService) {
        this.drugRepository = drugRepository;
        this.medicationDataService = medicationDataService;
    }

    @GetMapping
    public String listDrugs(@RequestParam(required = false) String keyword, Model model) {
        List<Drug> drugs;

        if (keyword != null && !keyword.trim().isEmpty()) {
            drugs = drugRepository.searchDrugs(keyword);
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

    @GetMapping("/{id}")
    public String viewDrug(@PathVariable Long id, Model model) {

        Drug drug = drugRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid drug Id: " + id));

        model.addAttribute("drug", drug);

        return "drug-details";
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

    @GetMapping("/external-search")
    public String showMedicationSearch(
            @RequestParam(required = false) String name,
            Model model) {

        if (name != null && !name.trim().isEmpty()) {

            MedicationSearchResult result =
                    medicationDataService.searchMedication(name);

            model.addAttribute("result", result);
            model.addAttribute("searchedName", name);
        }

        return "medication-search";
    }

    @PostMapping("/save-external")
    public String saveExternalDrug(
            @RequestParam String rxcui,
            @RequestParam String name,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String usage,
            @RequestParam(required = false) String sideEffects) {

        if (drugRepository.existsByRxcui(rxcui)) {
            return "redirect:/drugs?duplicate=true";
        }

        Drug drug = new Drug(
                name,
                manufacturer,
                usage,
                sideEffects
        );

        drug.setRxcui(rxcui);

        drugRepository.save(drug);

        return "redirect:/drugs?saved=true";
    }

    @GetMapping("/search-test")
    @ResponseBody
    public MedicationSearchResult searchMedication(
            @RequestParam String name) {

        return medicationDataService.searchMedication(name);
    }
}