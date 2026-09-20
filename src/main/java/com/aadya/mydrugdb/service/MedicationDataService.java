package com.aadya.mydrugdb.service;

import com.aadya.mydrugdb.dto.MedicationSearchResult;
import com.aadya.mydrugdb.dto.RxNormResponse;
import com.aadya.mydrugdb.dto.OpenFdaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class MedicationDataService {

    private final RestClient restClient;

    public MedicationDataService() {
        this.restClient = RestClient.create();
    }

    public MedicationSearchResult searchMedication(String name) {

        String url = "https://rxnav.nlm.nih.gov/REST/drugs.json";

        // Search RxNorm using the drug name
        RxNormResponse response = restClient.get()
                .uri(url + "?name={name}", name)
                .retrieve()
                .body(RxNormResponse.class);

        // Check that RxNorm actually returned something
        if (response == null ||
                response.getDrugGroup() == null ||
                response.getDrugGroup().getConceptGroup() == null ||
                response.getDrugGroup().getConceptGroup().isEmpty()) {

            return null;
        }

        // Look through the RxNorm results
        RxNormResponse.ConceptProperties result = null;
        RxNormResponse.ConceptProperties fallback = null;
        for (RxNormResponse.ConceptGroup group :
                response.getDrugGroup().getConceptGroup()) {

            if (group.getConceptProperties() != null &&
                    !group.getConceptProperties().isEmpty()) {

                // Get the RxNorm result that matches the searched term firstly otherwise continue

                for (RxNormResponse.ConceptProperties concept :
                        group.getConceptProperties()) {

                    if (concept.getName().toLowerCase()
                            .startsWith(name.toLowerCase())) {

                        fallback = concept;
                    }

                    if (concept.getName().toLowerCase()
                            .startsWith(name.toLowerCase())
                            && !concept.getName().contains("/")) {

                        result = concept;
                        break;
                    }
                }

            }
        }

                if (result == null) {
                    result = fallback;
                }

                if (result == null) {
                    return null;
                }

                 // Use the RxCUI to search openFDA
                OpenFdaResponse fdaResponse =
                        searchOpenFda(result.getRxcui());

                // Default values if openFDA has no information
                String manufacturer = null;
                String usage = null;
                String sideEffects = null;

                // Check that openFDA returned a result
                if (fdaResponse != null &&
                        fdaResponse.getResults() != null &&
                        !fdaResponse.getResults().isEmpty()) {

                    OpenFdaResponse.Result fdaResult =
                            fdaResponse.getResults().get(0);

                    // Get usage / indications
                    if (fdaResult.getIndications_and_usage() != null &&
                            !fdaResult.getIndications_and_usage().isEmpty()) {

                        usage =
                                fdaResult.getIndications_and_usage().get(0);
                    }

                    // Get adverse reactions / side effects
                    if (fdaResult.getAdverse_reactions() != null &&
                            !fdaResult.getAdverse_reactions().isEmpty()) {

                        sideEffects =
                                fdaResult.getAdverse_reactions().get(0);
                    }

                    // Get manufacturer
                    if (fdaResult.getOpenfda() != null &&
                            fdaResult.getOpenfda().getManufacturer_name() != null &&
                            !fdaResult.getOpenfda().getManufacturer_name().isEmpty()) {

                        manufacturer =
                                fdaResult.getOpenfda()
                                        .getManufacturer_name()
                                        .get(0);
                    }
                }

                // Combine RxNorm + openFDA information
                return new MedicationSearchResult(
                        result.getRxcui(),
                        result.getName(),
                        manufacturer,
                        usage,
                        sideEffects
                );
            }


    private OpenFdaResponse searchOpenFda(String rxcui) {

        String url = "https://api.fda.gov/drug/label.json";

        try {

            return restClient.get()
                    .uri(url + "?search=openfda.rxcui:{rxcui}&limit=1", rxcui)
                    .retrieve()
                    .body(OpenFdaResponse.class);

        } catch (HttpClientErrorException.NotFound e) {

            return null;
        }
    }
}