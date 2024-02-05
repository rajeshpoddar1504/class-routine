
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>

	<div class="dashboard">

		<div class="tab-buttons">
			<div class="tab-button active" onclick="showTab('content1')">Tab1</div>
			<div class="tab-button" onclick="showTab('content2')">Faculty
				Service</div>
			<div class="tab-button" onclick="showTab('content3')">Days &
				Time Slots</div>
		</div>

		<div class="tab-content-container">

			<div id="content1" class="tab-content active-tab">
				<h2>Tab 1 Content</h2>
				<!-- <form>
					<div class="m-4 ">
						<label for="radioOption">Radio Button:</label> <input type="radio"
							id="radioOption" name="radioOption">
					</div>
					<div class="m-4">
						<label for="selectOption">Select Option:</label> <select
							id="selectOption">
							<option value="option1">Option 1</option>
							<option value="option2">Option 2</option>
							<option value="option3">Option 3</option>
						</select>
					</div>
					<button type="submit" class="btn btn-submit">Submit</button>
				</form> -->
			</div>

			<div id="content2" class="tab-content">
				<h2>Add / Delete Faculty</h2>
				<div class="m-4 ">
					<label for="addFaculty">Add Faculty</label> <input type="radio"
						id="addFaculty" id="addFaculty" name="addDelete" checked="checked">
					<label for="deleteRadio" class="text-danger">Delete Faculty</label>
					<input type="radio" id="deleteRadio" name="addDelete"
						value="deleteRadio">
				</div>
				<div class="m-4 d-none" id="delete-section">

					<form action="#" method="POST" id="deleteFacultyForm">
						<div class="m-4">
							<label for="selectFaculty">Select Faculty: </label> <select
								id="selectFaculty">
								<option value="" disabled selected>--select--</option>
							</select>
						</div>
						<button type="submit" id="deleteFacultySubmit"
							class="btn btn-danger">Remove</button>
					</form>
				</div>
				<div id="add-section" class="m-4">
					<form action="/admin/add/user" method="POST" id="addFacultyForm">
						<div class="m-4 ">
							<label for="fname">Faculty first Name</label> <input type="text"
								id="fname" name="fName" placeholder="faculty first name">
						</div>
						<div class="m-4 ">
							<label for="lName">Faculty Last Name</label> <input type="text"
								id="lName" name="lName" placeholder="faculty last name">
						</div>
						<div class="m-4 ">
							<label for="abbrv">Faculty abbreviation</label> <input
								type="text" id="abbrv" name="abbrevation"
								placeholder="short name">
						</div>

						<!-- <div class="m-4 ">
                    <label for="radioOption1">Radio Button 1:</label>
                    <input type="radio" id="radioOption1" name="radioOption2">
                

                    <label for="radioOption2">Radio Button 2:</label>
                    <input type="radio" id="radioOption2" name="radioOption2">
                </div> -->
						<button type="submit" class="btn btn-submit m-2"
							id="addFacultySubmit">Add Faculty</button>
					</form>
				</div>
				<div>
					<span id="success-msg" class="text-success"></span>
				</div>
				<div>
					<span id="alert-msg" class="text-danger"></span>
				</div>
			</div>

			<div id="content3" class="tab-content">
				<h2>Update Days / Time slots</h2>

				<div class="my-4">
					<label for="updateTime">Update Time</label> <input type="radio"
						id="updateTime" name="time-date-room" value="updateTime"
						checked="checked"> <label for="updateDate">Update
						Days</label> <input type="radio" id="updateDate" name="time-date-room"
						value="updateDate"> <label for="updateRooms">Update
						Rooms</label> <input type="radio" id="updateRooms" name="time-date-room"
						value="updateRooms">
				</div>
				<div class="my-4">
					<label for="addModifytTime">Select: </label> <select
						id="addModifytTime" class="addModifytTime">
						<option value="addtime" selected="selected">Add New Time
							Slots</option>
						<option value="modifytime">Modify Existing Time Slots</option>
						<option value="deletetime">Delete Time Slots</option>
					</select>
					<div id="add-ts-section" class="my-4">
						<form action="/admin/add/timeslot" method="POST" id="addTsForm">
							<label for="inputField3">New Time Slot: </label> <input
								type="text" id="tstextI" name="tstextI"
								placeholder="enter new time slot">
							<button type="submit" class="btn btn-submit" id="addTS">Add</button>
							<div>
								<span id="success-addts" class="text-success"></span>
							</div>
							<div>
								<span id="alert-addts" class="text-danger"></span>
							</div>
						</form>
						<div class="my-2">
							<h3>Available Time slots:</h3>
							<div class="my-2 d-flex justify-center avail-ts-cont flex-wrap tscont">
							
							<p class="d-none pforapend"></p>
								<!-- <p id="ts1">9:30-10:30</p>
								<p id='ts2'>10:30-11:30</p>
								<p id="ts3">11:30-12:30</p>
								<p id="ts3">1:00-2:00</p>
								<p id="ts3">2:00-3:30</p> -->
							</div>
						</div>
					</div>
					<div id="modify-ts-section" class="my-4 d-none">
						<h3>Available Time slots:</h3>
						
						
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable tsupdatable tscont">
							
							<p class="d-none pforapend"></p>
							 <p id="ts1" class="ts-selectable">9:30-10:30</p>
						</div>

						<form action="/admin/update/timeslot" method="POST"
							id="updateTsForm">
							<label for="inputField3">Update to Slot: </label> <input
								type="text" id="inputField3" name="inputField3"
								placeholder="enter time slot">
								<input type="hidden" id="tstomodify" value="" />
							<button type="submit" class="btn btn-warning" id="addTS">Modify</button>
						</form>
					</div>
					<div id="delete-ts-section" class="my-4 d-none">
						<h3>Available Time slots:</h3>
						<c:set var="tstodelete" value="" scope="page"></c:set>
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable tsdeletable tscont">
							<p class="d-none pforapend"></p>
						</div>
						<form action="/admin/delete/timeslot" method="POST"
							id="updateTsForm">
							<input type="hidden" id="tstodelete" value="" />
							<button type="submit" class="btn btn-danger" id="deleteTS">Remove</button>
						</form>
					</div>
					<!-- <div class="m-4">
							
							
							<label for="inputField3">New Time Slot: </label> 
							<input type="text" id="inputField3" name="inputField3">
						</div> -->

				</div>
				<!-- <form>
					<label for="selectOption2">Select Option:</label> <select
						id="selectOption2">
						<option value="option1">Option 1</option>
						<option value="option2">Option 2</option>
						<option value="option3">Option 3</option>
					</select> <label for="inputField3">Input Field:</label> <input type="text"
						id="inputField3" name="inputField3">
				</form> -->
			</div>

		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="/js/admin.js"></script>

</body>
</html>