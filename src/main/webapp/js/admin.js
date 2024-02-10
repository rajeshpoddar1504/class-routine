function showTab(tabId) {
	// Hide all tabs
	document.querySelectorAll('.tab-content').forEach(tab => {
		tab.classList.remove('active-tab');
	});

	// Show the selected tab
	document.getElementById(tabId).classList.add('active-tab');

	//     // Remove 'active' class from all tab buttons
	document.querySelectorAll('.tab-button').forEach(button => {
		button.classList.remove('active');
	});
}

$('#facultyListBook, #dayListBook, #batchListBook').on('change', function(e) {
	
	e.preventDefault();
	$('#book-alert-msg').text('');
	$('#book-success-msg').text('');
		
	var selectedFaculty = $('#facultyListBook option:selected').attr("value");
	var selectedDay = $('#dayListBook option:selected').attr("value");
	var selectedBatch = $('#batchListBook option:selected').attr("value");

	if (selectedFaculty != '' && selectedDay != '' && selectedBatch != '') {
		$.ajax({
			url: '/admin/timeslot/get/filter',
			data: { facultyAbbr: selectedFaculty, selectedDay: selectedDay, selectedBatch: selectedBatch },
			type: 'post'
		}).done(function(result) {

			$("#timeSlotListBook option[value!='']").remove();
			$.each(result, function(i, timSlot) {
				$('#timeSlotListBook').append($("<option></option>")
					.attr("value", timSlot.time_slot)
					.html(timSlot.time_slot));
			});

		})
			.fail(function() {
				//$('#alert-msg').text('error occurred!!');
			});
	}
});



$('#timeSlotListBook').on('change', function(e) {

	e.preventDefault();
	var selectedFaculty = $('#facultyListBook option:selected').attr("value");
	var selectedDay = $('#dayListBook option:selected').attr("value");
	var selectedBatch = $('#batchListBook option:selected').attr("value");
	var selectedTime = $('#timeSlotListBook option:selected').attr("value");

	if (selectedFaculty != '' && selectedDay != '' && selectedBatch != '' && selectedTime != '') {
		$.ajax({
			url: '/admin/rooms/get/filter',
			data: { facultyAbbr: selectedFaculty, selectedDay: selectedDay, selectedBatch: selectedBatch, selectedTime: selectedTime },
			type: 'post'
		}).done(function(result) {

			$("#roomListBook option[value!='']").remove();
			$.each(result, function(i, room) {
				//console.log(room);
				$('#roomListBook').append($("<option></option>")
					.attr("value", room.room_abbr)
					.html(room.room_desc));
			});

		})
			.fail(function() {
				//$('#alert-msg').text('error occurred!!');
			});
	}
});


$('#bookSchedule').click(function(e) {
	e.preventDefault();
	
	e.preventDefault();
	var selectedFaculty = $('#facultyListBook option:selected').attr("value");
	var selectedDay = $('#dayListBook option:selected').attr("value");
	var selectedBatch = $('#batchListBook option:selected').attr("value");
	var selectedTime = $('#timeSlotListBook option:selected').attr("value");
	var selectedRoom = $('#roomListBook option:selected').attr("value");
	var courseCode = $('#coursecode').val();
	if(selectedFaculty != '' && selectedDay != '' && selectedBatch != '' && selectedTime != ''&&selectedRoom!=''){
		
	$.ajax({
		url: '/admin/book/schedule',
		data: { facultyAbbr: selectedFaculty, selectedDay: selectedDay, selectedBatch: selectedBatch, selectedTime: selectedTime,selectedRoom:selectedRoom,courseCode:courseCode},
		type: 'post'
	}).done(function() {
		//$("#selectFaculty option[value=='selectedFaculty']").remove();
		console.log('success');
		$('#book-alert-msg').text('');
		$('#book-success-msg').text('success!!');
		
		$('#addFacultyForm').each(function() {
			this.reset();
		});

	})
		.fail(function() {
			$('#book-success-msg').text('');
			$('#alert-msg').text('error occurred!!');
		});

	}
});

