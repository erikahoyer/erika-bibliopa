/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoyer.erika.pa.tags;

/**
 *
 * @author Erika
 */

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import static javax.servlet.jsp.tagext.Tag.SKIP_PAGE;

public class BiblioTags extends BodyTagSupport {
    private String whichPage;
    private String whichHandler;
    
    public BiblioTags(){
        super();
    }
    
    // Setters of private attributes
    public void setwhichPage(String whichPage) {
        this.whichPage = whichPage;
    }

    public void setwhichHandler(String whichHandler) {
        this.whichHandler = whichHandler;
    }
    
    // Getters of private attributes
    public String getwhichPage(){
        return this.whichPage;
    }
    
    public String getwhichHandler(){
        return this.whichHandler;
    }
    
    /**
     * Method called from doStartTag(). Fill in this method to perform other
     * operations from doStartTag().
     */
    private void otherDoStartTagOperations() {
        try {
            JspWriter out = pageContext.getOut();
            out.println(
            "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
            "<title>Biblioteca da Erika</title></head><body><center>"+
            "<br><br><h1 style=\"font-size:48px;\">"+whichPage+"</h1><br><br>"+
            "<form method=\"GET\" action=\"bibliocontroller\">"+
            "<input type=\"hidden\" name=\"whichHandler\""+
            "value=\""+whichHandler+"\"/>"
            );
           } catch (IOException ex) {
               // do something
           }
    }

    /**
     * Method called from doEndTag() Fill in this method to perform other
     * operations from doEndTag().
     */
    private void otherDoEndTagOperations() {
           try {
               JspWriter out = pageContext.getOut();
               out.println("</form></center></body></html>");
           } catch (IOException ex) {
               // do something
           }
    }

    /**
     * Fill in this method to process the body content of the tag. You only need
     * to do this if the tag's BodyContent property is set to "JSP" or
     * "tagdependent."
     */
    private void writeTagBodyContent(
            JspWriter out, BodyContent bodyContent) throws IOException {
        // write the body content (after processing by the JSP engine) on the output Writer
        bodyContent.writeOut(out);

        // clear the body content for the next time through.
        bodyContent.clearBody();
    }

    /**
     * This method is called when the JSP engine encounters the start tag, after
     * the attributes are processed. Scripting variables (if any) have their
     * values set here.
     */
    @Override
    public int doStartTag() throws JspException {
        otherDoStartTagOperations();
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        }
        
        return SKIP_BODY;
    }

    /**
     * This method is called after the JSP engine finished processing the tag.
     */
    @Override
    public int doEndTag() throws JspException {
        otherDoEndTagOperations();
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        }
        
        return SKIP_PAGE;
    }

    /**
     * This method is called after the JSP engine processes the body content of
     * the tag.
     */
    @Override
    public int doAfterBody() throws JspException {
        try {
            // This code is generated for tags whose bodyContent is "JSP"
            BodyContent bodyCont = getBodyContent();
            JspWriter out = bodyCont.getEnclosingWriter();
            
            writeTagBodyContent(out, bodyCont);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }
        
        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } 
        return SKIP_BODY;
    }

    /**
     * Handles exception from processing the body content.
     */
    private void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("Error in MVCPaginas tag", ex);
    }

    /**
     * Fill in this method to determine if the rest of the JSP page should be
     * generated after this tag is finished. Called from doEndTag().
     */
    private boolean shouldEvaluateRestOfPageAfterEndTag() {
        return true;
    }

    /**
     * Fill in this method to determine if the tag body should be evaluated
     * and again after evaluating the body. Use this method to create an iterating
     * tag. Called from doAfterBody().
     */
    private boolean theBodyShouldBeEvaluated() {
        return true;
    }
    
    private boolean theBodyShouldBeEvaluatedAgain() {
        return false;
    }
}
