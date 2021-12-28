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
        private String accessibility;
        private String eropdx_accessibility_modality;
        private String email;
        private String name;
        private String formUrl;
        private String databaseUrl;
      
        
        static final String[] columns = {"model_id","accessibility","europdx_access_modality","email","name","form_url","database_url"};

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
     * @return the eropdx_accessibility_modality
     */
    public String getEropdx_accessibility_modality() {
        return eropdx_accessibility_modality;
    }

    /**
     * @param eropdx_accessibility_modaity the eropdx_accessibility_modality to set
     */
    public void setEropdx_accessibility_modality(String eropdx_accessibility_modality) {
        this.eropdx_accessibility_modality = eropdx_accessibility_modality;
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

    public String toString(){
        return this.toString("\t");
    }
    
    public String toString(String del){
        return this.modelId+del+
        this.accessibility+del+
        this.eropdx_accessibility_modality+del+
        this.email+del+
        this.name+del+
        this.formUrl+del+
        this.databaseUrl;
    }
}