$('input[type=radio][name=time-date-room]').change(function() {
	if (this.value == 'updateDays') {
		$('#timeSections').addClass('d-none');
		$('#roomSections').addClass('d-none');
		$('#daysSections').removeClass('d-none');
		$('#batchSections').addClass('d-none');
		
	} else if (this.value == 'updateRooms') {
		$('#timeSections').addClass('d-none');
		$('#roomSections').removeClass('d-none');
		$('#daysSections').addClass('d-none');
		$('#batchSections').addClass('d-none');
	} else if(this.value == 'updateBatch'){
		
		$('#batchSections').removeClass('d-none');
		$('#timeSections').addClass('d-none');
		$('#roomSections').addClass('d-none');
		$('#daysSections').addClass('d-none');
		
	}else{
		
		$('#timeSections').removeClass('d-none');
		$('#roomSections').addClass('d-none');
		$('#daysSections').addClass('d-none');
		$('#batchSections').addClass('d-none');

	}
});


$('input[type=radio][name=addDelete]').change(function() {
	if (this.value == 'deleteRadio') {
		$('#add-section').addClass('d-none');
		$('#delete-section').removeClass('d-none');
		$.ajax({
			url: "/admin/get/user", success: function(result) {
				//	console.log(result[0]);
				$("#selectFaculty option[value!='']").remove();

				$.each(result, function(i, faculty) {
					$('#selectFaculty').append($("<option></option>")
						.attr("value", faculty.id)
						.html(faculty.faculty_fname + ' ' + faculty.faculty_lname));

				});
			}
		});
	} else {
		$('#delete-section').addClass('d-none');
		$('#add-section').removeClass('d-none');

	}
});


$('#deleteFacultySubmit').click(function(e) {
	e.preventDefault();
	var selectedFaculty = $('#selectFaculty option:selected').attr("value");
	alert(selectedFaculty);
	$.ajax({
		url: '/admin/delete/user',
		data: { empId: selectedFaculty },
		type: 'post'
	}).done(function() {
		$("#selectFaculty option[value=='selectedFaculty']").remove();
		$('#alert-msg').text('');
		$('#success-msg').text('success!!');
		$('#addFacultyForm').each(function() {
			this.reset();
		});

	})
		.fail(function() {
			$('#alert-msg').text('error occurred!!');
		});

});


$('#addFacultySubmit').click(function(e) {
	if ($('#abbrv').val() == '' || $('#lName').val() == '' || $('#fname').val() == '') {
		e.preventDefault();
		$('#alert-msg').text('provide all fields');
	} else {
		e.preventDefault();

		var formData = new FormData(document.getElementById('addFacultyForm'));

		$.ajax({
			url: '/admin/add/user',
			data: formData,
			type: 'POST',
			contentType: false,
			processData: false
		}).done(function() {
			$('#alert-msg').text('');
			$('#success-msg').text('success!!');
			$('#addFacultyForm').each(function() {
				this.reset();
			});
			//$('input[type=text]').reset();
		})
			.fail(function() {
				$('#alert-msg').text('error occurred!!');
			});
	}
});


$('#addModifytTime').on('change', function() {

	if (this.value == 'addtime') {
		$('#modify-ts-section').addClass('d-none');
		$('#delete-ts-section').addClass('d-none');
		$('#add-ts-section').removeClass('d-none');
	} else if (this.value == 'modifytime') {
		$('#add-ts-section').addClass('d-none');
		$('#delete-ts-section').addClass('d-none');
		$('#modify-ts-section').removeClass('d-none');

	} else if (this.value == 'deletetime') {
		$('#add-ts-section').addClass('d-none');
		$('#modify-ts-section').addClass('d-none');
		$('#delete-ts-section').removeClass('d-none');

	}

	$.ajax({
		url: '/admin/timeslot/get',
		type: 'GET',
	}).done(function(slotList) {

		$(".tscont p").remove();
		slotList.forEach(slot => {
			$('.tscont')
				.append("<p id='" + slot.id + "' class='ts-selectable'>" + slot.time_slot + "</p>");
		})

	})
		.fail(function() {
			$('#alert-msg').text('error occurred!!');
		});

});

