
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
						checked="checked"><label for="updateRooms">Update
						Rooms</label> <input type="radio" id="updateRooms" name="time-date-room"
						value="updateRooms">
						<label for="updateDate">Update
						Days</label> <input type="radio" id="updateDays" name="time-date-room"
						value="updateDays"> 
				</div>
				<div class="my-4 timeSections" id="timeSections">

					<label for="addModifytTime">Select: </label> <select
						id="addModifytTime" class="addModifytTime">
						<option value="addtime" selected="selected">Add New Time
							Slots</option>
						<option value="modifytime">Modify Existing Time Slots</option>
						<option value="deletetime">Delete Time Slots</option>
					</select>
					<div id="add-ts-section" class="my-4">
						<form action="/admin/add/timeslot" method="POST" id="addTsForm">
							<label for="tstextI">New Time Slot: </label> <input type="text"
								id="tstextI" name="tstextI" placeholder="enter new time slot">
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
							<div
								class="my-2 d-flex justify-center avail-ts-cont flex-wrap tscont">

								<p class="d-none pforapend"></p>
							</div>
						</div>
					</div>
					<div id="modify-ts-section" class="my-4 d-none">
						<h3>Available Time slots:</h3>

						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable tsupdatable tscont">

							<p class="d-none pforapend"></p>
							<!-- <p id="ts1" class="ts-selectable"></p> -->
						</div>

						<form action="/admin/update/timeslot" method="POST"
							id="updateTsForm">
							<label for="modifyTsI">Update to Slot: </label> <input
								type="text" id="modifyTsI" name="modifyTsI"
								placeholder="enter time slot"> <input type="hidden"
								id="tstomodify" value=null />
							<button type="submit" class="btn btn-warning" id="updateTS">Modify</button>
							<div>
								<span id="success-modifyts" class="text-success"></span>
							</div>
							<div>
								<span id="alert-modifyts" class="text-danger"></span>
							</div>
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
							id="deleteTsForm">
							<input type="hidden" id="tstodelete" value=null />
							<button type="submit" class="btn btn-danger" id="deleteTS">Remove</button>
							<div>
								<span id="success-deletets" class="text-success"></span>
							</div>
							<div>
								<span id="alert-deletets" class="text-danger"></span>
							</div>
						</form>
					</div>
				</div>

				<div class="my-4 days-section d-none" id="daysSections">
					<label for="addModifyDay">Select: </label> <select
						id="addModifyDay" class="addModifyDay">
						<option value="addday" selected="selected">Add Day</option>
						<option value="modifyday">Modify Existing Days</option>
						<option value="deleteday">Delete Day</option>
					</select>
					<div id="add-day-section" class="my-4">
						<form action="/admin/day/add" method="POST" id="addDayForm">
							<label for="daytextI">New Day: </label> <input type="text"
								id="daytextI" name="daytextI" placeholder="enter new day">
							<button type="submit" class="btn btn-submit" id="addDay">Add</button>
							<div>
								<span id="success-addday" class="text-success"></span>
							</div>
							<div>
								<span id="alert-addday" class="text-danger"></span>
							</div>
						</form>
						<div class="my-2">
							<h3>Available Days:</h3>
							<div
								class="my-2 d-flex justify-center avail-ts-cont flex-wrap tscont daycont">
								<p class="d-none pforapend"></p>
							</div>
						</div>
					</div>
					<div id="modify-day-section" class="my-4 d-none">
						<h3>Available Days:</h3>
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable day-updatable daycont">

							<p class="d-none pforapend"></p>
							<p id="ts1" class="day-selectable"></p>
						</div>

						<form action="/admin/update/day" method="POST"
							id="updateDayForm">
							<label for="modifyDayI">Update to Day: </label> <input
								type="text" id="modifyDayI" name="modifyDayI"
								placeholder="enter day"> <input type="hidden"
								id="daytomodify" value=null />
							<button type="submit" class="btn btn-warning" id="updateDay">Modify</button>
							<div>
								<span id="success-modifyday" class="text-success"></span>
							</div>
							<div>
								<span id="alert-modifyday" class="text-danger"></span>
							</div>
						</form>
					</div>
					<div id="delete-day-section" class="my-4 d-none">
						<h3>Available Days:</h3>
						<c:set var="daytodelete" value="" scope="page"></c:set>
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable day-deletable tscont daycont">
							<p class="d-none pforapend"></p>
						</div>
						<form action="/admin/delete/day" method="POST" id="deleteDayForm">
							<input type="hidden" id="daytodelete" value=null />
							<button type="submit" class="btn btn-danger" id="deleteDay">Remove</button>
							<div>
								<span id="success-deleteday" class="text-success"></span>
							</div>
							<div>
								<span id="alert-deleteday" class="text-danger"></span>
							</div>
						</form>
					</div>
				</div>

				<div class="my-4 rooms-section d-none" id="roomSections">
					 <label for="addModifyRooms">Select: </label> <select
						id="addModifyRooms" class="addModifytRooms">
						<option value="addroom" selected="selected">Add New Room</option>
						<option value="modifyroom">Modify Existing Room</option>
						<option value="deleteroom">Delete Room</option>
					</select>
					<div id="add-room-section" class="my-4">
						<form action="/admin/add/room" method="POST" id="addRoomForm">
							<label for="roomtextI">New Room Abbr: </label> <input type="text"
								id="roomtextIAbbr" name="roomtextI" placeholder="new room abbr">
								
								<label for="roomtextI">New Room Desc: </label> <input type="text"
								id="roomtextIDesc" name="roomtextI" placeholder="new room desc">
								
							<button type="submit" class="btn btn-submit" id="addRoom">Add</button>
							<div>
								<span id="success-addroom" class="text-success"></span>
							</div>
							<div>
								<span id="alert-addroom" class="text-danger"></span>
							</div>
						</form>
						<div class="my-2">
							<h3>Available Rooms:</h3>
							<div
								class="my-2 d-flex justify-center avail-ts-cont flex-wrap roomcont ">
								<p class="d-none pforapend"></p>
							</div>
						</div>
					</div>
					<div id="modify-room-section" class="my-4 d-none">
						<h3>Available Rooms:</h3>
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable room-updatable roomcont">

							<p class="d-none pforapend"></p>
							<p id="ts1" class="room-selectable"></p>
						</div>

						<form action="/admin/update/room" method="POST"
							id="updateRoomForm">
							<label for="modifyRoomI">Room Abbr: </label> <input
								type="text" id="modifyRoomI" name="modifyRoomI"
								placeholder="enter room abbr"> 
								<label for="roomtextI">New Room Desc: </label> <input type="text"
								id="roomtextIDesc" name="roomtextI" placeholder="new room desc">
								<input type="hidden"
								id="roomtomodify" value=null />
							<button type="submit" class="btn btn-warning" id="updateRoom">Modify</button>
							<div>
								<span id="success-modifyroom" class="text-success"></span>
							</div>
							<div>
								<span id="alert-modifyroom" class="text-danger"></span>
							</div>
						</form>
					</div>
					<div id="delete-room-section" class="my-4 d-none">
						<h3>Available Rooms:</h3>
						
						<div
							class="my-2 d-flex justify-center avail-ts-cont flex-wrap modifiable room-deletable roomcont">
							<p class="d-none pforapend"></p>
						</div>
						<form action="/admin/delete/room" method="POST"
							id="deleteRoomForm">
							<input type="hidden" id="roomtodelete" value=null />
							<button type="submit" class="btn btn-danger" id="deleteRoom">Remove</button>
							<div>
								<span id="success-deleteroom" class="text-success"></span>
							</div>
							<div>
								<span id="alert-deleteroom" class="text-danger"></span>
							</div>
						</form>
					</div>

				</div>
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