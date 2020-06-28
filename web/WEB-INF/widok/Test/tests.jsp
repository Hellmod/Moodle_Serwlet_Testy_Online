<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Baza baza = (Baza) pageContext.getServletContext().getAttribute("baza");
    User user = (User) session.getAttribute("user");
    List<Object[]> rekordy = baza.selectTests(user.getId());//0-testId 1-testName 2-ileMin 3-odData 4-doData
    request.setAttribute("rekordy",rekordy);

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd', 'kk:mm");
    request.setAttribute("ft",ft);

%>
<br/>
<c:forEach var="test" items="${rekordy}">
    <form action="TEST?akcja=addUser" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${test[0]}">
        <label>${test[1]}</label><br/>
        czas na rozwiązanie testu: ${test[2]}<br/>
        ${ft.format(test[3])}<br/>
        ${ft.format(test[4])}
        <input type="button" onclick="window.location.href='index.jsp?strona=Test/addUserToTest&testId=${test[0]}'" value="Dodaj osoby"/>

    </form>


</c:forEach>
<br/>

<br/>

