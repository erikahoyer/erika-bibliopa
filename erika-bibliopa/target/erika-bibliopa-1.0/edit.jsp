<%-- 
    Document   : catalog
    Created on : Set 17, 2016, 2:25:37 AM
    Author     : Erika
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="/WEB-INF/tlds/MVCPages" %>

<link rel="stylesheet" type="text/css" href="./assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="./assets/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
<script src="./assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<mvc:MVCPages whichPage="Catalogação" whichHandler="com.hoyer.erika.pa.handlers.EditHandler">
    <div class = "container">
        <center><a href="index.jsp">Busca</a></center>
        <p/>
        <p/>
        <h3 style="color:red;">${sessionScope.Dialog}</h3>
        <p/>
        <p/>
        Serial = <input type="text" name="serialNo" value="${param.serialNo}" size="5"/><br/>
        Título = <input type="text" name="Title" value="${sessionScope.Title}" size="60"/><br/>
        Autor  = <input type="text" name="Author" value="${sessionScope.Author}" size="60"/><br/><br/>
                <input type="submit" name="botaoSubmit" value="Inserir"/>
                <input type="submit" name="botaoSubmit" value="Atualizar"/>
                <input type="submit" name="botaoSubmit" value="Limpar"/>
                <input type="submit" name="botaoSubmit" value="Excluir"/>
        
    </div>
</mvc:MVCPages>