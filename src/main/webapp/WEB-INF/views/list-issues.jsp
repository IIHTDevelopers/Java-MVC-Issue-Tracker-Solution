<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<!-- Add Table Content here -->
		<table>
			<tr>
				<th> Ticket Number</th>
				<th> Issue </th>
				<th> Filing Date</th>
				<th> Owner</th>
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
						<td> ${tempIssue.bouncer} </td>
						<td> ${tempIssue.issue} </td>
						<td> ${tempIssue.filingDate} </td>
						<td> ${tempIssue.owner} </td>
						<td>
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this issue?'))) return false">
							|Clear</a>
						</td>
					</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>