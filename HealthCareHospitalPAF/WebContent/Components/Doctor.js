/**
 * 
 */
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#DoctorIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "DoctorAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPaymentSaveComplete(response.responseText, status);
		}
	});

});

function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divDoctorGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#DoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

// DELET implementation
$(document).on("click", ".btnRemove", function(event) {

	$.ajax({
		url : "DoctorAPI",
		type : "DELETE",
		data : "DoctorID=" + $(this).data("doctorid"),
		dataType : "text",
		complete : function(response, status) {
			onpaymentDeleteComplete(response.responseText, status);
		}
	});

});

function onpaymentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
					$("#DoctorIDSave").val(
							$(this).closest("tr").find('#DoctorIDUpdate')
									.val());
					$("#name").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#address").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#gender").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#specialization").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#registHospital").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#email").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#phone").val(
							$(this).closest("tr").find('td:eq(2)').text());	
					
				});

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// Doctor ID
	if ($("#doctorid").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	// name
	if ($("#name").val().trim() == "") {
		return "Insert Name.";
	}
		// check the address
		if ($("#address").val().trim() == "") {
			return "Insert Address.";
		}

		
	// specialozation-----------------------------
	if ($("#specialization").val().trim() == "") {
		return "Insert Gender.";
	}
	
	if ($("#registHospital").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	// Gender
	if ($("#email").val().trim() == "") {
		return "Insert Name.";
	}
		// check the address
		if ($("#address").val().trim() == "") {
			return "Insert Address.";
		}

		
	// Phone-------------------------
	if ($("#phone").val().trim() == "") {
		return "Insert Gender.";
	}
	
	
	return true;
}
