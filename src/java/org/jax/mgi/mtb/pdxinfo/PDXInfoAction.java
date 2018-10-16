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
public class PDXInfoAction extends Action {

    private final static Logger log
            = Logger.getLogger(PDXInfoAction.class.getName());

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PDXInfoUtil pdxInfoUtil = new PDXInfoUtil();

        response.setContentType("application/json");

        if (request.getParameter("allModels") != null) {
            response.getWriter().write(pdxInfoUtil.getJSONPDXInfo());
        }

        String modelID = request.getParameter("modelVariation");
        if (modelID != null) {
            response.getWriter().write(pdxInfoUtil.getVariationJSON(modelID));
        }

        modelID = request.getParameter("modelHistology");
        if (modelID != null) {
            response.getWriter().write(pdxInfoUtil.getModelHistology(modelID));
        }
        
        modelID = request.getParameter("modelCNV");
        if (modelID != null) {
            response.getWriter().write(pdxInfoUtil.getModelCNV(modelID));
        }
        
        modelID = request.getParameter("modelExpression");
        if (modelID != null) {
            response.getWriter().write(pdxInfoUtil.getModelExpression(modelID));
        }

        response.flushBuffer();

        return null;
    }
}
