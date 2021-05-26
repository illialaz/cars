<%--
  Created by IntelliJ IDEA.
  User: illia
  Date: 4/17/21
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid black;
        }

        th {
            background-color: darksalmon;
            height: 70%;
        }

        th, td {
            padding: 15px;
            border-bottom: 1px solid black;
        }

        tr:hover {background-color: gray;}

        a, a:visited {
            color: darkturquoise;
            text-decoration: none;
        }

        a:hover {
            color: deepskyblue;
        }
    </style>
</head>
<body>
<div style="overflow-x:auto;">
    <table>
        <tr>
            <th><a href="?param=country">Country</a></th>
            <th><a href="?param=company">Company</a></th>
            <th><a href="?param=model">Model</a></th>
            <th><a href="?param=weight">Weight</a></th>
            <th><a href="?param=maxSpeed">Max Speed</a></th>
            <th><a href="?param=cost">Price</a></th>
            <th><a href="?param=seats">Seats</a></th>
            <th><a href="?param=ageUnder18"><18</a></th>
            <th><a href="?param=ageBetween18And30">18-30</a></th>
            <th><a href="?param=ageBetween30And50">30-50</a></th>
            <th><a href="?param=ageAfter50">>50</a></th>
            <th>Change</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${sportcars}" var="sportcar">
            <tr>
                <td><c:out value="${sportcar.country}" /></td>
                <td><c:out value="${sportcar.company}" /></td>
                <td><c:out value="${sportcar.model}" /></td>
                <td><c:out value="${sportcar.weight}" /></td>
                <td><c:out value="${sportcar.maxSpeed}" /></td>
                <td><c:out value="${sportcar.cost}" /></td>
                <td><c:out value="${sportcar.quarterMileTime}" /></td>
                <td><c:out value="${sportcar.statistics.ageUnder18}" /></td>
                <td><c:out value="${sportcar.statistics.ageBetween18And30}" /></td>
                <td><c:out value="${sportcar.statistics.ageBetween30And50}" /></td>
                <td><c:out value="${sportcar.statistics.ageAfter50}" /></td>
                <td>
                    <a href="/cars_war_exploded/sportcar?id=${sportcar.id}">Change</a>
                </td>
                <td>
                    <a class="confirmation" href="/cars_war_exploded/deleteSportcar?id=${sportcar.id}">Delete</a>
                </td>
                <script type="text/javascript">
                    var aElems = document.getElementsByClassName('confirmation');

                    for (var i = 0, len = aElems.length; i < len; i++) {
                        aElems[i].onclick = function () {
                            var check = confirm("Are you sure you want to leave?");
                            return check === true;
                        };
                    }
                </script>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/cars_war_exploded/sportcar">Add</a>
</div>
</body>
</html>
