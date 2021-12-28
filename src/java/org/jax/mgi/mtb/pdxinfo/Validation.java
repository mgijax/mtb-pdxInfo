/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jax.mgi.mtb.pdxinfo;

/**
 *
 * @author sbn
 */
public class Validation {
        private String modelId;
        private String validationTechnique;
        private String description;
        private String passagesTested;
        private String validationHostStrainFull;

        public static final String[] columns = {"model_id","validation_technique","description","passages_tested","validation_host_strain_nomenclature"};
    /**
     * @return the modelId
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * @param modelId the modelId to set
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * @return the validationTechnique
     */
    public String getValidationTechnique() {
        return validationTechnique;
    }

    /**
     * @param validationTechnique the validationTechnique to set
     */
    public void setValidationTechnique(String validationTechnique) {
        this.validationTechnique = validationTechnique;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the passagesTested
     */
    public String getPassagesTested() {
        return passagesTested;
    }

    /**
     * @param passagesTested the passagesTested to set
     */
    public void setPassagesTested(String passagesTested) {
        this.passagesTested = passagesTested;
    }

    /**
     * @return the validationHostStrainFull
     */
    public String getValidationHostStrainFull() {
        return validationHostStrainFull;
    }

    /**
     * @param validationHostStrainFull the validationHostStrainFull to set
     */
    public void setValidationHostStrainFull(String validationHostStrainFull) {
        this.validationHostStrainFull = validationHostStrainFull;
    }
 
    
    public String toString(){
        return this.toString("\t");
    }
    
    public String toString(String del){
        return  this.modelId+del+
        this.validationTechnique+del+
        this.description+del+
        this.passagesTested+del+
        this.validationHostStrainFull;

    }
 }