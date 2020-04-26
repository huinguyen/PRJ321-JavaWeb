<%-- 
    Document   : viewcart
    Created on : Mar 22, 2020, 7:32:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW CART PAGE</title>
    </head>
    <body>
        <h1>Your cart includes</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="itemsBook" value="${cart.items}"/>
            <c:if test="${not empty itemsBook}">
                <form action="removeBook">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th><center>Action</center></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="itemName" items="${itemsBook}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${itemName.key}
                            </td>
                            <td>
                                ${itemName.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${itemName.key}" />
                            </td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                              <a href="bookStore">Add more books to your cart</a>
                            </td>
                            <td>
                              <input type="submit" value="Remove selected items" name="btnAction" />
                            </td>
                        </tr> 
                    </tbody>
                </table>
                </form>
                
                <form action="checkOut">
                    <input type="submit" value="CheckOut" name="btnAction" />
                </form>
            </c:if>
                <c:if test="${empty cart.items}">
                    <h2>
                       Your cart is not existed!!!
                    </h2>
                </c:if>
        </c:if>    

        
         
        <c:set var="checkoutMessage" value="${requestScope.SUCCESSFULL}"/>
        <c:if test="${not empty checkoutMessage}">
            <font color="green">
            ${checkoutMessage}
            </font> <br/>
            <a href="loginPage">Click here to back Login Page</a> <br/>
        </c:if>
            
        <c:if test="${empty checkoutMessage}">
        <c:if test="${empty cart.items}">
            <h3>
                Your cart is not existed!!!!
            </h3>
            <a href="loginPage">Click here to back Login Page</a> <br/>
            <a href="bookStore">Click here to go shopping!!!</a> <br/>
        </c:if> 
        </c:if>   
    </body>
</html>
