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
public class Cytogenetics {

    static final String[] columns = {"sample_id", "sample_origin", "passage", "host_strain_nomenclature", "model_id", "marker_name", "marker_status", "essential_or_additional_marker", "technique_name", "protocol_file_name", "result_file_name"};

    private String sample_id;
    private String sample_origin;
    private String passage;
    private String host_strain_nomenclature;
    private String model_id;
    private String marker_name;
    private String marker_status;
    private String essential_or_additional_marker;
    private String technique_name;
    private String protocol_file_name;
    private String result_file_name;

    public static void main(String[] args) {

    }

    /**
     * @return the sample_id
     */
    public String getSample_id() {
        return sample_id;
    }

    /**
     * @param sample_id the sample_id to set
     */
    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    /**
     * @return the sample_origin
     */
    public String getSample_origin() {
        return sample_origin;
    }

    /**
     * @param sample_origin the sample_origin to set
     */
    public void setSample_origin(String sample_origin) {
        this.sample_origin = sample_origin;
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
     * @return the host_strain_nomenclature
     */
    public String getHost_strain_nomenclature() {
        return host_strain_nomenclature;
    }

    /**
     * @param host_strain_nomenclature the host_strain_nomenclature to set
     */
    public void setHost_strain_nomenclature(String host_strain_nomenclature) {
        this.host_strain_nomenclature = host_strain_nomenclature;
    }

    /**
     * @return the model_id
     */
    public String getModel_id() {
        return model_id;
    }

    /**
     * @param model_id the model_id to set
     */
    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    /**
     * @return the marker_name
     */
    public String getMarker_name() {
        return marker_name;
    }

    /**
     * @param marker_name the marker_name to set
     */
    public void setMarker_name(String marker_name) {
        this.marker_name = marker_name;
    }

    /**
     * @return the marker_status
     */
    public String getMarker_status() {
        return marker_status;
    }

    /**
     * @param marker_status the marker_status to set
     */
    public void setMarker_status(String marker_status) {
        this.marker_status = marker_status;
    }

    /**
     * @return the essential_or_additional_marker
     */
    public String getEssential_or_additional_marker() {
        return essential_or_additional_marker;
    }

    /**
     * @param essential_or_additional_marker the essential_or_additional_marker
     * to set
     */
    public void setEssential_or_additional_marker(String essential_or_additional_marker) {
        this.essential_or_additional_marker = essential_or_additional_marker;
    }

    /**
     * @return the technique_name
     */
    public String getTechnique_name() {
        return technique_name;
    }

    /**
     * @param technique_name the technique_name to set
     */
    public void setTechnique_name(String technique_name) {
        this.technique_name = technique_name;
    }

    /**
     * @return the protocol_file_name
     */
    public String getProtocol_file_name() {
        return protocol_file_name;
    }

    /**
     * @param protocol_file_name the protocol_file_name to set
     */
    public void setProtocol_file_name(String protocol_file_name) {
        this.protocol_file_name = protocol_file_name;
    }

    /**
     * @return the result_file_name
     */
    public String getResult_file_name() {
        return result_file_name;
    }

    /**
     * @param result_file_name the result_file_name to set
     */
    public void setResult_file_name(String result_file_name) {
        this.result_file_name = result_file_name;
    }

    
     public static String getColumnHeaders(String delim) {
        return String.join(delim, columns);
    }
    
    public String toString() {
        return toString("\t");
    }

    public String toString(String del) {
        return this.sample_id + del
                + this.sample_origin + del
                + this.passage + del
                + this.host_strain_nomenclature + del
                + this.model_id + del
                + this.marker_name + del
                + this.marker_status + del
                + this.essential_or_additional_marker + del
                + this.technique_name + del
                + this.protocol_file_name + del
                + this.result_file_name;
    }
}
