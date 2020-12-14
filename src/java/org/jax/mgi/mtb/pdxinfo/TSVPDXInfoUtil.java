/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jax.mgi.mtb.pdxinfo;

import com.starlims.www.webservices.GetPDXPatientClinicalReports_sessionless;
import com.starlims.www.webservices.GetPDXStatusReport_sessionless;
import com.starlims.www.webservices.MTB_wsStub;
import com.starlims.www.webservices.PDXPatientClinicalReport;
import com.starlims.www.webservices.Pdx_model_status;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXContent;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXDAO;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXGraphic;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXLink;

/**
 * Access to data from ELIMS web services
 *
 * @author sbn
 */
public class TSVPDXInfoUtil {

    private String userName;
    private String password;
    private String mtbUser;
    private String mtbPassword;
    private String mtbDB;
    private String socURL;

    private static String baseURI = "http://pdxdata.jax.org/api/";

    private static String VARIANT_URL = baseURI + "variants";
    private static String CNA_URL = baseURI+"cnv_gene?filter=yes&keepnulls=yes&all_ctp_genes=yes&model=";
    private static String EXP_URL = baseURI+"expression?filter=yes&keepnulls=yes&all_ctp_genes=yes&model=";

    

    private static final String GRAPHIC_URL = "http://tumor.informatics.jax.org/mtbwi/pdxDetailsTabs.do?tab=graphicDetails&contentKey=";

    private static final String NSG_OFFICIAL_NAME = "NOD.Cg-Prkdcscid Il2rgtm1Wjl/SzJ";
    
    private static final String NOT_SPECIFIED = "Not Specified";
    private static final String ORTHOTOPIC ="Orthotopic";
    private static final String HETEROTOPIC ="Heterotopic";
    
    private static final String VALIDATION_DESCRIPTION ="The histology of the engrafted tumor retains morphologic features of the primary patient tumor as assessed by a Board Certified Pathologist. "+
	"IHC for human Ki67 validates that the tumor cells are actively dividing and are of human origin. "+
	"IHC for CD45 and cytokeratin (AE1/AE3) rules out a xenograft-associated lymphocytic tumor. "+ 
	"STR analysis validates provenance of engrafted tumor."; 
    
    private static final String VALIDATION ="Histology, Ki67 IHC, CD45 IHC, STR analysis";
    private static final String VALIDATION_PENDING = "Validation is pending.";

    private static final String ASSEMBLY_38 = "GRCh38";
    
    private final static Logger log
            = Logger.getLogger(TSVPDXInfoUtil.class.getName());
    
    HashMap<String, String> modelPatients = new HashMap();
    HashMap<String, ArrayList<String>> modelClincalDetails = new HashMap<String, ArrayList<String>>();
    
    
    private static boolean initialized = false;
    private static ArrayList<Patient> patientCache = new ArrayList();
    private static ArrayList<Sample> sampleCache  = new ArrayList();
    private static ArrayList<Model> modelCache = new ArrayList();
    private static ArrayList<Validation> validationCache = new ArrayList();
    private static ArrayList<Sharing> sharingCache = new ArrayList();
    private static ArrayList<Cytogenetics> cytoCache = new ArrayList();
    private static String variationModels = "";
    private static String cnaModels = "";
    private static String expModels = "";
    
    public static void main(String[] args){
        TSVPDXInfoUtil util = new TSVPDXInfoUtil();
        //util.getClinicalDetails();
      //  util.loadPDXInfo();
      //  System.out.println(util.getCNA("TM00099"));
     
      //  System.out.println(util.loadModelsWithCNAData());
      
    }

    public TSVPDXInfoUtil() {
        try{
           
            Properties p = new Properties();
            p.load(TSVPDXInfoUtil.class.getClassLoader().getResource("pdxInfo.properties").openStream());
            userName = p.getProperty("elimsuser");
            password = p.getProperty("elimspassword");
            mtbUser = p.getProperty("mtbuser");
            mtbPassword = p.getProperty("mtbpassword");
            mtbDB = p.getProperty("mtbdb");
            socURL = p.getProperty("socurl");
            
            
            
        }catch(Exception e){
            log.error("can't load properties file",e);
        }
    }
    
