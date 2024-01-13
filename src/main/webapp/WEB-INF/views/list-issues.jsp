<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
	<title>List Issues</title>

	<!-- LInk to CSS File -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2> Issue Management System</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		<!--  Add "Add Issue" Button -->
		<input type="button" value="Add Issue"
			   onclick="window.location.href='showFormForAdd'; return false;"
			   class="add-button"/>

         <h2> Search Appointment </h2>
                            <form:form action="/search?page=0&size=5" method="POST">
                    			<div>
                    			    <input type="text" placeholder="Search By Issue Title/Desc/Owner" name="theSearchName" value = "${theSearchName}">
                    			    <select name="status">
                                        <c:forEach var="item" items="${statusList}">
                                            <option value="${item}" ${item == status ? 'selected="selected"' : ''}>${item}</option>
                                        </c:forEach>
                                    </select>
                    			    <input type="submit" value="Search"/>
                    			</div>
                            </form:form>

		<!-- Add Table Content here -->
		<table border = "1">
			<tr>
				<th> Ticket Number</th>
				<th> Issue Title </th>
				<th> Issue Description </th>
				<th> Filing Date</th>
				<th> Owner</th>
				<th> Current Status </th>
				<th> Action</th>
			</tr>
			<c:forEach var="tempIssue" items="${issues}">

			<!-- Add embedded link to update the customer -->
			<c:url var="updateLink" value="/issue/showFormForUpdate">
				<c:param name="issueId" value="${tempIssue.id}"/>
			</c:url>
			<c:url var="deleteLink" value="/issue/showFormForDelete">
				<c:param name="issueId" value="${tempIssue.id}"/>
			</c:url>

					<tr>
						<td> Issue - ${tempIssue.id} </td>
						<td> ${tempIssue.issueTitle} </td>
						<td> ${tempIssue.issueDesc} </td>
						<td> ${tempIssue.filingDate} </td>
						<td> ${tempIssue.owner} </td>
						<td> ${tempIssue.status} </td>
						<td>
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this issue?'))) return false">
							|Clear</a>
							<c:if test = "${tempIssue.status == 'OPEN'}">
                                     <a href="/updateStatus?status=IN_PROGRESS&id=${tempIssue.id}">Move to In Progress</a>
                            </c:if>
                            <c:if test = "${tempIssue.status == 'IN_PROGRESS'}">
                                  <a href="/updateStatus?status=COMPLETED&id=${tempIssue.id}">Mark As Completed</a>
                            </c:if>
						</td>
					</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>