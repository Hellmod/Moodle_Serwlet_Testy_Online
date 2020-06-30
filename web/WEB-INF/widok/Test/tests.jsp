<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Baza baza = (Baza) pageContext.getServletContext().getAttribute("baza");
    User user = (User) session.getAttribute("user");
    List<Object[]> rekordy = baza.selectTests(user.getId());//0-testId 1-testName 2-ileMin 3-odData 4-doData 5-randomQuestion
    request.setAttribute("rekordy",rekordy);

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd', 'kk:mm");
    request.setAttribute("ft",ft);

    Date today = new Date();
    request.setAttribute("today",today);
%>
<br/>
<c:forEach var="test" items="${rekordy}">
    <form action="TEST?akcja=startTest" method="post" accept-charset="UTF-8">
        <input type="hidden" name="testId" value="${test[0]}">
        <label>${test[1]}</label><br/>
        czas na rozwiązanie testu: ${test[2]}<br/>
        Data od: <b>${ft.format(test[3])}</b> do:        <b>${ft.format(test[4])}</b><br/>

        <c:if test="${ today>test[3] && today<test[4] && baza.isPermission(test[0],user.getId()) }">
            <input type="button" onclick="window.location.href='index.jsp?strona=Test/solveTest&testId=${test[0]}&quesrNum=${test[5]}'" value="Rozwiąż"/>
        </c:if>

        <c:if test="${ today>test[3] && today<test[4] && baza.isPermission(test[0],user.getId()) }">
            <input type="submit" value="Rozwiąż"/>
        </c:if>
    </form>
    <form action="TEST?akcja=showResult" method="post" accept-charset="UTF-8">
        <input type="hidden" name="testId" value="${test[0]}">
        <c:if test="${today>test[4]}">
            <input type="submit" value="Pokaż rozwiązanie"/>
        </c:if>
    </form>
</c:forEach>
<br/>

<br/>

