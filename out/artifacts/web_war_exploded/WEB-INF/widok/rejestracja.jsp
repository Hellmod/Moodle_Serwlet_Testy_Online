<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="USER?akcja=register" method="post" accept-charset="UTF-8">
    <table>
        <TR><td><label for="log">Login:</label><td/><td><input placeholder="login" id="log" name="log" ></td>
        <TR><td><label for="pass">Hasło:</label><td/><td><input type="password" placeholder="hasło" id="pass" name="pass" ></td>
    </table>
    <br/>
    <input type="submit" id="loguj" value="Zarejestruj">

</form>