    public String getPatients(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Patient.columns)).append("\n");
        for(Patient p : patientCache){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
     
    public String getSamples(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Sample.columns)).append("\n");
        for(Sample s : sampleCache){
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
     
    public String getModels(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Model.columns)).append("\n");
        for(Model m : modelCache){
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
     
    public String getValidations(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Validation.columns)).append("\n");
        for(Validation v : validationCache){
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getSharing(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Sharing.columns)).append("\n");
        for(Sharing s : sharingCache){
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public String getCytogenetics(){
        checkInitialized();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t",Cytogenetics.columns)).append("\n");
        for(Cytogenetics c : cytoCache){
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public String getModelsWithVariationData(){
        checkInitialized();
        return variationModels;
    }
    
     public String getModelsWithCNAData(){
        checkInitialized();
        return cnaModels;
    }
     
    public String getModelsWithExpData(){
        checkInitialized();
        return expModels;
    }
  
    private void checkInitialized(){
        if(!initialized){
            loadPDXInfo();
        }
    }
    
     public String getVariationTSV(String model) {

        int limit = 150000;
        int skip = 0;
        boolean done = false;

        ArrayList<Mutation> mutes = new ArrayList<>();
       
        try {
            JSONArray array;

            do {

                String params = "?keepnulls=yes&model=" + model + "&skip=" + skip + "&limit=" + limit + "&filter=yes";
                JSONObject job = new JSONObject(getJSON(VARIANT_URL + params, null));

                if(job.has("total_rows")){
                    int total = (Integer) job.get("total_rows");
                    done = (total < skip);
                }else{
                    break;
                }
                array = (JSONArray) job.get("data");

                boolean includeWholeExome = true;

                for (int i = 0; i < array.length(); i++) {

                    if (!"Whole Exome".equals(getField(array.getJSONObject(i), "platform")) || includeWholeExome) {

                        Mutation mute = new Mutation();
                        mute.setModel_id(getField(array.getJSONObject(i), "model_id"));
                        mute.setSample_id(getField(array.getJSONObject(i), "sample_name"));
                        mute.setSample_origin("xenograft"); // verify we don't have any PT data for public comsumption
                        mute.setHost_strain_nomenclature(NSG_OFFICIAL_NAME);
                        mute.setPassage(getField(array.getJSONObject(i), "passage_num"));
                        mute.setSymbol(getField(array.getJSONObject(i), "gene_symbol"));
                        mute.setBiotype("");
                        mute.setCoding_sequence_change("");
                        mute.setVariant_class("");
                        mute.setCodon_change("");
                        mute.setAmino_acid_change(getField(array.getJSONObject(i), "amino_acid_change"));
                        mute.setConsequence(getField(array.getJSONObject(i), "consequence"));
                        mute.setFunctional_prediction("");
                        mute.setRead_depth(getField(array.getJSONObject(i), "read_depth"));
                        mute.setAllele_frequency(getField(array.getJSONObject(i), "allele_frequency"));
                        mute.setChromosome(getField(array.getJSONObject(i), "chromosome"));
                        mute.setSeq_start_position(getField(array.getJSONObject(i), "seq_position"));
                        mute.setRef_allele(getField(array.getJSONObject(i), "ref_allele"));
                        mute.setAlt_allele(getField(array.getJSONObject(i), "alt_allele"));
                        mute.setUcsc_gene_id("");
                        mute.setNcbi_gene_id("");
                        mute.setNcbi_transcript_id("");
                        mute.setEnsembl_gene_id("");
                        mute.setEnsembl_transcript_id("");
                        mute.setVariation_id("");
                        mute.setGenome_assembly(ASSEMBLY_38);
                        mute.setPlatform(getField(array.getJSONObject(i), "platform"));
                        
                       mutes.add(mute);
                    }
                }

                skip += limit;

            } while (array.length() > 0 || !done);

           

        } catch (Exception e) {
            System.out.println("Error loading PDX info.");
            e.printStackTrace();
            log.error("Error loading PDX Info", e);
           
        }

        StringBuilder sb = new StringBuilder();
        if(mutes.size()>0){
            sb.append(Mutation.getColumnHeaders()).append("\n");
            for(Mutation mute : mutes){
                sb.append(mute.toString()).append("\n");
            }
        }
        return sb.toString();
    }
     
     public String getCNA(String modelID){
         
         StringBuilder sb = new StringBuilder(CNA.getColumnHeaders("\t")).append("\n");
         
     
          try{
             
            JSONObject job = new JSONObject(getJSON(CNA_URL+modelID));
            JSONArray result = job.getJSONArray("data");
            for(int i = 0; i < result.length(); i++){
                CNA cna = new CNA();
                JSONObject row = result.getJSONObject(i);
     
                cna.setModel_id(row.getString("model_name"));
                cna.setSample_id(row.getString("sample_name"));
                cna.setSample_origin("PDX");
                cna.setPassage(row.getString("passage_num"));
                cna.setHost_strain_nomenclature(NSG_OFFICIAL_NAME);
                cna.setChromosome(row.getString("chromosome"));
                cna.setSeq_start_position(row.getString("gene_start_bp"));
                cna.setSeq_end_position(row.getString("gene_end_bp"));
                cna.setSymbol(row.getString("gene_symbol"));
                cna.setUcsc_gene_id("");
                cna.setNcbi_gene_id("");
                cna.setEnsembl_gene_id(row.getString("ensembl_gene_id"));
                cna.setLog10r_cna("");
                cna.setLog2r_cna(row.getString("logratio_2"));
                cna.setFold_change(row.getString("cn_raw"));
                cna.setCopy_number_status("");
                cna.setGistic_value("");
                cna.setPicnic_value("");
                cna.setGenome_assembly(ASSEMBLY_38);
                cna.setPlatform(row.getString("platform"));
                
                sb.append(cna.toString()).append("\n");
            }
    
          }catch(Exception e){
              log.error(e);
              e.printStackTrace();
          }
         return sb.toString();
         }
     
      public String getExpression(String modelID){
         
         StringBuilder sb = new StringBuilder(Expression.getColumnHeaders("\t")).append("\n");
         
     
          try{
             
            JSONObject job = new JSONObject(getJSON(EXP_URL+modelID));
            JSONArray result = job.getJSONArray("data");
            for(int i = 0; i < result.length(); i++){
                
                
            //                 model_id;
            //                 sample_id;
            //                 sample_origin;
            //                 host_strain_nomenclature;
            //                 passage;
            //                 chromosome;
            //                 strand;
            //                 seq_start_position;
            //                 seq_end_position;
            //                 symbol;
            //                 ucsc_gene_id;
            //                 ncbi_gene_id;
            //                 ensembl_gene_id;
            //                 ensembl_transcript_id;
            //                 rnaseq_coverage;
            //                 rnaseq_fpkm;
            //                 rnaseq_tpm;
            //                 rnaseq_count;
            //                 affy_hgea_probe_id;
            //                 affy_hgea_expression_value;
            //                 illumina_hgea_probe_id;
            //                 illumina_hgea_expression_value;
            //                 z_score;
            //                 genome_assembly;
            //                 platform;
                
                Expression expr = new Expression();
                JSONObject row = result.getJSONObject(i);
     
                expr.setModel_id(row.getString("model_name"));
                expr.setSample_id(row.getString("sample_name"));
                expr.setSample_origin("PDX");
                expr.setHost_strain_nomenclature(NSG_OFFICIAL_NAME);
                expr.setPassage(row.getString("passage_num"));
                expr.setChromosome("");
                expr.setStrand("");
                expr.setSeq_start_position("");
                expr.setSeq_end_position("");
                expr.setSymbol(row.getString("gene_symbol"));
                expr.setUcsc_gene_id("");
                expr.setNcbi_gene_id("");
                expr.setEnsembl_gene_id(row.getString("ensembl_gene_id"));
                expr.setEnsembl_transcript_id("");
                expr.setRnaseq_coverage("");
                expr.setRnaseq_fpkm(row.getString("fpkm"));
                expr.setRnaseq_tpm(row.getString("tpm"));
                expr.setRnaseq_count("");
                expr.setAffy_hgea_probe_id("");
                expr.setAffy_hgea_expression_value("");
                expr.setIllumina_hgea_probe_id("");
                expr.setIllumina_hgea_expression_value("");
                expr.setZ_score(row.getString("z_score_expression"));
                expr.setGenome_assembly(ASSEMBLY_38);
                expr.setPlatform(row.getString("platform"));
                
                sb.append(expr.toString()).append("\n");
            }
    
          }catch(Exception e){
              log.error(e);
              e.printStackTrace();
          }
         return sb.toString();
         }
   
   
     
   

    // produces Patients, Samples, Models, validations, sharings  TSV reports for all models
    
    
    
    private void loadPDXInfo() {
        
        
       
        
        HashMap<String,Sample> samplesMap  = new HashMap<>();
        HashMap<String,Patient> patientsMap = new HashMap<>();
        HashMap<String,Model> modelsMap = new HashMap<>();
        HashMap<String,Validation> validationsMap = new HashMap<>();
        HashMap<String,Sharing> sharingsMap = new HashMap<>();
        
        
        
        try {

            getClinicalDetails();
            
            PDXDAO dao = getPDXDAO();

           

            MTB_wsStub stub = getStub();

            GetPDXStatusReport_sessionless soapRequest
                    = GetPDXStatusReport_sessionless.class.newInstance();

            soapRequest.setPwd(password);
            soapRequest.setUser(userName);

            Pdx_model_status[] result = stub.getPDXStatusReport_sessionless(soapRequest).getGetPDXStatusReport_sessionlessResult().getPdx_model_status();

          
           
            if (result.length > 0) {
               
                for (int i = 0; i < result.length; i++) {

                   String id = result[i].getIdentifier();
                   String patientID = modelPatients.get(id);
                    try {
                        int intID = new Integer(id);
                        id = ("TM" + String.format("%05d", intID));
                    } catch (NumberFormatException e) {
                        // this will happen for J##### ids which is ok
                    }
                    
                    int numericID =111111;
                      try{
                        numericID = new Integer(result[i].getIdentifier().replaceAll("J",""));
                    }catch(Exception e){}
                    
                     

                    String status = result[i].getModel_Status();
                    
                     if (status.contains("Active Available") 
                        || status.contains("Active: Available")    
                        || status.contains("Blood")
                        || status.contains("Data")
                        || (status.contains("Active: P1 Available") && numericID < 111056 )) {
                         
                          log.info("Loading data for "+id);
                         
                            Patient pat = new Patient();
                            pat.setId(patientID);
                            pat.setSex(result[i].getGender());
                            pat.setEthnicity(result[i].getEthnicity());
                            pat.setEthncityAssesement("Self reported");
                            pat.setDiagnosis(getDiagnosis(clean(result[i].getInitial_Diagnosis()), clean(result[i].getClinical_Diagnosis())));
                            pat.setAgeAtDiagnosis(result[i].getPatient_Age());

                            pat.setHistory(NOT_SPECIFIED);

                            patientsMap.put(patientID,pat);

                            Sample sam = new Sample();


                            // only have age at diagnosis
                            sam.setAge(NOT_SPECIFIED);

                            String date = result[i].getCollection_Date().split("T")[0];

                            sam.setCollectionDate(date);
                            sam.setCollectionEvent(NOT_SPECIFIED);
                            sam.setCollectionSite(result[i].getSpecimen_Site());
                            sam.setDiagnosis(pat.getDiagnosis());
                            sam.setGrade(result[i].getGrades());
                            sam.setGradeSystem("AJCC");
                            sam.setStage(result[i].getTumor_Stage());
                            sam.setStageSystem("AJCC");
                            sam.setModelID(id);
                            sam.setMonthsSinceCollection(NOT_SPECIFIED);
                            sam.setPatientID(patientID);
                            sam.setPrimarySite(result[i].getPrimary_Site());
                           

                             ArrayList<String> details = modelClincalDetails.get(clean(id));
                             String treatmentNaive = "Unknown";
                             if (details != null && details.get(1) != null && details.get(1).trim().length() > 0) {
                          //       pat.setHistory(details.get(0));
                                 treatmentNaive = details.get(1);
                             }

                            sam.setPriorTreatment(NOT_SPECIFIED);
                            sam.setTreated(NOT_SPECIFIED);
                            sam.setTreatmentNaiveAtCollection(treatmentNaive);
                            sam.setShareable("YES");
                            sam.setTumorType(result[i].getTumor_Type());
                            sam.setVirologyStatus(NOT_SPECIFIED);

                          
                            sam.setSampleID(id+"_PS");
                            samplesMap.put(sam.getSampleID(),sam);
                       //     System.out.println("model:"+id+"\t sampleID:"+id+"\t patient:"+patientID);


                            Model mo = new Model();

                            mo.setModelId(id);
                            mo.setEngraftmentSite(fixEngraftment(result[i].getEngraftmentSite()));
                            mo.setHostStrain("NSG(NOD scid gamma)");
                            mo.setHostStrainFull(NSG_OFFICIAL_NAME);
                            // default P1 per Carol's email shouldn't really be collecting this as model data
                            mo.setPassage("P1");

                            StringBuilder pubs = new StringBuilder();
                             for (PDXLink link : dao.getLinks(id)) {
                                 if(link.getCharacterization() == PDXContent.REFERENCE){
                                     if(link.getPubMedID()!= null && link.getPubMedID().trim().length()>0){
                                         if(pubs.length()>0){
                                             pubs.append(",");
                                         }
                                         pubs.append(link.getPubMedID());
                                     }
                                 }
                              }
                            mo.setPublications(pubs.toString());
                            String eType = HETEROTOPIC;
                            if(result[i].getEngraftmentSite()!= null && result[i].getEngraftmentSite().toLowerCase().contains("fat") &&
                                    sam.getCollectionSite().toLowerCase().contains("breast")){
                                eType = ORTHOTOPIC;
                            }

                            mo.setEngraftmentType(eType);
                            mo.setSampleType(clean(result[i].getSample_Type()));
                            mo.setSampleState(NOT_SPECIFIED);

                            modelsMap.put(id,mo);
     

                            Validation val = new Validation();
                            val.setModelId(id);
                            if (result[i].getModel_Status().indexOf("QC Complete") != -1) {

                                 val.setValidationTechnique(VALIDATION_PENDING);
                                 val.setDescription(VALIDATION_PENDING);
                            }else{
                                 val.setValidationTechnique(VALIDATION);
                                 val.setDescription(VALIDATION_DESCRIPTION);
                            }

                            val.setPassagesTested(NOT_SPECIFIED);
                            val.setValidationHostStrainFull(mo.getHostStrainFull());

                            validationsMap.put(id,val);

                            Sharing sharon = new Sharing();
                            sharon.setModelId(id);
                            sharon.setAccessibility("Academia and Industry");
                            sharon.setDatabaseUrl("http://tumor.informatics.jax.org/mtbwi/pdxDetails.do?modelID="+id);
                            sharon.setFormUrl("http://tumor.informatics.jax.org/mtbwi/pdxRequest.do?mice="+id);
                            sharon.setProject("JAX PDX");
                            sharon.setProviderName("The Jackson Laboratory");
                            sharon.setProviderAbbreviation("JAX");
                            sharon.setProviderType("Academia");
                            sharon.setEropdx_accessibility_modlaity("Not Applicable");
                            sharon.setEmail("micetech@jax.org");
                            sharon.setName("micetech");

                            sharingsMap.put(id,sharon);

                         }
                }
            }
            
            patientCache.addAll(patientsMap.values());
            sampleCache.addAll(samplesMap.values());
            modelCache.addAll(modelsMap.values());
            validationCache.addAll(validationsMap.values());
            sharingCache.addAll(sharingsMap.values());
            
            loadCytogenetics();
            
            variationModels = loadModelsWithVariationData();
            cnaModels = loadModelsWithCNAData();
            expModels = loadModelsWithEXPData();
          
            
            
            
            initialized = true;
            
           
         
        } catch (Exception e) {
            log.error("Error gettting PDX Info not updating chached values",e);
        }

        
    }
    
    
    private void loadCytogenetics(){
        PDXDAO dao = getPDXDAO();
        
        ArrayList<PDXGraphic> histologyImages = dao.getHistologyImages();
        
        for(PDXGraphic graphic: histologyImages){
        
            Cytogenetics cyto = new Cytogenetics();
            
//            private String sample_id;
//            private String sample_origin;
//            private String passage;
//            private String host_strain_nomenclature;
//            private String model_id;
//            private String marker_name;
//            private String marker_status;
//            private String essential_or_additional_marker;
//            private String technique_name;
//            private String protocol_file_name;
//            private String result_file_name;
//        

            String sample = NOT_SPECIFIED;
            String origin = "zenograft";
            String passage = NOT_SPECIFIED;
            if(graphic.getDescription().split(" ") != null){
                String[] desc = graphic.getDescription().split(" ");
                
                if(desc[0].equals("Patient")){
                    origin = "patient";
                    passage = "";
                    sample = graphic.getModelID()+"_PT";
                }
                if(desc[0].length()==2 && desc[0].startsWith("P")){
                    passage = desc[0];

                }
                
                System.out.println(graphic.getDescription());
                System.out.println("\t"+sample);
                System.out.println("\t"+origin);
                System.out.println("\t"+passage);
            }

            cyto.setSample_id(sample);
            cyto.setSample_origin(origin);
            cyto.setPassage(passage);
            cyto.setHost_strain_nomenclature(NSG_OFFICIAL_NAME);
            cyto.setModel_id(graphic.getModelID());
            cyto.setMarker_name("");
            cyto.setMarker_status("");
            cyto.setEssential_or_additional_marker("");
            cyto.setTechnique_name("");
            cyto.setProtocol_file_name("");
            cyto.setResult_file_name(GRAPHIC_URL+graphic.getFileName());
            
            cytoCache.add(cyto);
                    
        }
    }
    
    
    private String loadModelsWithVariationData(){
        
        StringBuilder modelIDs = new StringBuilder();
        
        for(Model m : modelCache){
            try{
             String params = "?keepnulls=yes&model=" + m.getModelId() + "&limit=5&filter=yes";
             JSONObject job = new JSONObject(getJSON(VARIANT_URL + params, null));
             log.info("checking for variation data for "+m.getModelId());
                if(job.has("total_rows")){
                    int total = (Integer) job.get("total_rows");
                    if(total >0){
                        modelIDs.append(m.getModelId()).append("\n");
                    }
                }
                
            }catch(Exception e){
                log.error("Unable to check for variation data for model "+m.getModelId(),e);
            }
        }
        
        return modelIDs.toString();
    }
    
    
    private String loadModelsWithCNAData(){
        
        StringBuilder modelIDs = new StringBuilder();
        
        for(Model m : modelCache){
            try{
             String params =  m.getModelId() + "&limit=5";
             JSONObject job = new JSONObject(getJSON(CNA_URL + params, null));
             log.info("checking for cna data for "+m.getModelId());
                if(job.has("count")){
                    int total = (Integer) job.get("count");
                    if(total >0){
                        modelIDs.append(m.getModelId()).append("\n");
                    }
                }
                
            }catch(Exception e){
                log.error("Unable to check for CNA data for model "+m.getModelId(),e);
            }
        }
        
        return modelIDs.toString();
    }
    
    
     private String loadModelsWithEXPData(){
        
        StringBuilder modelIDs = new StringBuilder();
        
        for(Model m : modelCache){
            try{
             String params =  m.getModelId() + "&limit=5";
             JSONObject job = new JSONObject(getJSON(EXP_URL + params, null));
             log.info("checking for Expression data for "+m.getModelId());
                if(job.has("count")){
                    int total = (Integer) job.get("count");
                    if(total >0){
                        modelIDs.append(m.getModelId()).append("\n");
                    }
                }
                
            }catch(Exception e){
                log.error("Unable to check for expression data for model "+m.getModelId(),e);
            }
        }
        
        return modelIDs.toString();
    }
    
    
     // Return a map of patient details to model ID's this is used for the model comparision page
    private void getClinicalDetails() {
       

        try {

            MTB_wsStub stub = getStub();

            GetPDXPatientClinicalReports_sessionless soapRequest
                    = GetPDXPatientClinicalReports_sessionless.class.newInstance();

            soapRequest.setPwd(password);
            soapRequest.setUser(userName);

            PDXPatientClinicalReport[] result
                    = stub.getPDXPatientClinicalReports_sessionless(soapRequest).getGetPDXPatientClinicalReports_sessionlessResult().getPDXPatientClinicalReport();

            HashMap<String,ArrayList<String>> patientModels = new HashMap();
            if (result.length > 0) {

                for (int i = 0; i < result.length; i++) {

                    ArrayList<String> details = new ArrayList<String>();

                    details.add("Current smoker:"+result[i].getCurrent_Smoker()+", Former smoker:"+result[i].getFormer_Smoker()+", Alcohol use:"+result[i].getAlcohol_Use());
                    details.add(result[i].getTreatment_Naive());
                    

                            
                    modelClincalDetails.put(clean(result[i].getModel()), details);
                    
                    String patientID = result[i].getParticipant_ID();
                    if(patientID == null || patientID.trim().length()<1){
                        patientID  = result[i].getModel();
                    }
                    modelPatients.put(result[i].getModel(),patientID);
          
                    
                    if(patientModels.containsKey(patientID)){
                        patientModels.get(patientID).add(result[i].getModel());
                    }else{
                        ArrayList<String> models = new ArrayList();
                        models.add(result[i].getModel());
                        patientModels.put(patientID,models);
                              
                    }
                         
 
                }
            }
                   
        } catch (Exception e) {
            log.error("Error getting PDX Clinical Details",e);
            e.printStackTrace();
        }

        

    }

  

    
//    private ArrayList<String> getModelSamples(String id){
//        ArrayList<String> samples = new ArrayList();
//        HashMap<String,String> deDupe = new HashMap();
//        String url ="http://pdxdata.jax.org/api/inventory?model="+id;
//        try{
//            JSONObject job = new JSONObject(getJSON(url));
//            JSONArray jarray = job.getJSONArray("data");
//            for(int i = 0; i < jarray.length(); i++){
//                JSONObject j = jarray.getJSONObject(i);
//                deDupe.put(j.getString("sample_name"),j.getString("sample_name"));
//            }
//            
//            for(String sampleID : deDupe.keySet()){
//                samples.add(sampleID);
//            }
//                    
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return samples;
//    }

    private String getDiagnosis(String initial, String clinical){
        String diagnosis = initial;
        if(initial == null || initial.trim().length() == 0){
            diagnosis = clinical;
        }
        return diagnosis;
    }
    
    private String clean(String in) {
        if (in != null) {
            in =  in.replaceAll("\"", "").replaceAll("'", "").replaceAll("\\p{C}", "").replaceAll("&#x.{1,4};", " ");
        }
        if (in.trim().length() == 0) {
            in = NOT_SPECIFIED;
        }
        return in;
    }

    // if engraftment site is not provided it is ''
    private String fixEngraftment(String in) {
        
    //    System.out.println("Engraftment Site:"+in);
        
        if (in == null || in.trim().length() == 0) {
            // formerly sub q but not anymore
            return NOT_SPECIFIED;
        } else {
            return in;
        }
        
        
    }
    
     private String fixStrain(String strain){
 //     System.out.print(strain);
        if(strain != null && strain.startsWith("NSG")){
            strain =NSG_OFFICIAL_NAME;
        }
       
        return strain;
    }
     
     
       private String getField(JSONObject job, String field) {
        String val = "";
        try {

            val = job.get(field).toString();
            val = val.replaceAll(",", ";");
            val = val.replaceAll("'", "");

        } catch (Exception e) {
            // if the field value is null it may not be included as a field in the json so skip it
            //  log.error("Unable to get value for " + field + " from json object");
        }

        return val;
    }

    private PDXDAO getPDXDAO() {
        PDXDAO pdxDAO = PDXDAO.getInstance();
        pdxDAO.setConnectionInfo("org.postgresql.Driver", mtbDB, mtbPassword, mtbUser);
        return pdxDAO;
    }

    // private utility methods
    private static MTB_wsStub getStub() throws Exception {

        MTB_wsStub stub = null;
        try {
            stub = new MTB_wsStub();

            //stub._getServiceClient().getOptions().setSoapVersionURI(
            //        SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            //stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(300000);
        } catch (Error e) {
            e.printStackTrace();
            throw new Exception(e.fillInStackTrace());
        }
        return stub;

    }

    
    
     private String getJSON(String uri){
        return getJSON(uri,null);
    }
    private String getJSON(String uri, String json) {

        boolean post = true;
        if (json == null || json.length() == 0) {
            post = false;
        }

        String responseSingle = "";
        StringBuffer response = new StringBuffer();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(uri);
            connection
                    = (HttpURLConnection) url.openConnection();
            if (post) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true); // sending stuff
            } else {
                connection.setRequestMethod("GET");
            }

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoInput(true); //we want a response
            connection.setUseCaches(false);

            if (post) {
                OutputStream out = connection.getOutputStream();
                try {

                    OutputStreamWriter wr = new OutputStreamWriter(out);
                    wr.write(json.toString());
                    wr.flush();
                    wr.close();
                } catch (IOException e) {

                    System.out.println("Error writing to webservice " + uri);
                    e.printStackTrace();
                    log.error(e);
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }

            // Open a stream which can read the server response
            InputStream in = connection.getInputStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                while ((responseSingle = rd.readLine()) != null) {
                    response.append(responseSingle);
                }
                rd.close(); //close the reader

            } catch (IOException e) {

                System.out.println("Error reading webservice " + uri);
                e.printStackTrace();
                log.error(e);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error with webservice " + uri);
            e.printStackTrace();
            log.error(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();

    }
}
