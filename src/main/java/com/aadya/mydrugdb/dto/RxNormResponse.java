package com.aadya.mydrugdb.dto;

import java.util.List;

public class RxNormResponse {

    private DrugGroup drugGroup;

    public RxNormResponse() {}

    public DrugGroup getDrugGroup() {
        return drugGroup;
    }

    public void setDrugGroup(DrugGroup drugGroup) {
        this.drugGroup = drugGroup;
    }

    public static class DrugGroup {

        private List<ConceptGroup> conceptGroup;

        public DrugGroup() {}

        public List<ConceptGroup> getConceptGroup() {
            return conceptGroup;
        }

        public void setConceptGroup(List<ConceptGroup> conceptGroup) {
            this.conceptGroup = conceptGroup;
        }
    }

    public static class ConceptGroup {

        private String tty;
        private List<ConceptProperties> conceptProperties;

        public ConceptGroup() {}

        public String getTty() {
            return tty;
        }

        public void setTty(String tty) {
            this.tty = tty;
        }

        public List<ConceptProperties> getConceptProperties() {
            return conceptProperties;
        }

        public void setConceptProperties(List<ConceptProperties> conceptProperties) {
            this.conceptProperties = conceptProperties;
        }
    }

    public static class ConceptProperties {

        private String rxcui;
        private String name;

        public ConceptProperties() {}

        public String getRxcui() {
            return rxcui;
        }

        public void setRxcui(String rxcui) {
            this.rxcui = rxcui;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}