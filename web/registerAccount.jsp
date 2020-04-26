<%-- 
    Document   : registerAccount
    Created on : Mar 21, 2020, 11:05:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRATION ACCOUNT</title>
    </head>
    <body>
        <h1> Register your account </h1>
        <form action="registerAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6-20 chars) <br/>
            <c:if test="${not empty errors.usernameLongErr}">
                <font color="red">
                ${errors.usernameLongErr}
                </font>
            </c:if> <br/>
                
            Password* <input type="password" name="txtPassword" value="" /> (6-30 chars) <br/>
            <c:if test="${not empty errors.passwordLongErr}">
                <font color="red">
                ${errors.passwordLongErr}
                </font>
            </c:if> <br/>
                
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNoMatchPassword}">
                <font color="red">
                ${errors.confirmNoMatchPassword}
                </font>
            </c:if> <br/>
                
            Full name* <input type="text" name="txtFullname" value="${txtFullname}" /> (2 - 50 chars) <br/>
            <c:if test="${not empty errors.fullNameLongErr}">
                <font color="red">
                ${errors.fullNameLongErr}
                </font>
            </c:if> <br/>
            
            <input type="submit" value="Register" name="btnAction" />
            <input type="reset" value="Reset" />
            
        </form> <br/>
        
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
                ${errors.usernameIsExisted}
            </font>
        </c:if><br/>
        <a href="bookStore">Click here to go shopping!!!</a> <br/>
        <a href="loginPage">Click here to back Login Page</a>
    </body>
</html>
