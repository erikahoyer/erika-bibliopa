/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoyer.erika.pa.model;

/**
 *
 * @author Erika
 */

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
    private DataSource ds;
    
    public BaseDAO(){
        try {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                System.out.println("[DataBaseDAO.constructor] Failed in InitialContext.");
            } else {
                ds = (DataSource) cxt.lookup("java:comp/env/jdbc/referenciasbibliograficas");
            }
        } catch (Exception e) {
            System.out.println("[DataBaseDAO.constructor] Exception: " + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        Connection result = null;
        try{
            if(ds != null) result = ds.getConnection();
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return result;
    }
}
