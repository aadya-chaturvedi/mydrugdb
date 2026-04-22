package com.aadya.mydrugdb.config;

import com.aadya.mydrugdb.model.Drug;
import com.aadya.mydrugdb.repository.DrugRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DrugRepository drugRepository;

    public DataLoader(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public void run(String... args) {
    }
}