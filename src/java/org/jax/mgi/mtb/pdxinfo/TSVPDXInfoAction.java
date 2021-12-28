/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jax.mgi.mtb.pdxinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author sbn
 */
public class TSVPDXInfoAction extends Action {

    private final static Logger log
            = Logger.getLogger(TSVPDXInfoAction.class.getName());
    
    private final static TSVPDXInfoUtil util = new TSVPDXInfoUtil();

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        TSVPDXInfoUtil util = new TSVPDXInfoUtil();
        
        response.setContentType("text/plain");

        String modelID = request.getParameter("model_variation");
        
        if (modelID != null) {
            response.getWriter().write(util.getVariationTSV(modelID));
        }
        
        else if(request.getParameter("patient")!=null){
            response.getWriter().write(util.getPatients());
        }
        
        else if(request.getParameter("patient_sample")!=null){
            response.getWriter().write(util.getSamples());
        }
        
        else if(request.getParameter("pdx_model")!=null){
            response.getWriter().write(util.getModels());
        }
        
        else if(request.getParameter("model_validation")!=null){
            response.getWriter().write(util.getValidations());
        }
        
        else if(request.getParameter("sharing")!=null){
            response.getWriter().write(util.getSharing());
        }
        
        else if(request.getParameter("modelsWithVariationData")!=null){
            response.getWriter().write(util.getModelsWithVariationData());
        }
        
        else if(request.getParameter("cna")!=null){
            modelID = request.getParameter("cna");
            response.getWriter().write(util.getCNA(modelID));
        }
        
         else if(request.getParameter("modelsWithCNAData")!=null){
             
            response.getWriter().write(util.getModelsWithCNAData());
        }
        
        else if(request.getParameter("exp")!=null){
            modelID = request.getParameter("exp");
            response.getWriter().write(util.getExpression(modelID));
        }
        
        else if(request.getParameter("modelsWithExpData")!=null){
             
            response.getWriter().write(util.getModelsWithExpData());
        }
        
        else if(request.getParameter("cytogenetics")!=null){
              modelID = request.getParameter("cytogenetics");
            response.getWriter().write(util.getCytogenetics(modelID));
        }
        
        else if(request.getParameter("modelsWithCytogenetics")!=null){
              
            response.getWriter().write(util.getModelsWithCytogenetics());
        }
        
        
      

        response.flushBuffer();

        return null;
    }
}