$('#addModifyDay').on('change', function() {

	if (this.value == 'addday') {
		$('#modify-day-section').addClass('d-none');
		$('#delete-day-section').addClass('d-none');
		$('#add-day-section').removeClass('d-none');
	} else if (this.value == 'modifyday') {
		$('#add-day-section').addClass('d-none');
		$('#delete-day-section').addClass('d-none');
		$('#modify-day-section').removeClass('d-none');

	} else if (this.value == 'deleteday') {
		$('#add-day-section').addClass('d-none');
		$('#modify-day-section').addClass('d-none');
		$('#delete-day-section').removeClass('d-none');

	}

	$.ajax({
		url: '/admin/days/get',
		type: 'GET',
	}).done(function(dayList) {


		$(".daycont p").remove();
		dayList.forEach(day => {
			console.log(day.day_abbr);
			$('.daycont')
				.append("<p id='" + day.id + "' class='day-selectable'>" + day.day_abbr + "</p>");
		})

	})
		.fail(function() {
			$('#alert-msg').text('error occurred!!');
		});

});

$('#addModifyRooms').on('change', function() {

	if (this.value == 'addroom') {
		$('#modify-room-section').addClass('d-none');
		$('#delete-room-section').addClass('d-none');
		$('#add-room-section').removeClass('d-none');
	} else if (this.value == 'modifyroom') {
		$('#add-room-section').addClass('d-none');
		$('#delete-room-section').addClass('d-none');
		$('#modify-room-section').removeClass('d-none');

	} else if (this.value == 'deleteroom') {
		$('#add-room-section').addClass('d-none');
		$('#modify-room-section').addClass('d-none');
		$('#delete-room-section').removeClass('d-none');

	}

	$.ajax({
		url: '/admin/rooms/get',
		type: 'GET',
	}).done(function(roomList) {

		$(".roomcont p").remove();
		roomList.forEach(room => {
			$('.roomcont')
				.append("<p id='" + room.id + "' class='room-selectable'>" + room.room_abbr + "</p>");
		})

	})
		.fail(function() {
			$('#alert-msg').text('error occurred!!');
		});

});

$('#addModifyBatch').on('change', function() {

	if (this.value == 'addbatch') {
		$('#modify-batch-section').addClass('d-none');
		$('#delete-batch-section').addClass('d-none');
		$('#add-batch-section').removeClass('d-none');
	} else if (this.value == 'modifybatch') {
		$('#add-batch-section').addClass('d-none');
		$('#delete-batch-section').addClass('d-none');
		$('#modify-batch-section').removeClass('d-none');

	} else if (this.value == 'deletebatch') {
		$('#add-batch-section').addClass('d-none');
		$('#modify-batch-section').addClass('d-none');
		$('#delete-batch-section').removeClass('d-none');
	}

	$.ajax({
		url: '/admin/batch/get',
		type: 'GET',
	}).done(function(batchList) {

		$(".batchcont p").remove();
		batchList.forEach(batch => {
			$('.batchcont')
				.append("<p id='" + batch.batch_abbr + "' class='batch-selectable'>" + batch.batch_desc + "</p>");
		})

	})
		.fail(function() {
			$('#alert-msg').text('error occurred!!');
		});

});

$(document).on('click', '.ts-selectable', function() {
	selectedTsModiy = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
});

$(document).on('click', '.tsdeletable p', function() {
	selectedTsDelete = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
	$('#tstodelete').val(selectedTsDelete);
	//console.log('to delete'+selectedTsDelete);
});

$(document).on('click', '.tsupdatable p', function() {
	selectedTsModiy = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
	$('#tstomodify').val(selectedTsModiy);
});


