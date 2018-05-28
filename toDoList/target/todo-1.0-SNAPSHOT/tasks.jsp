<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--@elvariable id="futureCookie" type="java.lang.String"--%>
<%--@elvariable id="taskList" type="java.util.List"--%>

<c:set var="userLocale"
       value="${not empty futureCookie ?
       futureCookie : (not empty cookie.userLocale.value ? cookie.userLocale.value : pageContext.request.locale)}"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="labels"/>

<html>
<head>
    <title>TODO list</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="small text-right">
                <a href="task?userLocale=en_GB"><fmt:message key="language.english"/></a> |
                <a href="task?userLocale=de_DE"><fmt:message key="language.german"/></a> |
                <a href="task?userLocale=pl_PL"><fmt:message key="language.polish"/></a>
            </div>
            <br>
            <div class="text-center">
                <h1 class="display-1"><strong>todo</strong> list</h1></h1>
                <form action="task" method="POST">
                    <div class="form-row align-items-center">
                        <div class="col-md-10">
                            <input type="text" id="newTask" name="newTask" class="form-control" autocomplete="off"/>
                        </div>
                        <div class="col-md-2">
                            <input type="submit" class="btn btn-outline-primary btn-sm"
                                   value="<fmt:message key="label.submit"/>"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card shadow-sm p-3 mb-5 bg-white rounded">
                <ul class="list-group list-group-flush">
                    <c:forEach var="task" items="${taskList}">
                        <c:url var="deleteUrl" value="task">
                            <c:param name="id" value="${task.id}"/>
                            <c:param name="operation" value="delete"/>
                        </c:url>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                ${task.task}
                            <span class="close">
                                <a href="${deleteUrl}" aria-label="<fmt:message key="button.delete"/>">
                                    <span aria-hidden="true">&times;</span>
                                </a>
                            </span>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${empty taskList}">
                    <div class="text-center"><fmt:message key="label.list.empty"/></div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script src="static/js/jquery-3.3.1.slim.min.js"></script>
<script src="static/js/popper.min.js"></script>
<script src="static/js/bootstrap.bundle.min.js"></script>
</body>
</html>
