<%@page import="ti.Narzedzia, ti.model.User,ti.model.Baza2" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="baza" class="ti.model.Baza" scope="session"></jsp:useBean>
<%
    List<User> rekordy = baza.selectUsers();
    request.setAttribute("rekordy",rekordy);

%>
<br/>
<c:forEach var="pracownik" items="${rekordy}">
    <form action="ADMIN?akcja=uprawnienia" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${pracownik.getId()}">
        <label>${pracownik.getLogin()}</label>
        <select name="uprawnienia">
            <option value="-2" <c:if test = "${pracownik.getPermissions()>0}">selected</c:if> >Zablokowany</option>
            <option value="1" <c:if test = "${pracownik.getPermissions()==1}">selected</c:if> >Aktywny</option>
            <option value="2" <c:if test = "${pracownik.getPermissions()==2}">selected</c:if> >Administrator</option>
        </select>
        <input type="submit" value="Nadaj uprawnienia"/>

    </form>
    <form action="ADMIN?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${pracownik.getId()}">
        <input type="hidden" name="login" value="${pracownik.getLogin()}">
        <input type="submit" value="Usuń"/>
    </form>


</c:forEach>
<br/>

<br/>