$(document).on('click', '.day-selectable p', function() {
	selectedDayModiy = $(this).attr('id');
	$('.day-selectable').removeClass('day-selected');
	$(this).addClass('day-selected');
});

$(document).on('click', '.day-deletable p', function() {
	selectedDayDelete = $(this).attr('id');
	$('.day-selectable').removeClass('day-selected');
	$(this).addClass('day-selected');
	$('#daytodelete').val(selectedDayDelete);
	//console.log('to delete'+selectedTsDelete);
});

$(document).on('click', '.day-updatable p', function() {
	selectedDayModiy = $(this).attr('id');
	$('.day-selectable').removeClass('day-selected');
	$(this).addClass('day-selected');
	$('#daytomodify').val(selectedDayModiy);
});



$(document).on('click', '.room-selectable p', function() {
	selectedRoomModiy = $(this).attr('id');
	$('.room-selectable').removeClass('room-selected');
	$(this).addClass('room-selected');
});

$(document).on('click', '.room-deletable p', function() {
	selectedRoomDelete = $(this).attr('id');
	console.log(selectedRoomDelete);
	$('.room-selectable').removeClass('room-selected');
	$(this).addClass('room-selected');
	$('#roomtodelete').val(selectedRoomDelete);
	//console.log('to delete'+selectedTsDelete);
});

$(document).on('click', '.room-updatable p', function() {
	selectedRoomModiy = $(this).attr('id');
	
	$('.room-selectable').removeClass('room-selected');
	$(this).addClass('room-selected');
	$('#roomtomodify').val(selectedRoomModiy);
});

$(document).on('click', '.batch-deletable p', function() {
	selectedBatchModiy = $(this).attr('id');
	
	$('.batch-selectable').removeClass('room-selected');
	$(this).addClass('room-selected');
	$('#batchtodelete').val(selectedBatchModiy);
	
	
});


$('#addTS').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#tstextI').val();
	if ($('#tstextI').val() == '') {
		$('#alert-addts').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/timeslot/add',
			data: { timeSlot: inputTxtVal },
			type: 'post'
		}).done(function() {

			$('#alert-addts').text('');

			$('#success-addts').text('success!!');
			$('#addTsForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-addts').text('error occurred!!');
		});
	}
});


$('#updateTS').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#tstomodify').val();
	var newTs = $('#modifyTsI').val();
	//console.log(inputTxtVal,newTs)
	if ($('#modifyTsI').val() == '') {
		$('#alert-modifyts').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/timeslot/modify',
			data: { timeSlot: inputTxtVal, newTimeSlot: newTs },
			type: 'post'
		}).done(function() {

			$('#alert-modifyts').text('');
			$('#success-modifyts').text('success!!');
			$('#updateTsForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-modifyts').text('error occurred!!');
		});
	}
});


$('#deleteTS').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#tstodelete').val();

	//console.log(inputTxtVal,newTs)
	if ($('#tstodelete').val() == '') {
		$('#alert-modifyts').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/timeslot/delete',
			data: { timeSlot: inputTxtVal },
			type: 'post'
		}).done(function() {

			$('#alert-deletets').text('');
			$('#success-deletets').text('success!!');
			$('#deleteTsForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-deletets').text('error occurred!!');
		});
	}
});


$('#addDay').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#daytextI').val();
	if ($('#daytextI').val() == '') {
		$('#alert-addday').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/day/add',
			data: { day: inputTxtVal },
			type: 'post'
		}).done(function() {
			$('#alert-addday').text('');
			$('#success-addday').text('success!!');
			$('#addDayForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-addday').text('error occurred!!');
		});
	}
});


$('#updateDay').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#daytomodify').val();
	var newDay = $('#modifyDayI').val();

	//console.log(inputTxtVal,newTs)
	if ($('#modifyDayI').val() == '') {
		$('#alert-modifyday').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/day/modify',
			data: { dayToModify: inputTxtVal, newDay: newDay },
			type: 'post'
		}).done(function() {
			$('#alert-modifyday').text('');
			$('#success-modifday').text('success!!');
			$('#updateDayForm').each(function() {
				this.reset();
			});
		}).fail(function() {
			$('#alert-modifyday').text('error occurred!!');
		});
	}
});


