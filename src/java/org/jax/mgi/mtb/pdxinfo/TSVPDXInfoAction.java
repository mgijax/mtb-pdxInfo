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

        
        String modelID = request.getParameter("modelVariation");
        if (modelID != null) {
            response.getWriter().write(util.getVariationTSV(modelID));
        }
        
        else if(request.getParameter("patients")!=null){
            response.getWriter().write(util.getPatients());
        }
        
        else if(request.getParameter("samples")!=null){
            response.getWriter().write(util.getSamples());
        }
        
        else if(request.getParameter("models")!=null){
            response.getWriter().write(util.getModels());
        }
        
        else if(request.getParameter("validation")!=null){
            response.getWriter().write(util.getValidations());
        }
        
        else if(request.getParameter("sharing")!=null){
            response.getWriter().write(util.getSharing());
        }
        
        else if(request.getParameter("modelsWithVariationData")!=null){
            response.getWriter().write(util.getModelsWithVariationData());
        }
        
      

        response.flushBuffer();

        return null;
    }
}