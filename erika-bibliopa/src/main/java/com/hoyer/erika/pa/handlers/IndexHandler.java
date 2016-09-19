/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoyer.erika.pa.handlers;

/**
 *
 * @author Erika
 */

import com.hoyer.erika.pa.model.BiblioModel;
import com.hoyer.erika.pa.model.DataBaseDAO;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexHandler implements InterfaceBiblioHandler{
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        String query = "";
        
        ArrayList<BiblioModel> result = new ArrayList();
        BiblioModel entryObject = new BiblioModel();
        
        String whatToSearch = req.getParameter("searchType");
        String bt = req.getParameter("botaoSubmit");
        
        switch(bt){
            case "Buscar":{
                switch(whatToSearch){
                    case "Title":{
                        entryObject.setTitle(req.getParameter("barraBusca"));
                        query = entryObject.getTitle();
                        result = (new DataBaseDAO()).doLookForTitle(entryObject);
                        break;
                    }
                    case "Author":{
                        entryObject.setAuthor(req.getParameter("barraBusca"));
                        query = entryObject.getAuthor();
                        result = (new DataBaseDAO()).doLookForAuthor(entryObject);
                        break;
                    }
                }
                req.getSession().setAttribute("SearchField", query);
                req.getSession().setAttribute("searchResult", result);
                break;
            }
            case "Limpar":{
                req.getSession().setAttribute("barraBusca", "");
                req.getSession().setAttribute("searchResult", "");
                break;
            }
        }
        return "index.jsp";
    }
}