$('#deleteDay').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#daytodelete').val();

	//console.log(inputTxtVal,newTs)
	if ($('#daytodelete').val() == '') {
		$('#alert-deleteday').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/day/delete',
			data: { daysId: inputTxtVal },
			type: 'post'
		}).done(function() {

			$('#alert-deleteday').text('');
			$('#success-deleteday').text('success!!');
			$('#deleteDayForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-deleteday').text('error occurred!!');
		});
	}
});


$('#addRoom').click(function(e) {
	e.preventDefault();
	var inputTxtValAbbr = $('#roomtextIAbbr').val();
	var inputTxtValDesc = $('#roomtextIDesc').val();
	if ($('#roomtextI').val() == '') {
		$('#alert-addroom').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/rooms/add',
			data: { newRoomAbbr: inputTxtValAbbr, newRoomDesc: inputTxtValDesc },
			type: 'post'
		}).done(function() {

			$('#alert-addroom').text('');

			$('#success-addroom').text('success!!');
			$('#addRoomForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-addroom').text('error occurred!!');
		});
	}
});

$('#addBatch').click(function(e) {
	e.preventDefault();
	var inputTxtValAbbr = $('#batchtextI').val();
	var inputTxtValDesc = $('#batchtextIDesc').val();
	if (inputTxtValAbbr == ''||inputTxtValDesc=='') {
		$('#alert-addbatch').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/batch/add',
			data: { newBatchAbbr: inputTxtValAbbr, newBatchDesc: inputTxtValDesc },
			type: 'post'
		}).done(function() {

			$('#alert-addbatch').text('');

			$('#success-addbatch').text('success!!');
			$('#addBatchForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-addbatch').text('error occurred!!');
		});
	}
});

$('#updateRoom').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#roomtomodify').val();
	var inputTxtValAbbr = $('#roomtextIAbbr').val();
	var inputTxtValDesc = $('#roomtextIDesc').val();

	if ($('#modifyRoomI').val() == '') {
		$('#alert-modifyroom').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/room/modify',
			data: { roomId: inputTxtVal, newRoomAbbr: inputTxtValAbbr, newRoomDesc: inputTxtValDesc },
			type: 'post'
		}).done(function() {
			$('#alert-modifyroom').text('');
			$('#success-modifyroom').text('success!!');
			$('#updateRoomForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-modifyroom').text('error occurred!!');
		});
	}
});


$('#deleteRoom').click(function(e) {

	e.preventDefault();
	var inputTxtVal = $('#roomtodelete').val();

	if ($('#roomtodelete').val() == null) {
		$('#alert-deleteroom').text('provide input!!')
	} else {
		$.ajax({
			url: '/admin/room/delete',
			data: { roomId: inputTxtVal },
			type: 'post'
		}).done(function() {

			$('#alert-deleteroom').text('');
			$('#success-deleteroom').text('success!!');
			$('#deleteRoomForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-deleteroom').text('error occurred!!');
		});
	}
});


$('#deleteBatch').click(function(e) {
	e.preventDefault();
	var batchtodelete = $('#batchtodelete').val();
	console.log(batchtodelete);
	if (batchtodelete == null || batchtodelete =='') {
		$('#alert-deletebatch').text('select batch!!')
	} else {
		$.ajax({
			url: '/admin/batch/delete',
			data: { batchAbbr: batchtodelete },
			type: 'post'
		}).done(function() {

			$('#alert-deletebatch').text('');
			$('#success-deletebatch').text('success!!');
			$('#deleteBatchForm').each(function() {
				this.reset();
			});

		}).fail(function() {
			$('#alert-deletebatch').text('error occurred!!');
		});
	}
});
