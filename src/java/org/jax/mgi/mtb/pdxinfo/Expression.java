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
public class Expression {
    
    private static final String[] columns = {"sample_id","chromosome","strand","seq_start_position","seq_end_position","symbol","ucsc_gene_id","ncbi_gene_id","ensembl_gene_id","ensembl_transcript_id","rnaseq_coverage","rnaseq_fpkm","rnaseq_tpm","rnaseq_count","affy_hgea_probe_id","affy_hgea_expression_value","illumina_hgea_probe_id","illumina_hgea_expression_value","z_score","genome_assembly","platform_id"};
    
   
    private String sample_id;
    private String chromosome;
    private String strand;
    private String seq_start_position;
    private String seq_end_position;
    private String symbol;
    private String ucsc_gene_id;
    private String ncbi_gene_id;
    private String ensembl_gene_id;
    private String ensembl_transcript_id;
    private String rnaseq_coverage;
    private String rnaseq_fpkm;
    private String rnaseq_tpm;
    private String rnaseq_count;
    private String affy_hgea_probe_id;
    private String affy_hgea_expression_value;
    private String illumina_hgea_probe_id;
    private String illumina_hgea_expression_value;
    private String z_score;
    private String genome_assembly;
    private String platform;
    
   
    
     public static String getColumnHeaders(String delim) {
        return String.join(delim, columns);
    }
    
    
     public String toString(){
        return this.toString("\t");
    }

    public String toString(String del){
       return 
               this.getSample_id()+del+
               
               this.getChromosome()+del+
               this.getStrand()+del+
               this.getSeq_start_position()+del+
               this.getSeq_end_position()+del+
               this.getSymbol()+del+
               this.getUcsc_gene_id()+del+
               this.getNcbi_gene_id()+del+
               this.getEnsembl_gene_id()+del+
               this.getEnsembl_transcript_id()+del+
               this.getRnaseq_coverage()+del+
               this.getRnaseq_fpkm()+del+
               this.getRnaseq_tpm()+del+
               this.getRnaseq_count()+del+
               this.getAffy_hgea_probe_id()+del+
               this.getAffy_hgea_expression_value()+del+
               this.getIllumina_hgea_probe_id()+del+
               this.getIllumina_hgea_expression_value()+del+
               this.getZ_score()+del+
               this.getGenome_assembly()+del+
               this.getPlatform();
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
     * @return the strand
     */
    public String getStrand() {
        return strand;
    }

    /**
     * @param strand the strand to set
     */
    public void setStrand(String strand) {
        this.strand = strand;
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
     * @return the ensembl_transcript_id
     */
    public String getEnsembl_transcript_id() {
        return ensembl_transcript_id;
    }

    /**
     * @param ensembl_transcript_id the ensembl_transcript_id to set
     */
    public void setEnsembl_transcript_id(String ensembl_transcript_id) {
        this.ensembl_transcript_id = ensembl_transcript_id;
    }

    /**
     * @return the rnaseq_coverage
     */
    public String getRnaseq_coverage() {
        return rnaseq_coverage;
    }

    /**
     * @param rnaseq_coverage the rnaseq_coverage to set
     */
    public void setRnaseq_coverage(String rnaseq_coverage) {
        this.rnaseq_coverage = rnaseq_coverage;
    }

    /**
     * @return the rnaseq_fpkm
     */
    public String getRnaseq_fpkm() {
        return rnaseq_fpkm;
    }

    /**
     * @param rnaseq_fpkm the rnaseq_fpkm to set
     */
    public void setRnaseq_fpkm(String rnaseq_fpkm) {
        this.rnaseq_fpkm = rnaseq_fpkm;
    }

    /**
     * @return the rnaseq_tpm
     */
    public String getRnaseq_tpm() {
        return rnaseq_tpm;
    }

    /**
     * @param rnaseq_tpm the rnaseq_tpm to set
     */
    public void setRnaseq_tpm(String rnaseq_tpm) {
        this.rnaseq_tpm = rnaseq_tpm;
    }

    /**
     * @return the rnaseq_count
     */
    public String getRnaseq_count() {
        return rnaseq_count;
    }

    /**
     * @param rnaseq_count the rnaseq_count to set
     */
    public void setRnaseq_count(String rnaseq_count) {
        this.rnaseq_count = rnaseq_count;
    }

    /**
     * @return the affy_hgea_probe_id
     */
    public String getAffy_hgea_probe_id() {
        return affy_hgea_probe_id;
    }

    /**
     * @param affy_hgea_probe_id the affy_hgea_probe_id to set
     */
    public void setAffy_hgea_probe_id(String affy_hgea_probe_id) {
        this.affy_hgea_probe_id = affy_hgea_probe_id;
    }

    /**
     * @return the affy_hgea_expression_value
     */
    public String getAffy_hgea_expression_value() {
        return affy_hgea_expression_value;
    }

    /**
     * @param affy_hgea_expression_value the affy_hgea_expression_value to set
     */
    public void setAffy_hgea_expression_value(String affy_hgea_expression_value) {
        this.affy_hgea_expression_value = affy_hgea_expression_value;
    }

    /**
     * @return the illumina_hgea_probe_id
     */
    public String getIllumina_hgea_probe_id() {
        return illumina_hgea_probe_id;
    }

    /**
     * @param illumina_hgea_probe_id the illumina_hgea_probe_id to set
     */
    public void setIllumina_hgea_probe_id(String illumina_hgea_probe_id) {
        this.illumina_hgea_probe_id = illumina_hgea_probe_id;
    }

    /**
     * @return the illumina_hgea_expression_value
     */
    public String getIllumina_hgea_expression_value() {
        return illumina_hgea_expression_value;
    }

    /**
     * @param illumina_hgea_expression_value the illumina_hgea_expression_value to set
     */
    public void setIllumina_hgea_expression_value(String illumina_hgea_expression_value) {
        this.illumina_hgea_expression_value = illumina_hgea_expression_value;
    }

    /**
     * @return the z_score
     */
    public String getZ_score() {
        return z_score;
    }

    /**
     * @param z_score the z_score to set
     */
    public void setZ_score(String z_score) {
        this.z_score = z_score;
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
    
}
