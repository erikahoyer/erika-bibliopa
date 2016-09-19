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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditHandler implements InterfaceBiblioHandler{
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        String bt = req.getParameter("botaoSubmit");
        
        BiblioModel entryFromEditPage = new BiblioModel();
        
        entryFromEditPage.setSerialNo(req.getParameter("serialNo"));
        entryFromEditPage.setTitle(req.getParameter("Title"));
        entryFromEditPage.setAuthor(req.getParameter("Author"));
        
        switch(bt){
            case "Inserir": {
                req.getSession().setAttribute("Author", entryFromEditPage.getAuthor());
                req.getSession().setAttribute("Title", entryFromEditPage.getTitle());
                if((new DataBaseDAO()).doCreate(entryFromEditPage)){
                    req.getSession().setAttribute("serialNo", entryFromEditPage.getSerialNo());
                }
                break;
            }
            case "Atualizar":{
                req.getSession().setAttribute("serialNo", entryFromEditPage.getSerialNo());
                req.getSession().setAttribute("Title", entryFromEditPage.getTitle());
                req.getSession().setAttribute("Author", entryFromEditPage.getAuthor());
                
                if((new DataBaseDAO()).doUpdate(entryFromEditPage)){
                    req.getSession().setAttribute("Dialog", "Atualizado!");
                } else{
                    req.getSession().setAttribute("Dialog", "Erro ao atualizar entrada!");
                }
                break;
            }
            case "Excluir":{
                if ((new DataBaseDAO()).doDelete(entryFromEditPage)) {
                    req.getSession().setAttribute("serialNo", "");
                    req.getSession().setAttribute("Title", "");
                    req.getSession().setAttribute("Author", "");
                    req.getSession().setAttribute("Dialog", "Excluído!");
                } else {
                    req.getSession().setAttribute("Dialog", "Falha na operação !");
                }
                break;
            }
            case "Limpar":{
                req.getSession().setAttribute("serialNo", "");
                req.getSession().setAttribute("Title", "");
                req.getSession().setAttribute("Author", "");
                req.getSession().setAttribute("Dialog", "");
                break;
            }
        }
        
        return "edit.jsp";
    }
}
