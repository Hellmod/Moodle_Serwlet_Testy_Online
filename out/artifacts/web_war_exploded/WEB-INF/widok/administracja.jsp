<%@page import="ti.Narzedzia, ti.model.RMuzytkownik,ti.model.Baza" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="RM?akcja=kolor" method="post">

    Kolor tła: <input type="color" name="kolor" value="${applicationScope.kolorTla}"/>
    <input type="submit" value="zapisz"/>

</form>

<form action="RM?akcja=usunKolor" method="post">

    <input type="submit" value="Usuń Kolor"/>

</form>

<br/>
<c:forEach var="pracownik" items="${  applicationScope.baza.uzytkownicy}">
    <form action="RM?akcja=uprawnienia" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik.value.login}">
        ${pracownik.key}:</br>
        <select name="uprawnienia">
            <option value="0" <c:if test = "${pracownik.value.uprawnienia==0}">selected</c:if> >Nieaktywny</option>
            <option value="1" <c:if test = "${pracownik.value.uprawnienia==1}">selected</c:if> >Aktywny</option>
            <option value="2" <c:if test = "${pracownik.value.uprawnienia==2}">selected</c:if> >Administrator</option>
        </select>
        <input type="submit" value="Nadaj uprawnienia"/>
        ${pracownik.value.uprawnienia}

    </form>
    <form action="RM?akcja=usun" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik.value.login}">
        <input type="submit" value="Usuń"/>
    </form>

    <form action="RM?akcja=zablokuj" method="post" accept-charset="UTF-8">
        <input type="hidden" name="login" value="${pracownik.value.login}">
        <input type="submit" value="Zablokuj"/>
    </form>

</c:forEach>
<br/>

<br/>

