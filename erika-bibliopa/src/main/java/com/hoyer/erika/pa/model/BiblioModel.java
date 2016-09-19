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

import java.io.Serializable;

public class BiblioModel implements Serializable{
    private String title;
    private String author;
    private String serialno;
    
    // Getters of the class attributes
    public String getTitle(){
        return this.title;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getSerialNo(){
        return this.serialno;
    }
    
    // Setters of the class attributes
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public void setSerialNo(String serialno){
        this.serialno = serialno;
    }
}