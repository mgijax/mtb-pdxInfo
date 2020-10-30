/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jax.mgi.mtb.pdxinfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author sbn
 */
public class Init extends HttpServlet {
    
    public void init(ServletConfig config)
            throws ServletException {
        // Initialization code...
        
        System.out.println("Initializing TSV info");
        TSVPDXInfoUtil util = new TSVPDXInfoUtil();
        util.getModels();
        
        System.out.println("TSV info initialized.");
    }
    
}
