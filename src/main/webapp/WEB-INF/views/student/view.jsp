<%-- 
    Document   : views
    Created on : Aug 4, 2020, 12:55:51 AM
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
        <c:url var="post_url" value="/student"/>
        <form:form enctype="multipart/form-data" commandName="estudante" method="POST" action="${post_url}">
            <form:input path="codEstudante" readonly="true" type="hidden" name="codEstudante"/>
            <h2>Formulário de Cadastro</h2>
            <h3>Dados do Estudante</h3>
            <p><label for="nome">Nome</label></p>
            <p><form:input path="nome" type="text" name="nome"/></p>
            <form:errors path="nome" />
            <p><label for="sobrenome">Sobrenome</label></p>
            <p><form:input path="sobrenome" type="text" name="sobrenome"/></p>
            <form:errors path="sobrenome" />
            <p><label for="matricula">Matrícula</label></p>
            <p><form:input path="matricula" type="text" name="matricula"/></p>
            <form:errors path="matricula" />
            <a href="<c:url value="/"/>">Voltar</a>
            <input type="submit" value="Cadastrar">
        </form:form>
    </body>
</html>

