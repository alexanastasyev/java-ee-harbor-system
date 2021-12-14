<%--suppress XmlDefaultAttributeValue, ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Welcome, moderator</title>

  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

  <%--    Bootstrap--%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>

<body>
  <div class="content-container">
    ${errorMessage}
    <h1>Welcome!</h1>
    <hr/>
      <div class="align-content-opposite">
        <div class="width-wrap-content">
          <a href="controller?command=show_moderator_users_page"><button class="btn btn-primary">Users</button></a>
        </div>
        <div class="width-wrap-content">
          <a href="controller?command=logout"><button class="btn btn-danger">Logout</button></a>
        </div>
      </div>
    <hr/>
    <h2>Complaints</h2>
    <table class="table table-hover margin-top-medium">
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
          <td><a href="controller?command=show_report_page&id=${user.getId()}"><button class="btn btn-outline-primary">Show details</button></a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>

</html>
