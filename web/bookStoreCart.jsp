<%-- 
    Document   : bookStoreCart
    Created on : Mar 22, 2020, 11:41:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BOOKSTORE CART</title>
    </head>
    <body>
        <h1>Book Store</h1>    
            <c:set var="result" value="${requestScope.BOOKS}"/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="addBook">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.title}
                            <input type="hidden" name="titleBook" value="${dto.title}" />
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <input type="submit" value="Add" name="btnAction" />
                            </td>
                        </tr>       
                        </form>                      
                        </c:forEach>
                    </tbody>
                </table><br/>
                <form action="viewCart">
                    <input type="submit" value="View your cart" name="btnAction" />
                </form><br/>
        <a href="loginPage">Click here to back Login Page</a>
    </body>
</html>
