<%@page import="ti.Narzedzia, ti.model.User,ti.model.Baza2" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="baza" class="ti.model.Baza" scope="session"></jsp:useBean>
<%
    List<String[]> rekordy = baza.selectTests();
    request.setAttribute("rekordy",rekordy);

%>
<br/>
<c:forEach var="test" items="${rekordy}">
    <form action="TEST?akcja=addUser" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${test[0]}">
        <label>${test[1]}</label>
        <input type="button" onclick="window.location.href='index.jsp?strona=Test/addUserToTest&testId=${test[0]}'" value="Dodaj osoby"/>

    </form>
    <form action="TEST?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${test[0]}">
        <input type="submit" value="Usuń"/>
    </form>
    <form action="TEST?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${test[0]}">
        <input type="submit" value="Edytuj"/>
    </form>


</c:forEach>
<br/>

<br/>

