<%-- 
    Document   : index
    Created on : Aug 4, 2020, 12:55:35 AM
    Author     : milena
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forleven</title>
    </head>
    <body>
        <h1>Gerenciamento de Estudantes</h1>
        <p><a href="<c:url value="/student"/>">Cadastro</a></p>
        <p><a href="<c:url value="/student/list"/>">Consulta</a></p>
    </body>
</html>
