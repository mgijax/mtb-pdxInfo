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
public class CNA {
    
    
    private static final String[] columns = {"sample_id","chromosome","seq_start_position","seq_end_position","symbol","ucsc_gene_id","ncbi_gene_id","ensembl_gene_id","log10r_cna","log2r_cna","fold_change","copy_number_status","gistic_value","picnic_value","genome_assembly","platform"};

   

    
    private String sample_id;
    private String chromosome;
    private String seq_start_position;
    private String seq_end_position;
    private String symbol;
    private String ucsc_gene_id;
    private String ncbi_gene_id;
    private String ensembl_gene_id;
    private String log10r_cna;
    private String log2r_cna;
    private String fold_change;
    private String copy_number_status;
    private String gistic_value;
    private String picnic_value;
    private String genome_assembly;
    private String platform;
    
    

   

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
     * @return the chromosome
     */
    public String getChromosome() {
        return chromosome;
    }

    /**
     * @param chromosome the chromosome to set
     */
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @return the seq_start_position
     */
    public String getSeq_start_position() {
        return seq_start_position;
    }

    /**
     * @param seq_start_position the seq_start_position to set
     */
    public void setSeq_start_position(String seq_start_position) {
        this.seq_start_position = seq_start_position;
    }

    /**
     * @return the seq_end_position
     */
    public String getSeq_end_position() {
        return seq_end_position;
    }

    /**
     * @param seq_end_position the seq_end_position to set
     */
    public void setSeq_end_position(String seq_end_position) {
        this.seq_end_position = seq_end_position;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the ucsc_gene_id
     */
    public String getUcsc_gene_id() {
        return ucsc_gene_id;
    }

    /**
     * @param ucsc_gene_id the ucsc_gene_id to set
     */
    public void setUcsc_gene_id(String ucsc_gene_id) {
        this.ucsc_gene_id = ucsc_gene_id;
    }

    /**
     * @return the ncbi_gene_id
     */
    public String getNcbi_gene_id() {
        return ncbi_gene_id;
    }

    /**
     * @param ncbi_gene_id the ncbi_gene_id to set
     */
    public void setNcbi_gene_id(String ncbi_gene_id) {
        this.ncbi_gene_id = ncbi_gene_id;
    }

    /**
     * @return the ensembl_gene_id
     */
    public String getEnsembl_gene_id() {
        return ensembl_gene_id;
    }

    /**
     * @param ensembl_gene_id the ensembl_gene_id to set
     */
    public void setEnsembl_gene_id(String ensembl_gene_id) {
        this.ensembl_gene_id = ensembl_gene_id;
    }

    /**
     * @return the log10r_cna
     */
    public String getLog10r_cna() {
        return log10r_cna;
    }

    /**
     * @param log10r_cna the log10r_cna to set
     */
    public void setLog10r_cna(String log10r_cna) {
        this.log10r_cna = log10r_cna;
    }

    /**
     * @return the log2r_cna
     */
    public String getLog2r_cna() {
        return log2r_cna;
    }

    /**
     * @param log2r_cna the log2r_cna to set
     */
    public void setLog2r_cna(String log2r_cna) {
        this.log2r_cna = log2r_cna;
    }

    /**
     * @return the fold_change
     */
    public String getFold_change() {
        return fold_change;
    }

    /**
     * @param fold_change the fold_change to set
     */
    public void setFold_change(String fold_change) {
        this.fold_change = fold_change;
    }

    /**
     * @return the copy_number_status
     */
    public String getCopy_number_status() {
        return copy_number_status;
    }

    /**
     * @param copy_number_status the copy_number_status to set
     */
    public void setCopy_number_status(String copy_number_status) {
        this.copy_number_status = copy_number_status;
    }

    /**
     * @return the gistic_value
     */
    public String getGistic_value() {
        return gistic_value;
    }

    /**
     * @param gistic_value the gistic_value to set
     */
    public void setGistic_value(String gistic_value) {
        this.gistic_value = gistic_value;
    }

    /**
     * @return the picnic_value
     */
    public String getPicnic_value() {
        return picnic_value;
    }

    /**
     * @param picnic_value the picnic_value to set
     */
    public void setPicnic_value(String picnic_value) {
        this.picnic_value = picnic_value;
    }

    /**
     * @return the genome_assembly
     */
    public String getGenome_assembly() {
        return genome_assembly;
    }

    /**
     * @param genome_assembly the genome_assembly to set
     */
    public void setGenome_assembly(String genome_assembly) {
        this.genome_assembly = genome_assembly;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
     public static String getColumnHeaders(String delim) {
        return String.join(delim, columns);
    }
    
    
    public String toString(){
        return this.toString("\t");
    }

    public String toString(String del){
       return 
                this.sample_id+del+
                this.chromosome+del+
                this.seq_start_position+del+
                this.seq_end_position+del+
                this.symbol+del+
                this.ucsc_gene_id+del+
                this.ncbi_gene_id+del+
                this.ensembl_gene_id+del+
                this.log10r_cna+del+
                this.log2r_cna+del+
                this.fold_change+del+
                this.copy_number_status+del+
                this.gistic_value+del+
                this.picnic_value+del+
                this.genome_assembly+del+
                this.platform;
    }
    
}
