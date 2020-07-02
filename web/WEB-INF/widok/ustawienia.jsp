<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="USER?akcja=ustawienia" method="post" accept-charset="UTF-8" >
    <table>
        <TR><td>Login:</td>   <td><input type="text" name="login" value="${user.login}"/><br/></td></TR>
        <TR><td>Hasło:</td>   <td><input type="password" name="haslo" value="${user.password}"/><br/></td></TR>
    </table>
    <input type="submit" value="Zapisz"/>
</form>
