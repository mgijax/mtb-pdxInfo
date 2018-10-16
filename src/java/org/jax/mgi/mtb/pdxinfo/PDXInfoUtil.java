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
import java.io.File;
import java.io.FileReader;
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
//import org.apache.axiom.soap.SOAP11Constants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXComment;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXDAO;
import org.jax.mgi.mtb.dao.custom.mtb.pdx.PDXGraphic;

/**
 * Access to data from ELIMS web services
 *
 * @author sbn
 */
public class PDXInfoUtil {

    private static String userName;
    private static String password;

    private static String mtbUser;
    private static String mtbPassword;
    private static String mtbDB;

    private static String baseURI = "http://pdxdata.jax.org/api/";

    private static String VARIANTS = baseURI + "variants";

    private static String JSON_PDX_INFO;

    private static final String graphicURL = "http://tumor.informatics.jax.org/mtbwi/pdxDetailsTabs.do?tab=graphicDetails&contentKey=";

    private final static Logger log
            = Logger.getLogger(PDXInfoUtil.class.getName());

    public PDXInfoUtil() {
        try{
           
            Properties p = new Properties();
            p.load(PDXInfoUtil.class.getClassLoader().getResource("pdxInfo.properties").openStream());
            userName = p.getProperty("elimsuser");
            password = p.getProperty("elimspassword");
            mtbUser = p.getProperty("mtbuser");
            mtbPassword = p.getProperty("mtbpassword");
            mtbDB = p.getProperty("mtbDB");
        }catch(Exception e){
            log.error("can't load properties file",e);
        }
    }

