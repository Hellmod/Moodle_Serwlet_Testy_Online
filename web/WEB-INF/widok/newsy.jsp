<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="ti.model.User" scope="session"/>
        <% if (user.getPermissions()<0) {%>

        <form action="USER?akcja=login" method="post">
            <label for="log">Login:</label> <input placeholder="login" id="log" name="log" style="width: 90%">
            <label for="pass">Hasło:</label> <input type="password" placeholder="hasło" id="pass" name="pass" style="width: 90%">
            <input type="submit" value="Zaloguj">
        </form>
        <%} else {%>
        <form action="USER?akcja=wyloguj" method="post">
            Jesteś zalogowany jako <b>${user.getLogin()}</b>
            <input type="submit" id="wyloguj" value="wyloguj">
        </form>

        <%}%>



