<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Welcome, moderator</title>
  </head>

  <body>
    <h1>Welcome, moderator</h1>
    ${errorMessage}
    <hr/>
    <a href="controller?command=show_moderator_users_page">Users</a>
    <hr/>
    <table>
      <tr>
        <th>No</th>
        <th>From</th>
        <th>To</th>
        <th></th>
      </tr>
      <c:forEach var="user" items="${reports}" varStatus="status">
        <tr>
          <td><c:out value="${status.count}" /></td>
          <td><c:out value="${user.getFromUser().getLogin()}" /></td>
          <td><c:out value="${user.getToUser().getLogin()}" /></td>
          <td><a href="controller?command=show_report_page&id=${user.getId()}">Show details</a></td>
        </tr>
      </c:forEach>
    </table>
    <hr />
    <a href="controller?command=logout">Logout</a>
  </body>

</html>
