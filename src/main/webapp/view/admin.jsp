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
	<div class=" d-flex text-end p-4">
		<a href="/admin/new/features" class="link-primary">New Feautures <svg
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24"
				height="24" fill="none" stroke="currentColor" stroke-width="2"
				stroke-linecap="round" stroke-linejoin="round">
 		 <path d="M5 12h14M12 5l7 7-7 7" />
		</svg>

		</a>
	</div>
	<div class="dashboard-box">
		<h2 class="text-2xl font-semibold text-gray-800 mb-4">Update
			Routine</h2>

		<form action="/admin/class-routine/update" method="post"
			enctype="multipart/form-data">

			<!-- Radio Options -->
			<div class="mb-4">
				<label class="block text-sm font-medium text-gray-600">Update
					for :</label>
				<div class="mt-1 items-center flex justify-content-center">

					<div class="flex p-2">
						<input type="radio" id="faculty-radio" name="upload-category"
							checked="checked" value="faculty"
							class="focus:ring-parrot-green-500 h-4 w-4 text-parrot-green-600 border-gray-300">
						<label for="faculty-radio"
							class="ml-2 block text-sm text-gray-600">Faculty</label>
					</div>
					<div class="flex p-2 ">
						<input type="radio" id="student-radio" name="upload-category"
							value="student"
							class="focus:ring-parrot-green-500 h-4 w-4 text-parrot-green-600 border-gray-300"
							onclick=""> <label for="student-radio"
							class="ml-2 block text-sm text-gray-600">Student</label>
					</div>
				</div>
			</div>

			<!-- Select Options -->
			<div class="mb-4 d-flex justify-content-center">
				<div class="d-flex flex-row justify-content-center ">
				
					<label for="selectOptions"
						class="block text-sm font-medium text-gray-600 mx-2">Select
						Faculty</label>
					<!-- focus:outline-none focus:ring-parrot-green-500 focus:border-parrot-green-500 -->
					
					<select id="select-faculty" name="select-faculty"
						class="mx-2 text-base  sm:text-sm">
						<option value="" disabled selected>-- Select Option --</option>
						<c:forEach var="faculty" items="${faculties }" >
							<option value="${faculty.id}">${faculty.fName} ${faculty.lName}</option>
						</c:forEach>
					</select>

				</div>
			</div>

			<!-- File Upload -->
			<div class="mb-4">
				<label for="fileUpload"
					class="block text-sm font-medium text-gray-600">Upload File</label>
				<input type="file" id="fileUpload" name="miltPrtFile"
					accept=".xlsx, .xls" class="mt-1 p-2 border rounded-md">

			</div>
			<div>
				<a href="/templates/student-routine-uploder.xlsx"
					class="link-primary" download> +download student template</a>
			</div>
			<div>
				<a href="/templates/faculty-routine-uploder.xlsx"
					class="link-primary" download>+download faculty template</a>
			</div>

			<input type="submit" id="submit-form" class="btn btn-primary mt-3">
			<div>
				<span id="alert-msg" class="text-danger"></span>
			</div>
		</form>
	</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="/js/custom.js"></script>

</html>