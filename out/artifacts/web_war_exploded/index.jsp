<%@ page import="ti.Narzedzia" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Moodle</title>
  <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<jsp:useBean id="user" class="ti.model.User" scope="session"/>

<% String strona = request.getParameter("strona");
  if (user.getPermissions()==1)
    strona = Narzedzia.parsujStrone(strona, "glowna;tests;pierwsze;ustawienia;");
  else if (user.getPermissions()==2)
    strona = Narzedzia.parsujStrone(strona, "glowna;tests;pierwsze;ustawienia;administracja;addTests;listTests;addUserToTest");
  else
    strona = Narzedzia.parsujStrone(strona, "glowna;pierwsze;rejestracja;");
%>

<body>
<div id="kontener">
  <div id="naglowek">
    <jsp:include page="/WEB-INF/widok/naglowek.jsp"/>
  </div>
  <div id="srodek">

    <div id="menu">
      <jsp:include page="/WEB-INF/widok/menu.jsp"/>

    </div>

    <div id="tresc">
      <jsp:include page="/WEB-INF/widok/tresc.jsp">
        <jsp:param name="jaka_strona" value="<%=strona%>"/>
      </jsp:include>
    </div>

    <div id="newsy">
      <jsp:include page="/WEB-INF/widok/newsy.jsp"/>

    </div>


  </div>

  <div id="stopka">
    <jsp:include page="/WEB-INF/widok/stopka.jsp"/>
  </div>
</div>
</body>
</html>

