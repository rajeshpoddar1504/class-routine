<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Include the Tailwind CSS file here -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
<link rel="stylesheet" href="/css/custom.css">
<title>College Timetable</title>
</head>
<body>
<body>

	<div class="container mt-5 px-1 canvas_div_pdf" id="htmlContent">

		<div class="download-link">
			<c:if test="${not empty download_url}">
				<a href="${download_url}" id="download-routine" class="link-primary"
					style="text-align: right;">Download+</a>
			</c:if>
		</div>
		<h1 class="text-center ">
			<u>Faculty Routine 2024</u>
		</h1>
		<h3 class="text-center">Department of Computer Science and
			Engineering</h3>
		<h3 class="text-center mb-3">Bangamata Sheikh Fojilatunnesa Mujib
			Science & Technology University</h3>

		<div class="m-4">
			Select faculty : <select class="" id="facultySelect">
				<option selected="selected" disabled="disabled" value="">--Select--</option>
				<c:forEach items="${faculty_data }" var="facultyData">
					<option value="${facultyData.abbrevation }">${facultyData.fName } ${facultyData.lName }</option>
				</c:forEach>
			</select>
		</div>

		<div class="table-responsive">
			<table class="table table-bordered text-center">
				<%-- <c:forEach items="${routine_data }" var="routine_data_row" end="0"> --%>

				<thead>
					<tr>

						<c:if test="${not empty routine_timeslot }">
							<th scope="col">Day/Time</th>
							<c:forEach var="routine_ts" items="${routine_timeslot }">
								<%-- <c:if test="${routine_data_row_map.value ne '' or not empty routine_data_row_map.value }">
    	<c:set var="head_count1" value="${head_count1+1 }" scope="page"></c:set> --%>
								<th scope="col">${routine_ts.timeSlots }</th>
								<%-- </c:if> --%>
							</c:forEach>
						</c:if>
					</tr>
				</thead>

				<%-- </c:forEach> --%>
				<tbody>
					<!-- Time slots -->
					<c:forEach items="${routine_day }" var="routine_d">
						<tr>
							<td>${routine_d.dayAbbr }</td>

							<c:forEach items="${routine_data }" var="routine">
								<c:if test="${routine.day eq routine_d.dayAbbr }">

									<c:forEach var="routine_ts" items="${routine_timeslot }">
										<c:set value="${routine_ts.timeSlots }" var="slots"></c:set>
										<td>${routine[slots]}</td>
									</c:forEach>

								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>

	</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js "></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>

<!-- <script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
 -->
<script type="text/javascript" src="/js/custom.js"></script>

</html>