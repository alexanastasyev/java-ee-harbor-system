<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Edit user</title>
</head>
  <body>
  <c:if test="${errorMessage != null}">
    <hr/>
    <p style="color: red">${errorMessage}</p>
    <c:remove var="errorMessage" scope="session" />
  </c:if>
    <b>From: </b>
    ${fromUser.getLogin()}
    <form method="post" action="controller">
      <input type="hidden" name="command" value="create_report">
      <input type="hidden" name="fromUserId" value="${fromUser.getId()}">
      <label>
        <b>To: </b>
        <select name="toUserId">
          <c:forEach var="toUser" items="${toUsers}">
            <option value="${toUser.getId()}">
                ${toUser.getLogin()}
            </option>
          </c:forEach>
        </select>
      </label>
      <label>
        <b>Text: </b>
        <input name="reportText" type="text"/>
      </label>
      <input type="submit" value="Report">
    </form>
  </body>
</html>