<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="RM?akcja=kolorUser" method="post">

    Kolor tła: <input type="color" name="kolor" value="${applicationScope.kolorTla}"/>
    <input type="submit" value="zapisz"/>

</form>

<form action="RM?akcja=ustawienia" method="post" accept-charset="UTF-8" >
    Imię: <input type="text" name="imie" value="${sessionScope.uzytkownik.imie}"/><br/>
    Nazwisko: <input type="text" name="nazwisko" value="${sessionScope.uzytkownik.nazwisko}"/><br/>
    Wiek: <input type="number" name="wiek" value="${sessionScope.uzytkownik.wieks}"/><br/>

    <input type="submit" value="Zapisz"/>


</form>
