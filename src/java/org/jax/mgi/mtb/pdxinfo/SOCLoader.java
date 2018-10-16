/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jax.mgi.mtb.pdxinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;


/**
 *
 * @author sbn
 * Query the SOC sqllite database for a list of PDX models that have SOC graphs.
 */
public class SOCLoader {
    
      private static final Logger log
            = Logger.getLogger(SOCLoader.class.getName());

     
      
      /**
       * For all models with RECIST data return a mapping modelId -> list of pairs of drug, response
       * @ return HashMap<String,ArrayList<ArrayList<String>>>
      */
       public static HashMap<String,ArrayList<ArrayList<String>>> getRECISTMap(String path) {

        
        HashMap<String,ArrayList<ArrayList<String>>> modelsMap = new HashMap();
        try {
            
            Connection liteCon = null;
            Class.forName("org.sqlite.JDBC");
            liteCon = DriverManager.getConnection("jdbc:sqlite:/" + path);
            
            String sql = "SELECT distinct s.model_TM, t.group_name, g.drug, g.recist, t.test_material_amount, t.administration_route_units, t.dose_activity  "+
                    " FROM groups g, studies s, treatments t"+
                    " WHERE g.study_number = s.study_number and g.study_number = t.study_number AND t.group_name = g.group_name "+
                    " ORDER BY s.model_TM, t.group_name";
            
         //   sql = "Select model_TM from studies";
            PreparedStatement stmt = liteCon.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            String id;
            String drug;
            while (rs.next()) {
                id = rs.getString(1);
                ArrayList<String> data = new ArrayList();
                drug = rs.getString(3);
                if("D5W".equals(drug)){
                    drug = "DSW (control)";
                }
                String group1 = rs.getString(2);
                if(rs.getString(3).contains("+")){
                    String dose1 = rs.getString(5);
                    String units1 = rs.getString(6);
                    String activity1 = rs.getString(7);
                    rs.next();
                    if(rs.getString(2).equals(group1) && drug.equals(rs.getString(3))){
                       
                        data.add(drug);
                        data.add(rs.getString(4));
                        String[] drugs = drug.split("\\+");
                        String dose2 = rs.getString(5);
                        String units2 = rs.getString(6);
                        String activity2 = rs.getString(7);
                        if(activity1.toLowerCase().contains(drugs[0].trim().toLowerCase())){
                            data.add(dose1+units1+";"+dose2+units2);
                            
                        }else if(activity1.toLowerCase().contains(drugs[1].trim().toLowerCase())){
                            data.add(dose2+units2+";"+dose1+units1);
                            
                        }else{
                            //bad
                            log.error("bad"+id+" "+drug+" "+activity1);
                        }
                        
                   
                    }else{
                        //this is bad
                        log.error("also bad "+id+" "+drug+" "+activity1);
                    }
                }else{
                    data.add(drug);
                    data.add(rs.getString(4));//response
                    data.add(rs.getString(5)+rs.getString(6));//dose units
                }
                
                if(modelsMap.containsKey(id)){
                    modelsMap.get(id).add(data);
                }else{
                    ArrayList<ArrayList<String>> list = new ArrayList();
                    list.add(data);
                    modelsMap.put(id,list);
                }
               
               
                
            }
                
            
        } catch (SQLException e) {
            log.error("Unable to load SOC recist model data",e);
            e.printStackTrace();
        } catch (Exception e){
            log.error("Unable to load SOC recist model data",e);
        }

        return modelsMap;
    }
}
