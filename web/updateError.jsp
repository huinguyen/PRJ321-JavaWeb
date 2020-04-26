<%-- 
    Document   : updateError
    Created on : Mar 21, 2020, 5:05:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCHING PAGE</title>
    </head>
    <body>
       <font color="red">
        Welcome, ${sessionScope.FULLNAME}
        </font>
        <form action="logout">
            <input type="submit" value="Logout" name="btnAction" />
        </form> <br/>
        <form action="searchLastname">
            Search value <input type="text" name="txtSearch" value="${requestScope.LASTSEARCHVALUE}" /> 
            <input type="submit" value="Search" name="btnAction" />
        </form>
            
            <c:set var="searchValue" value="${requestScope.LASTSEARCHVALUE}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="listResult" value="${sessionScope.SEARCHVALUE}"/>
                <c:if test="${not empty listResult}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>username</th>
                                <th>password</th>
                                <th>lastname</th>
                                <th>role</th>
<!--                                <td>Delete</td>-->
                                <td>Update</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="userUpdate" value="${requestScope.ERRORUSER}"/>
                            <c:forEach var="dto" items="${listResult}" varStatus="counter">
                            <form action="updateAccount" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                   <input type="text" name="txtPassword" value="${dto.password}" />
                                   <c:set var="userName" value="${dto.username}"/>
                                    <c:if test="${userUpdate == userName}">
                                        <c:set var="errors" value="${requestScope.ERRORUPDATE}"/>
                                        <c:if test="${not empty errors}">
                                            <font color="red">
                                               ${errors.passwordEmpty}
                                               ${errors.passwordLength}
                                            </font>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                              checked = "checked" 
                                           </c:if>
                                           />
                                           
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <input type="submit" value="Update" name="btnAction" />
                                </td>
                            </tr>    
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>         
            </c:if>
    </body>
</html>
