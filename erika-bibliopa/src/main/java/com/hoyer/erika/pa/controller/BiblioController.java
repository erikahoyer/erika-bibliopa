/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoyer.erika.pa.controller;

/**
 *
 * @author Erika
 */

import com.hoyer.erika.pa.handlers.InterfaceBiblioHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BiblioController extends HttpServlet{
    protected void processRequest(
        HttpServletRequest req,
        HttpServletResponse res
    ) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        res.setCharacterEncoding("UTF8");
        InterfaceBiblioHandler pagehandler;
        
        try {
            String reqHandler = req.getParameter("whichHandler");
            System.out.println("REQ = " + req.toString());
            System.out.println("reqHandler = " + reqHandler);
            pagehandler = (InterfaceBiblioHandler) Class.forName(reqHandler).newInstance();
            String resHandler = pagehandler.process(req, res);
            req.getRequestDispatcher(resHandler).forward(req, res);
            
        } catch (Exception e) {
            req.setAttribute("CONTROLER_EXCEPTION: ", e);
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
