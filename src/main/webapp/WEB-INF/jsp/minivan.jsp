<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: illia
  Date: 4/18/21
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <form:form method="POST" action="" modelAttribute="minivan">
        <table>
            <tr>
                <td><form:label path="country">Country</form:label></td>
                <td><form:input path="country" /></td>
            </tr>
            <tr>
                <td><form:label path="company">Company</form:label></td>
                <td><form:input path="company" /></td>
            </tr>
            <tr>
                <td><form:label path="model">Model</form:label></td>
                <td><form:input path="model" /></td>
            </tr>
            <tr>
                <td><form:label path="weight">Weight</form:label></td>
                <td><form:input path="weight" /></td>
            </tr>
            <tr>
                <td><form:label path="maxSpeed">Max Speed</form:label></td>
                <td><form:input path="maxSpeed" /></td>
            </tr>
            <tr>
                <td><form:label path="cost">Price</form:label></td>
                <td><form:input path="cost" /></td>
            </tr>
            <tr>
                <td><form:label path="seats">Seats</form:label></td>
                <td><form:input path="seats" /></td>
            </tr>
            <tr>
                <td><form:label path="statistics.ageUnder18">ageUnder18</form:label></td>
                <td><form:input path="statistics.ageUnder18" /></td>
            </tr>
            <tr>
                <td><form:label path="statistics.ageBetween18And30">ageBetween18And30</form:label></td>
                <td><form:input path="statistics.ageBetween18And30" /></td>
            </tr>
            <tr>
                <td><form:label path="statistics.ageBetween30And50">ageBetween30And50</form:label></td>
                <td><form:input path="statistics.ageBetween30And50" /></td>
            </tr>
            <tr>
                <td><form:label path="statistics.ageAfter50">ageAfter50</form:label></td>
                <td><form:input path="statistics.ageAfter50" /></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type = "submit" value = "Add"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
