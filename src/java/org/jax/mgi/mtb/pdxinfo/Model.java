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
public class Model {
    private String modelId ;
    private String hostStrain;
    private String hostStrainFull;
    private String engraftmentSite;
    private String engraftmentType;
    private String sampleType;
    private String sampleState;
    private String passage;
    private String publications;

    public static final String[] columns = {"model_id","host_strain_name","host_strain_nomenclature","engraftment_site","engraftment_type","sample_type","sample_state","passage_number","publications"};
    
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
     * @return the hostStrain
     */
    public String getHostStrain() {
        return hostStrain;
    }

    /**
     * @param hostStrain the hostStrain to set
     */
    public void setHostStrain(String hostStrain) {
        this.hostStrain = hostStrain;
    }

    /**
     * @return the hostStrainFull
     */
    public String getHostStrainFull() {
        return hostStrainFull;
    }

    /**
     * @param hostStrainFull the hostStrainFull to set
     */
    public void setHostStrainFull(String hostStrainFull) {
        this.hostStrainFull = hostStrainFull;
    }

    /**
     * @return the engraftmentSite
     */
    public String getEngraftmentSite() {
        return engraftmentSite;
    }

    /**
     * @param engraftmentSite the engraftmentSite to set
     */
    public void setEngraftmentSite(String engraftmentSite) {
        this.engraftmentSite = engraftmentSite;
    }

    /**
     * @return the engraftmentType
     */
    public String getEngraftmentType() {
        return engraftmentType;
    }

    /**
     * @param engraftmentType the engraftmentType to set
     */
    public void setEngraftmentType(String engraftmentType) {
        this.engraftmentType = engraftmentType;
    }

    /**
     * @return the sampleType
     */
    public String getSampleType() {
        return sampleType;
    }

    /**
     * @param sampleType the sampleType to set
     */
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    /**
     * @return the passage
     */
    public String getPassage() {
        return passage;
    }

    /**
     * @param passage the passage to set
     */
    public void setPassage(String passage) {
        this.passage = passage;
    }

    /**
     * @return the publications
     */
    public String getPublications() {
        return publications;
    }

    /**
     * @param publications the publications to set
     */
    public void setPublications(String publications) {
        this.publications = publications;
    }

    
    public String toString(){
        return this.toString("\t");
    }
    
    public String toString(String del){
        return this.modelId+del+ 
                this.hostStrain+del+
                this.hostStrainFull+del+
                this.engraftmentSite+del+
                this.engraftmentType+del+
                this.sampleType+del+
                this.sampleState+del+
                this.passage+del+
                this.publications;

                
    }

    /**
     * @return the sampleState
     */
    public String getSampleState() {
        return sampleState;
    }

    /**
     * @param sampleState the sampleState to set
     */
    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
    }
    
}
