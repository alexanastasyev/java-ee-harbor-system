<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Edit user</title>

  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/back.js"></script>

  <%--    Bootstrap--%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
  <div class="content-container">
    <h1>Make a complaint</h1>
    <c:if test="${errorMessage != null}">
      <hr/>
      <p style="color: red">${errorMessage}</p>
      <c:remove var="errorMessage" scope="session" />
    </c:if>
    <div class="margin-top-medium">
      <b>From: </b> <br/>
      ${fromUser.getLogin()}
    </div>
    <form method="post" action="controller" class="margin-top-small">
      <div class="form-group">
        <input type="hidden" name="command" value="create_report">
        <input type="hidden" name="fromUserId" value="${fromUser.getId()}">
        <label>
          <b>To: </b>
          <select name="toUserId" class="form-control">
            <c:forEach var="toUser" items="${toUsers}">
              <option value="${toUser.getId()}">
                  ${toUser.getLogin()}
              </option>
            </c:forEach>
          </select>
        </label>
        <br/>
        <label class="margin-top-small">
          <b>Text: </b>
          <textarea name="reportText" type="text" class="form-control" rows="5"></textarea>
        </label>
        <div>
          <button type="submit" class="btn btn-primary margin-top-medium">Complain</button>
        </div>
        <hr/>
      </div>
    </form>
    <button class="btn btn-secondary" onclick="back()">Back</button>
  </div>
</body>
</html>