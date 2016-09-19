<%-- 
    Document   : index
    Created on : Set 17, 2016, 1:43:46 AM
    Author     : Erika
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="/WEB-INF/tlds/MVCPages" %>

<link rel="stylesheet" type="text/css" href="./assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="./assets/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
<script src="./assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<mvc:MVCPages whichPage="Index" whichHandler="com.hoyer.erika.pa.handlers.IndexHandler">
    <center><a href="edit.jsp">Catalogação</a></center>
    <p/>
    <p/>
    <div class="container">
        <form>
            <select name="searchType">
                <option value="Title">Titulo</option>
                <option value="Author">Autor</option>
            </select>
            <input type="text" name="barraBusca" value="${sessionScope.SearchField}" size="60"/><br/><br/>
            <input type="submit" name="botaoSubmit" value="Buscar"/>
            <input type="submit" name="botaoSubmit" value="Limpar"/>    
        </form>
        <p/>
        <p/>
        <center><table class="table table-striped">
            <c:forEach items="${sessionScope.searchResult}" var="x">
                <tr>
                    <td>${x.getSerialNo()}</td><td><a href="edit.jsp?serialNo=${x.getSerialNo()}">${x.getTitle()}</a></td><td>${x.getAuthor()}</td>
                </tr>
            </c:forEach>
        </table></center>
    </div>
</mvc:MVCPages>