<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="uzytkownik" class="ti.model.User" scope="session"/>
        <% if (uzytkownik.getPermissions()<0) {%>

        <form action="RM?akcja=login" method="post">
            <label for="log">Login:</label> <input placeholder="login" id="log" name="log" style="width: 90%">
            <label for="pass">Hasło:</label> <input type="password" placeholder="hasło" id="pass" name="pass" style="width: 90%">
            <input type="submit" value="Zaloguj">
        </form>
        <%} else {%>
        <form action="RM?akcja=wyloguj" method="post">
            Jesteś zalogowany jako <b>${uzytkownik.getLogin()}</b>
            <input type="submit" id="wyloguj" value="wyloguj">
        </form>

        <%}%>
        <p id="news1"></p>
        <p id="news2"></p>


