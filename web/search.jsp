<%-- 
    Document   : search
    Created on : Mar 21, 2020, 10:10:12 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Searching Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULLNAME}
        </font>
        <form action="logout">
            <input type="submit" value="Logout" name="btnAction" />
        </form> <br/>
        <form action="searchLastname">
            Search value <input type="text" name="txtSearch" value="${param.txtSearch}" /> 
            <input type="submit" value="Search" name="btnAction" />
        </form>
            <c:set var="searchValue" value="${param.txtSearch}"/>
            <c:set var="checkedPassword" value="${requestScope.ERRORUPDATE}"/>
            <c:set var="username" value="${requestScope.ERRORUSER}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCHVALUE}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>username</th>
                                <th>password</th>
                                <th>lastname</th>
                                <th>role</th>
                                <td>Delete</td>
                                <td>Update</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
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
                                   <c:if test="${not empty checkedPassword and dto.username == username}">
                                       <font color="red">
                                       ${checkedPassword.passwordEmpty}
                                       ${checkedPassword.passwordLength}
                                       </font>
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
                                    <c:url var="urlRewriting" value="deleteAccount">
                                        <c:param name="btnAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>                                       
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>

<!--                <input type="button" value="Delete" onclick="window.location.href='/Assignment_NguyenHoangPhucHuy/deleteAccount?&pk=${dto.username}&lastSearchValue=${searchValue}'"/> -->
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
                
                
                <c:if test="${empty result}">
                    No records found
                </c:if>
            </c:if>       
    </body>
</html>
