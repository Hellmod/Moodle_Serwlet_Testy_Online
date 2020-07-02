<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Baza baza = (Baza) pageContext.getServletContext().getAttribute("baza");
    List<User> rekordy = baza.selectUsers();
    request.setAttribute("rekordy",rekordy);
%>
<br/>
<table>
<c:forEach var="pracownik" items="${rekordy}">

    <form action="ADMIN?akcja=uprawnienia" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${pracownik.getId()}">
        <TR><td><label>${pracownik.getLogin()}</label><td/><td>
        <select name="uprawnienia">
            <option value="-2" <c:if test = "${pracownik.getPermissions()>0}">selected</c:if> >Zablokowany</option>
            <option value="1" <c:if test = "${pracownik.getPermissions()==1}">selected</c:if> >Aktywny</option>
            <option value="2" <c:if test = "${pracownik.getPermissions()==2}">selected</c:if> >Administrator</option>
        </select>
            <td/>
            <td><input type="submit" value="Nadaj uprawnienia"/><td/>
    </form>
        <td>
    <form action="ADMIN?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${pracownik.getId()}">
        <input type="hidden" name="login" value="${pracownik.getLogin()}">
        <input type="submit" value="Usuń"/>
    </form>
        <td/><TR/>

</c:forEach>
</table>
<br/>

<br/>

