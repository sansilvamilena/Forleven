<%-- 
    Document   : list
    Created on : Aug 4, 2020, 12:56:03 AM
    Author     : milena
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h2>Formulário de Consulta</h2>
        <table border=1 cellspacing=0 cellpadding=2 bordercolor="666633">
            <tr>
                <td>Código</td>
                <td>Matrícula</td>
                <td>Nome</td>
                <td>Sobrenome</td>
                <td>Opções</td>
            </tr>
            <c:forEach items="${lista}" var="l">
                <tr>
                    <td>${l.codEstudante}</td>
                    <td>${l.matricula}</td>
                    <td>${l.nome}</td>
                    <td>${l.sobrenome}</td>
                    <td><a href="<c:url value="/student/"/>${l.codEstudante}">Alterar </a></td>
                    <td><a href="<c:url value="/student/delete/"/>${l.codEstudante}"> Remover</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="<c:url value="/"/>">Voltar</a>
    </body>
</html>
