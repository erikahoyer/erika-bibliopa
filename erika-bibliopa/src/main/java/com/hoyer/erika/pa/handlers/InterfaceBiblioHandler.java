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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceBiblioHandler {
    public String process(HttpServletRequest request,
        HttpServletResponse response);
}
