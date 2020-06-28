<%@page import="ti.Narzedzia, ti.model.User,ti.model.Baza2" %>
<%@ page import="java.util.List" %>
<%@ page import="ti.model.Baza" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
    Baza baza = (Baza) pageContext.getServletContext().getAttribute("baza");
    List<User> rekordy = baza.selectUsers();
    request.setAttribute("rekordy",rekordy);

    String testId = request.getParameter("testId");
    request.setAttribute("testId",testId);

    String testName = baza.selectTestName(testId);
    request.setAttribute("testName",testName);

    List<String> usersTestsId = baza.selectUsersTestsId(testId);
    System.out.println(usersTestsId);
    request.setAttribute("usersTestsId",usersTestsId);
%>
<br/>
<b>${testName}</b>
<form action="TEST?akcja=addUserToTest" method="post" accept-charset="UTF-8">
    <input type="hidden" name="testId" value="${testId}">
    <c:forEach var="pracownik" items="${rekordy}">
            <label>${pracownik.getLogin()}</label>
            <input <c:if test="${fn:contains(usersTestsId, pracownik.getId())  }">checked</c:if> type="checkbox" name="listToadd" value="${pracownik.getId()}">
            <br/>
    </c:forEach>

    <input type="submit" value="Zatwierdź"/>
</form>
<br/>

<br/>

