package com.aadya.mydrugdb.dto;

import java.util.List;

public class OpenFdaResponse {

    private List<Result> results;
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {


        private List<String> indications_and_usage;
        private List<String> adverse_reactions;
        private OpenFda openfda;

        public List<String> getIndications_and_usage() {
            return indications_and_usage;
        }

        public void setIndications_and_usage(List<String> indications_and_usage) {
            this.indications_and_usage = indications_and_usage;
        }

        public List<String> getAdverse_reactions() {
            return adverse_reactions;
        }

        public void setAdverse_reactions(List<String> adverse_reactions) {
            this.adverse_reactions = adverse_reactions;
        }

        public OpenFda getOpenfda() {
            return openfda;
        }

        public void setOpenfda(OpenFda openfda) {
            this.openfda = openfda;
        }

    }

    public static class OpenFda {

        private List<String> manufacturer_name;
        public List<String> getManufacturer_name() {
            return manufacturer_name;
        }

        public void setManufacturer_name(List<String> manufacturer_name) {
            this.manufacturer_name = manufacturer_name;
        }

    }
}