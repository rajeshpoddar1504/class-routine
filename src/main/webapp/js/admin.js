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

	// Add 'active' class to the clicked tab button
	//  document.querySelector('.tab-button[data-tab="${tabId}"]').classList.add('active');

	//console.log($(this).attr("class"));

}



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
		var selectedFaculty=$('#selectFaculty option:selected').attr("value");
		alert(selectedFaculty);
		$.ajax({
			url: '/admin/delete/user',
			data: {empId:selectedFaculty},
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
	
//  console.log( this.value );
	//var selectedOpt=this.value;
	if(this.value=='addtime'){
		$('#modify-ts-section').addClass('d-none');
		$('#delete-ts-section').addClass('d-none');
		$('#add-ts-section').removeClass('d-none');
		
		
		
	}else if(this.value=='modifytime'){
		$('#add-ts-section').addClass('d-none');
		$('#delete-ts-section').addClass('d-none');
		$('#modify-ts-section').removeClass('d-none');
		
	}else if(this.value=='deletetime'){
		$('#add-ts-section').addClass('d-none');
		$('#modify-ts-section').addClass('d-none');
		$('#delete-ts-section').removeClass('d-none');
		
	}
	
	$.ajax({
			url: '/admin/timeslot/get',
			type: 'GET',
		}).done(function(slotList) {
			//$('.tscont').remove
			$(".tscont p").remove();
			slotList.forEach(slot=>{
						$('.tscont')
						.append("<p id='"+slot.id+"' class='ts-selectable'>"+slot.time_slot+"</p>");
						
			})
			//$('input[type=text]').reset();
		})
			.fail(function() {
				$('#alert-msg').text('error occurred!!');
			});
			
});



/*$('.tscont p').click(function() { 
	alert(" ");
   selectedTsModiy = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
});
*/
$(document).on('click','.ts-selectable',function(){
	selectedTsModiy = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
	});
	


$(document).on('click','.tsdeletable p',function(){
	selectedTsDelete = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
	$('#tstomodify').val(selectedTsDelete);
	console.log('to delete'+selectedTsDelete);
	});

$(document).on('click','.tsupdatable p',function(){
	selectedTsModiy = $(this).attr('id');
	$('.ts-selectable').removeClass('ts-selected');
	$(this).addClass('ts-selected');
	$('#tstomodify').val(selectedTsModiy);
	console.log('to modify'+selectedTsModiy);
	});

$('#addTS').click(function(e) {
	
	e.preventDefault();
	var inputTxtVal=$('#tstextI').val();
	if ($('#tstextI').val() == '') {
		$('#alert-addts').text('provide input!!')
		}else{
			$.ajax({
			url: '/admin/timeslot/add',
			data: {timeSlot:inputTxtVal},
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