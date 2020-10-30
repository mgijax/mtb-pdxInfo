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
public class Sharing {
        private String modelId;
        private String providerType;
        private String accessibility;
        private String eropdx_accessibility_modlaity;
        private String email;
        private String name;
        private String formUrl;
        private String databaseUrl;
        private String providerName;
        private String providerAbbreviation;
        private String project;
        
        static final String[] columns = {"model_id","provider_type","accessibility","europdx_access_modality","email","name","form_url","database_url","provider_name","provider_abbreviation","project"};

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
     * @return the providerType
     */
    public String getProviderType() {
        return providerType;
    }

    /**
     * @param providerType the providerType to set
     */
    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    /**
     * @return the accessibility
     */
    public String getAccessibility() {
        return accessibility;
    }

    /**
     * @param accessibility the accessibility to set
     */
    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    /**
     * @return the eropdx_accessibility_modlaity
     */
    public String getEropdx_accessibility_modlaity() {
        return eropdx_accessibility_modlaity;
    }

    /**
     * @param eropdx_accessibility_modlaity the eropdx_accessibility_modlaity to set
     */
    public void setEropdx_accessibility_modlaity(String eropdx_accessibility_modlaity) {
        this.eropdx_accessibility_modlaity = eropdx_accessibility_modlaity;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the formUrl
     */
    public String getFormUrl() {
        return formUrl;
    }

    /**
     * @param formUrl the formUrl to set
     */
    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    /**
     * @return the databaseUrl
     */
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * @param databaseUrl the databaseUrl to set
     */
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    /**
     * @return the providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the providerAbbreviation
     */
    public String getProviderAbbreviation() {
        return providerAbbreviation;
    }

    /**
     * @param providerAbbreviation the providerAbbreviation to set
     */
    public void setProviderAbbreviation(String providerAbbreviation) {
        this.providerAbbreviation = providerAbbreviation;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    public String toString(){
        return this.toString("\t");
    }
    
    public String toString(String del){
        return this.modelId+del+
        this.providerType+del+
        this.accessibility+del+
        this.eropdx_accessibility_modlaity+del+
        this.email+del+
        this.name+del+
        this.formUrl+del+
        this.databaseUrl+del+
        this.providerName+del+
        this.providerAbbreviation+del+
        this.project;

    }
}
