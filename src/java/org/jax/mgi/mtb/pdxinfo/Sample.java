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
public class Sample {
    private String patientID;
    private String sampleID;
    private String collectionDate;
    private String collectionEvent; 
    private String monthsSinceCollection;
    private String age;
    private String diagnosis;
    private String tumorType;
    private String primarySite;
    private String collectionSite;
    private String stage; 
    private String stageSystem;
    private String grade;
    private String gradeSystem;
    private String virologyStatus; 
    private String shareable;
    private String treatmentNaiveAtCollection;
    private String treated;
    private String priorTreatment; 
    private String modelID;
    
    public static final String[] columns = {"patient_id","sample_id","collection_date","collection_event","months_since_collection_1","age_in_years_at_collection","diagnosis","tumour_type","primary_site","collection_site","stage","staging_system","grade","grading_system","virology_status","sharable","treatment_naive_at_collection","treated_at_collection","treated_prior_to_collection","model_id"};

    /**
     * @return the patientID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @param patient the patientID to set
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the sampleID
     */
    public String getSampleID() {
        return sampleID;
    }

    /**
     * @param sampleID the sampleID to set
     */
    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    /**
     * @return the collectionDate
     */
    public String getCollectionDate() {
        return collectionDate;
    }

    /**
     * @param collectionDate the collectionDate to set
     */
    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    /**
     * @return the collectionEvent
     */
    public String getCollectionEvent() {
        return collectionEvent;
    }

    /**
     * @param collectionEvent the collectionEvent to set
     */
    public void setCollectionEvent(String collectionEvent) {
        this.collectionEvent = collectionEvent;
    }

    /**
     * @return the monthsSinceCollection
     */
    public String getMonthsSinceCollection() {
        return monthsSinceCollection;
    }

    /**
     * @param monthsSinceCollection the monthsSinceCollection to set
     */
    public void setMonthsSinceCollection(String monthsSinceCollection) {
        this.monthsSinceCollection = monthsSinceCollection;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
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
     * @return the tumorType
     */
    public String getTumorType() {
        return tumorType;
    }

    /**
     * @param tumorType the tumorType to set
     */
    public void setTumorType(String tumorType) {
        this.tumorType = tumorType;
    }

    /**
     * @return the primarySite
     */
    public String getPrimarySite() {
        return primarySite;
    }

    /**
     * @param primarySite the primarySite to set
     */
    public void setPrimarySite(String primarySite) {
        this.primarySite = primarySite;
    }

    /**
     * @return the collectionSite
     */
    public String getCollectionSite() {
        return collectionSite;
    }

    /**
     * @param collectionSite the collectionSite to set
     */
    public void setCollectionSite(String collectionSite) {
        this.collectionSite = collectionSite;
    }

    /**
     * @return the stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * @return the stageSystem
     */
    public String getStageSystem() {
        return stageSystem;
    }

    /**
     * @param stageSystem the stageSystem to set
     */
    public void setStageSystem(String stageSystem) {
        this.stageSystem = stageSystem;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the gradeSystem
     */
    public String getGradeSystem() {
        return gradeSystem;
    }

    /**
     * @param gradeSystem the gradeSystem to set
     */
    public void setGradeSystem(String gradeSystem) {
        this.gradeSystem = gradeSystem;
    }

    /**
     * @return the virologyStatus
     */
    public String getVirologyStatus() {
        return virologyStatus;
    }

    /**
     * @param virologyStatus the virologyStatus to set
     */
    public void setVirologyStatus(String virologyStatus) {
        this.virologyStatus = virologyStatus;
    }

    /**
     * @return the shareable
     */
    public String getShareable() {
        return shareable;
    }

    /**
     * @param shareable the shareable to set
     */
    public void setShareable(String shareable) {
        this.shareable = shareable;
    }

    /**
     * @return the treatmentNaiveAtCollection
     */
    public String getTreatmentNaiveAtCollection() {
        return treatmentNaiveAtCollection;
    }

    /**
     * @param treatmentNaiveAtCollection the treatmentNaiveAtCollection to set
     */
    public void setTreatmentNaiveAtCollection(String treatmentNaiveAtCollection) {
        this.treatmentNaiveAtCollection = treatmentNaiveAtCollection;
    }

    /**
     * @return the treated
     */
    public String getTreated() {
        return treated;
    }

    /**
     * @param treated the treated to set
     */
    public void setTreated(String treated) {
        this.treated = treated;
    }

    /**
     * @return the priorTreatment
     */
    public String getPriorTreatment() {
        return priorTreatment;
    }

    /**
     * @param priorTreatment the priorTreatment to set
     */
    public void setPriorTreatment(String priorTreatment) {
        this.priorTreatment = priorTreatment;
    }

    /**
     * @return the modelID
     */
    public String getModelID() {
        return modelID;
    }

    /**
     * @param modelID the modelID to set
     */
    public void setModelID(String modelID) {
        this.modelID = modelID;
    }
    
    public String toString(){
        return this.toString("\t");
    }

    public String toString(String del){
       return   this.patientID+del+
                this.sampleID+del+
                this.collectionDate+del+
                this.collectionEvent+del+ 
                this.monthsSinceCollection+del+
                this.age+del+
                this.diagnosis+del+
                this.tumorType+del+
                this.primarySite+del+
                this.collectionSite+del+
                this.stage+del+ 
                this.stageSystem+del+
                this.grade+del+
                this.gradeSystem+del+
                this.virologyStatus+del+ 
                this.shareable+del+
                this.treatmentNaiveAtCollection+del+
                this.treated+del+
                this.priorTreatment+del+ 
                this.modelID;
    }
    
    
   
}
