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
public class Mutation {

    static String[] columns = {
        "sample_id",
        "symbol",
        "biotype",
        "coding_sequence_change",
        "variant_class",
        "codon_change",
        "amino_acid_change",
        "consequence",
        "functional_prediction",
        "read_depth",
        "allele_frequency",
        "chromosome",
        "seq_start_position",
        "ref_allele",
        "alt_allele",
        "ucsc_gene_id",
        "ncbi_gene_id",
        "ncbi_transcript_id",
        "ensembl_gene_id",
        "ensembl_transcript_id",
        "variation_id",
        "genome_assembly",
        "platform_id"};

   
    private String sample_id = "";
    private String symbol = "";
    private String biotype = "";
    private String coding_sequence_change = "";
    private String variant_class = "";
    private String codon_change = "";
    private String amino_acid_change = "";
    private String consequence = "";
    private String functional_prediction = "";
    private String read_depth = "";
    private String allele_frequency = "";
    private String chromosome = "";
    private String seq_start_position = "";
    private String ref_allele = "";
    private String alt_allele = "";
    private String ucsc_gene_id = "";
    private String ncbi_gene_id = "";
    private String ncbi_transcript_id = "";
    private String ensembl_gene_id = "";
    private String ensembl_transcript_id = "";
    private String variation_id = "";
    private String genome_assembly = "";
    private String platform = "";

   
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
     * @return the biotype
     */
    public String getBiotype() {
        return biotype;
    }

    /**
     * @param biotype the biotype to set
     */
    public void setBiotype(String biotype) {
        this.biotype = biotype;
    }

    /**
     * @return the coding_sequence_change
     */
    public String getCoding_sequence_change() {
        return coding_sequence_change;
    }

    /**
     * @param coding_sequence_change the coding_sequence_change to set
     */
    public void setCoding_sequence_change(String coding_sequence_change) {
        this.coding_sequence_change = coding_sequence_change;
    }

    /**
     * @return the variant_class
     */
    public String getVariant_class() {
        return variant_class;
    }

    /**
     * @param variant_class the variant_class to set
     */
    public void setVariant_class(String variant_class) {
        this.variant_class = variant_class;
    }

    /**
     * @return the codon_change
     */
    public String getCodon_change() {
        return codon_change;
    }

    /**
     * @param codon_change the codon_change to set
     */
    public void setCodon_change(String codon_change) {
        this.codon_change = codon_change;
    }

    /**
     * @return the amino_acid_change
     */
    public String getAmino_acid_change() {
        return amino_acid_change;
    }

    /**
     * @param amino_acid_change the amino_acid_change to set
     */
    public void setAmino_acid_change(String amino_acid_change) {
        this.amino_acid_change = amino_acid_change;
    }

    /**
     * @return the consequence
     */
    public String getConsequence() {
        return consequence;
    }

    /**
     * @param consequence the consequence to set
     */
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    /**
     * @return the functional_prediction
     */
    public String getFunctional_prediction() {
        return functional_prediction;
    }

    /**
     * @param functional_prediction the functional_prediction to set
     */
    public void setFunctional_prediction(String functional_prediction) {
        this.functional_prediction = functional_prediction;
    }

    /**
     * @return the read_depth
     */
    public String getRead_depth() {
        return read_depth;
    }

    /**
     * @param read_depth the read_depth to set
     */
    public void setRead_depth(String read_depth) {
        this.read_depth = read_depth;
    }

    /**
     * @return the allele_frequency
     */
    public String getAllele_frequency() {
        return allele_frequency;
    }

    /**
     * @param allele_frequency the allele_frequency to set
     */
    public void setAllele_frequency(String allele_frequency) {
        this.allele_frequency = allele_frequency;
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
     * @return the ref_allele
     */
    public String getRef_allele() {
        return ref_allele;
    }

    /**
     * @param ref_allele the ref_allele to set
     */
    public void setRef_allele(String ref_allele) {
        this.ref_allele = ref_allele;
    }

    /**
     * @return the alt_allele
     */
    public String getAlt_allele() {
        return alt_allele;
    }

    /**
     * @param alt_allele the alt_allele to set
     */
    public void setAlt_allele(String alt_allele) {
        this.alt_allele = alt_allele;
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
     * @return the ncbi_transcript_id
     */
    public String getNcbi_transcript_id() {
        return ncbi_transcript_id;
    }

    /**
     * @param ncbi_transcript_id the ncbi_transcript_id to set
     */
    public void setNcbi_transcript_id(String ncbi_transcript_id) {
        this.ncbi_transcript_id = ncbi_transcript_id;
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
     * @return the variation_id
     */
    public String getVariation_id() {
        return variation_id;
    }

    /**
     * @param variation_id the variation_id to set
     */
    public void setVariation_id(String variation_id) {
        this.variation_id = variation_id;
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

    public static String getColumnHeaders() {
        return getColumnHeaders("\t");
    }

    public static String getColumnHeaders(String delim) {
        return String.join(delim, columns);
    }

    public String toString() {
        return toString("\t");
    }

    public String toString(String delim) {
        return 
                 this.sample_id + delim       
                + this.symbol + delim
                + this.biotype + delim
                + this.coding_sequence_change + delim
                + this.variant_class + delim
                + this.codon_change + delim
                + this.amino_acid_change + delim
                + this.consequence + delim
                + this.functional_prediction + delim
                + this.read_depth + delim
                + this.allele_frequency + delim
                + this.chromosome + delim
                + this.seq_start_position + delim
                + this.ref_allele + delim
                + this.alt_allele + delim
                + this.ucsc_gene_id + delim
                + this.ncbi_gene_id + delim
                + this.ncbi_transcript_id + delim
                + this.ensembl_gene_id + delim
                + this.ensembl_transcript_id + delim
                + this.variation_id + delim
                + this.genome_assembly + delim
                + this.platform;
    }
}
