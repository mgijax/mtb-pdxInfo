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
public class Patient {
    private String id;
    private String sex;
    private String history;
    private String ethnicity;
    private String ethncityAssesement;
    private String diagnosis;
    private String ageAtDiagnosis;
    
    public static final String[] columns = {"patient_id","sex","history","ethnicity","ethnicity_assessment_method","initial_diagnosis","age_at_initial_diagnosis"};
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * @return the ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity the ethnicity to set
     */
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return the ethncityAssesement
     */
    public String getEthncityAssesement() {
        return ethncityAssesement;
    }

    /**
     * @param ethncityAssesement the ethncityAssesement to set
     */
    public void setEthncityAssesement(String ethncityAssesement) {
        this.ethncityAssesement = ethncityAssesement;
    }

    /**
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
     /**
     * @return the ageAtDiagnosis
     */
    public String getAgeAtDiagnosis() {
        return ageAtDiagnosis;
    }

    /**
     * @param ageAtDiagnosis the ageAtDiagnosis to set
     */
    public void setAgeAtDiagnosis(String ageAtDiagnosis) {
        this.ageAtDiagnosis = ageAtDiagnosis;
    }
    
    public String toString(){
        return toString("\t");
    }
    public String toString(String del){
        return this.id+del+this.sex+del+this.history+del+this.ethnicity+del+this.ethncityAssesement+del+this.diagnosis+del+this.ageAtDiagnosis;
    }

   
    
}
