/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoyer.erika.pa.model;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erika
 */
public class DataBaseDAO extends BaseDAO {
    
    // Insert new book
    public boolean doCreate(BiblioModel entry){
        try {
            Connection conn = getConnection();
            
            // SQL Query
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO referencias (titulo, autoria) VALUES(?,?) RETURNING serialno;"
            );
            
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getAuthor());
            ResultSet rst = pstmt.executeQuery();
            rst.next();
            entry.setSerialNo(rst.getString("serialno"));
            
            // Close
            conn.close();
            
        } catch(Exception e){
            e.printStackTrace(System.out);
            return false;
        }
        return true;
    }
    
    // Update book entry
    public boolean doUpdate(BiblioModel entry){
        try {
            Connection conn = getConnection();
            
            // SQL Query
            PreparedStatement pstmt = conn.prepareStatement(
               "UPDATE referencias SET titulo=? WHERE serialno=?;"
            );
            pstmt.setString(1, entry.getTitle());
            pstmt.setInt(2, Integer.parseInt(entry.getSerialNo()));
            pstmt.executeUpdate();
            
            // Close
            conn.close();
            
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(System.out);
            return false;
        }
        
        return true;
    }
    
    // Delete a book
    public boolean doDelete(BiblioModel entry){
        try {
            Connection conn = getConnection();
            
            // SQL Query
            PreparedStatement pstmt = conn.prepareStatement(
               "DELETE FROM referencias WHERE serialno=?;"
            );
            
            pstmt.setInt(1, (new Integer(entry.getSerialNo())));
            pstmt.execute();
            
            conn.close();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(System.out);
            return false;
        }
        
        return true;
    }
    
    // Search array of books by title
    public ArrayList<BiblioModel> doLookForTitle(BiblioModel book){
        BiblioModel tmpBook;
        ArrayList<BiblioModel> searchResult = new ArrayList();
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
               "SELECT * FROM referencias WHERE LOWER(titulo) LIKE LOWER(?);"
            );
            String name = book.getTitle();
            
            if(!"".equals(book.getTitle())){
                pstmt.setString(1, '%' + name + '%');
            } else {
                pstmt.setString(1, "%");
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                tmpBook = new BiblioModel();
                
                // Get result data
                tmpBook.setSerialNo(rs.getString("serialno"));
                tmpBook.setTitle(rs.getString("titulo"));
                tmpBook.setAuthor(rs.getString("autoria"));
                
                // Fill the result array
                searchResult.add(tmpBook);
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return searchResult;
    }
    
    // Search array of books by author
    public ArrayList<BiblioModel> doLookForAuthor(BiblioModel book){
        BiblioModel tmpBook;
        ArrayList<BiblioModel> searchResult = new ArrayList();
                
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
               "SELECT * FROM referencias WHERE LOWER(autoria) LIKE LOWER(?);"
            );
            String author = book.getAuthor();
            if(!"".equals(author)){
                pstmt.setString(1, '%' + author + '%');
            } else {
                pstmt.setString(1, "%");
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                tmpBook = new BiblioModel();
                
                // Get data from sql query result
                tmpBook.setTitle(rs.getString("titulo"));
                tmpBook.setAuthor(rs.getString("autoria"));
                tmpBook.setSerialNo(rs.getString("serialno"));
                
                // Fill the result array
                searchResult.add(tmpBook);
            }
            
            // Close
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return searchResult;
    }
}
