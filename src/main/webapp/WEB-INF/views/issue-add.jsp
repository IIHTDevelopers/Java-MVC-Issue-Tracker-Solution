<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
	<title>Issue</title>

	<!-- LInk to CSS File -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

	<!-- LInk to Another CSS File -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-issue-style.css" />

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Issue Management System</h2>
		</div>
	</div>
	<div id="container">
		<h3> Add Issue</h3>
		<form:form action="saveIssue" modelAttribute="issue" method="POST">
		<!-- Associate the data with a given customer with a hidden form param -->
		<form:hidden path="id"/> <!-- Customer.setId will be called -->
		<table>
			<tbody>
				<tr>
                	<td><label> Issue Title:</label></td>
                	<td><form:input path="issueTitle"/></td>
                	<td><form:errors path="issueTitle"/></td>
                	</tr>
				<tr>
					<td><label> Issue Description:</label></td>
					<td><form:textarea path="issueDesc"/></td>
					<td><form:errors path="issueDesc"/></td>
				</tr>
				<tr>
					<td><label> Filing Date (MM/DD/YYYY):</label></td>
					<td><form:input path="filingDate" type = "date" id="todayDate"/></td>
					<td><form:errors path="filingDate"/></td>
				</tr>
				<tr>
					<td><label> Owner:</label></td>
					<td><form:input path="owner"/></td>
					<td><form:errors path="owner"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			</tbody>
		</table>
		</form:form>
	</div>
	<!--  Adding The link to the bottom of the page rather than at the top -->
	<br><br>
	<br><br>
	<div id="container">
			<a href="${pageContext.request.contextPath}/issue/list">Back to List Page</a>
	</div>
<script>
  document.getElementById("todayDate").valueAsDate = new Date();
 </script>

</body>
</html>