    public String getModelHistology(String modelID) {

        //histology data is characterized by _pdxcharacterization_key 1,9
        PDXDAO pdxDAO = getPDXDAO();

        ArrayList<PDXComment> comments = pdxDAO.getComments(modelID);
        ArrayList<PDXGraphic> graphics = pdxDAO.getGraphics(modelID);

        StringBuilder sb = new StringBuilder("{\"pdxHistology\":{\"Model ID\":\"" + modelID + "\",\"Comment\":\"");

        for (PDXComment comment : comments) {
            if (comment.getCharacterization() == 1 || comment.getCharacterization() == 9) {
                sb.append(comment.getComment());
            }
        }

        sb.append("\",");

        sb.append("\"Graphics\":[ ");
        for (PDXGraphic graphic : graphics) {
            if (graphic.getCharacterization() == 1 || graphic.getCharacterization() == 9) {
                sb.append("{\"Description\":\"").append(graphic.getDescription().replaceAll("\"", "'")).append("\",");
                sb.append("\"URL\":\"" + graphicURL + graphic.getContentKey() + "\"},");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "]}}");

        return sb.toString();
    }

    public String getModelCNV(String modelID) {
        PDXDAO pdxDao = getPDXDAO();
        String cnvData = pdxDao.getModelCNVJSON(modelID);

        return cnvData;
    }

    public String getModelExpression(String modelID) {
        PDXDAO pdxDao = getPDXDAO();
        String expressionData = pdxDao.getModelExpressionJSON(modelID);

        return expressionData;
    }

    // keep a cached copy of the PDXInfo JSON but use a new one if available
    // used by the EBI PDXInfo project for loading JAX PDX data
    public String getJSONPDXInfo() {
        String newData = getPDXInfo();
        if (newData != null && newData.trim().length() != 80) {
            JSON_PDX_INFO = newData;
        } else {
            System.out.println("unable to load json pdx info.");
        }
        return JSON_PDX_INFO;

    }

    public String getVariationJSON(String model) {

        int limit = 150000;
        int skip = 0;
        boolean done = false;

        StringBuilder result = new StringBuilder("{\"variation\":[ ");

        try {
            JSONArray array;

            do {

                String params = "?keepnulls=yes&model=" + model + "&skip=" + skip + "&limit=" + limit + "&filter=yes";
                JSONObject job = new JSONObject(getJSON(VARIANTS + params, null));

                int total = (Integer) job.get("total_rows");
                done = (total < skip);

                array = (JSONArray) job.get("data");

                boolean includeWholeExome = true;

                for (int i = 0; i < array.length(); i++) {

                    if (!"Whole Exome".equals(getField(array.getJSONObject(i), "platform")) || includeWholeExome) {

                        result.append("{\"model id\":\"").append(getField(array.getJSONObject(i), "model_id")).append("\",");
                        result.append("\"sample\":\"").append(getField(array.getJSONObject(i), "sample_name")).append("\",");
                        result.append("\"gene symbol\":\"").append(getField(array.getJSONObject(i), "gene_symbol")).append("\",");
                        result.append("\"platform\":\"").append(getField(array.getJSONObject(i), "platform")).append("\",");
                        result.append("\"chromosome\":\"").append(getField(array.getJSONObject(i), "chromosome")).append("\",");
                        result.append("\"seq position\":\"").append(getField(array.getJSONObject(i), "seq_position")).append("\",");
                        result.append("\"ref allele\":\"").append(getField(array.getJSONObject(i), "ref_allele")).append("\",");
                        result.append("\"alt allele\":\"").append(getField(array.getJSONObject(i), "alt_allele")).append("\",");
                        result.append("\"consequence\":\"").append(getField(array.getJSONObject(i), "consequence")).append("\",");
                        result.append("\"amino acid change\":\"").append(getField(array.getJSONObject(i), "amino_acid_change")).append("\",");
                        result.append("\"rs variants\":\"").append(getField(array.getJSONObject(i), "rs_variants")).append("\",");
                        result.append("\"read depth\":\"").append(getField(array.getJSONObject(i), "read_depth")).append("\",");
                        result.append("\"allele frequency\":\"").append(getField(array.getJSONObject(i), "allele_frequency")).append("\",");

                        result.append("\"passage num\":\"").append(getField(array.getJSONObject(i), "passage_num")).append("\",");
                        result.append("\"gene id\":\"").append(getField(array.getJSONObject(i), "gene_id")).append("\"");// will need a comma if not last field

                        result.append("},");
                    }
                }

                skip += limit;

            } while (array.length() > 0 || !done);

            result.replace(result.length() - 1, result.capacity(), "]}");

        } catch (Exception e) {
            System.out.println("Error loading PDX info.");
            e.printStackTrace();
            log.error("Error loading PDX Info", e);
            result.append("]}");
        }

        String resultStr = result.toString().replaceAll("null", " ");

        return resultStr;
    }

    // Return a map of patient details to model ID's this is used for the model comparision page
    private HashMap<String, ArrayList<String>> getPDXClinicalDetails() {
        HashMap<String, ArrayList<String>> modelDetails = new HashMap<String, ArrayList<String>>();

        try {

            MTB_wsStub stub = getStub();

            GetPDXPatientClinicalReports_sessionless soapRequest
                    = GetPDXPatientClinicalReports_sessionless.class.newInstance();

            soapRequest.setPwd(password);
            soapRequest.setUser(userName);

            PDXPatientClinicalReport[] result
                    = stub.getPDXPatientClinicalReports_sessionless(soapRequest).getGetPDXPatientClinicalReports_sessionlessResult().getPDXPatientClinicalReport();

            if (result.length > 0) {

                for (int i = 0; i < result.length; i++) {

                    ArrayList<String> details = new ArrayList<String>();

                    details.add(result[i].getCurrent_Smoker());
                    details.add(result[i].getFormer_Smoker());
                    details.add(result[i].getTreatment_Naive());

                    modelDetails.put(clean(result[i].getModel()), details);

                }
            }
        } catch (Exception e) {
            System.out.println("Error getting PDX Clinical Details");
            e.printStackTrace();
            log.error("Error getting PDX Clinical Details", e);
        }

        return modelDetails;

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

    // JSON for the PDXInfo
    public String getPDXInfo() {
        StringBuilder report = new StringBuilder();
        try {

            HashMap<String, ArrayList<String>> detailsMap = getPDXClinicalDetails();

            HashMap<String, ArrayList<ArrayList<String>>> socMap = SOCLoader.getRECISTMap(socPath);

            MTB_wsStub stub = getStub();

            GetPDXStatusReport_sessionless soapRequest
                    = GetPDXStatusReport_sessionless.class.newInstance();

            soapRequest.setPwd(password);
            soapRequest.setUser(userName);

            Pdx_model_status[] result = stub.getPDXStatusReport_sessionless(soapRequest).getGetPDXStatusReport_sessionlessResult().getPdx_model_status();

            String columns[] = {"Model ID", "Patient ID", "Gender", "Age", "Race", "Ethnicity", "Specimen Site", "Primary Site", "Initial Diagnosis", "Clinical Diagnosis",
                "Tumor Type", "Grades", "Tumor Stage", "Sample Type", "Strain", "Mouse Sex", "Engraftment Site","Model Tag"};

            if (result.length > 0) {
                report.append("{\"pdxInfo\":[");
                for (int i = 0; i < result.length; i++) {

                    String id = result[i].getIdentifier();
                    try {
                        int intID = new Integer(id).intValue();
                        id = ("TM" + String.format("%05d", intID));
                    } catch (NumberFormatException e) {
                        // this will happen for J##### ids which is ok
                    }

                    int j = 0;

                    String qc = "Pending";
                    if (result[i].getModel_Status().indexOf("Available") != -1) {
                        if (result[i].getModel_Status().indexOf("QC Complete") != -1) {
                            qc = "Complete";
                        }
                        report.append("{");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(id)).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append("\"JAXPT" + i + "\"").append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getGender())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getPatient_Age())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getRace())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getEthnicity())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getSpecimen_Site())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getPrimary_Site())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getInitial_Diagnosis())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getClinical_Diagnosis())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getTumor_Type())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getGrades())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getTumor_Stage())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getSample_Type())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getStrain())).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getMouseSex())).append(",\n");
                        report.append("\"").append("QC").append("\":\"").append(qc).append("\",\n");
                        if (socMap.containsKey(id)) {
                            report.append("\"Treatments\":[");
                            boolean first = true;
                            for (ArrayList<String> vals : socMap.get(id)) {
                                if (!first) {
                                    report.append(",");
                                }
                                first = false;
                                report.append("{\"Drug\":\"").append(vals.get(0)).append("\",\"Response\":\"").append(vals.get(1)).append("\",");
                                report.append("\"Dose\":\"").append(vals.get(2)).append("\"}");
                            }
                            report.append("],\n");
                        }

                        ArrayList<String> details = detailsMap.get(clean(id));
                        String treatmentNaive = "Unknown";
                        if (details != null && details.get(2) != null && details.get(2).trim().length() > 0) {
                            treatmentNaive = details.get(2);
                        }
                        report.append("\"Treatment Naive\":\"").append(treatmentNaive).append("\",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(fixEngraftment(result[i].getEngraftmentSite()))).append(",\n");
                        report.append("\"").append(columns[j++]).append("\":").append(clean(result[i].getModelTags())).append("},\n");
                    }
                }
            }
            report.replace(report.length() - 2, report.capacity(), "]}");
        } catch (Exception e) {
            System.out.println("Error gettting PDX Info as JSON");
            e.printStackTrace();
        }

        return report.toString();
    }

    private String clean(String in) {
        if (in != null) {
            in = "\"" + in.replaceAll("\"", "").replaceAll("'", "").replaceAll("\\p{C}", "").replaceAll("&#x.{1,4};", " ") + "\"";
        }
        if (in.trim().length() == 0) {
            in = "Not Specified";
        }
        return in;
    }

    // if engraftment site is not provided it is ''
    private String fixEngraftment(String in) {
        if (in == null || in.trim().length() == 0) {
            // formerly sub q but not anymore
            return "";
        } else {
            return in;
        }
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

}
