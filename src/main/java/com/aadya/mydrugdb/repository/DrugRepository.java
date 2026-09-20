package com.aadya.mydrugdb.repository;

import com.aadya.mydrugdb.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {

    List<Drug> findByNameContainingIgnoreCase(String name);

    boolean existsByRxcui(String rxcui);

    @Query(value = """
            SELECT *
            FROM drug
            WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(usage) LIKE LOWER(CONCAT('%', :keyword, '%'))
            ORDER BY name ASC
            """, nativeQuery = true)
    List<Drug> searchDrugs(@Param("keyword") String keyword);
}