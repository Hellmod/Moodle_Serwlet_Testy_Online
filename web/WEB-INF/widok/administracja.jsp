<%@page import="ti.Narzedzia, ti.model.User,ti.model.Baza2" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="baza" class="ti.model.Baza" scope="session"></jsp:useBean>
<%
    List<User> rekordy = baza.selectUsers();
    System.out.println("Lista uzytkowników:");
    request.setAttribute("rekordy",rekordy);

%>
<br/>
<c:forEach var="pracownik" items="${rekordy}">
    <form action="RM?akcja=uprawnienia" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik}">
        <label>${pracownik.getLogin()}</label>
        <select name="uprawnienia">
            <option value="0" <c:if test = "${pracownik.getPermissions()==0}">selected</c:if> >Nieaktywny</option>
            <option value="1" <c:if test = "${pracownik.getPermissions()==1}">selected</c:if> >Aktywny</option>
            <option value="2" <c:if test = "${pracownik.getPermissions()==2}">selected</c:if> >Administrator</option>
        </select>
        <input type="submit" value="Nadaj uprawnienia"/>

    </form>
    <form action="RM?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik.getId()}">
        <input type="submit" value="Usuń"/>
    </form>

    <form action="RM?akcja=zablokuj" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik.getId()}">
        <input type="submit" value="Zablokuj"/>

    </form>

</c:forEach>
<br/>

<br/>

