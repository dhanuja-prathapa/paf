$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();


});

// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
// Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();


// Form validation-------------------
    var status = validateHospitalForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

// If valid------------------------
    var type = ($("#hidHosIDSave").val() == "") ? "POST" : "PUT";

    $.ajax(
        {
            url: "HospitalsAPI",
            type: type,
            data: $("#formHospital").serialize(),
            dataType: "text",
            complete: function (response, status) {

                onHospitalSaveComplete(response.responseText, status)

            }

        });
});

function onHospitalSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JASON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved. ");
            $("#alertSuccess").show();

            $("#divHospitalGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertSuccess").show()
        }

    } else if (status == "error") {

        $("alertError").text("Error while saving");
        $("alertError").show()
    } else {
        $("#alertError").text("Unknown error while saving");
        $("#alertError").show();
    }
    $("#hidHosIDSave").val("");
    $("#formHospital")[0].reset();

}

//DELETE
$(document).on("click", ".btnRemove", function (event) {

    $.ajax({
        url: "HospitalsAPI",
        type: "DELETE",
        data: "doctor_id" + $(this).data(doctor_id),
        dataType: "text",
        complete: function (response, status) {
            onDoctorDeleteComplete(response.responseText, status)
        }
    });

});

function onDoctorDeleteComplete(response, status) {


    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divHospitalGrid").html(resultSet.data);

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
$(document).on("click", ".btnUpdate", function (event) {
    $("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDSave').val());
    $("#doctor_id2").val($(this).closest("tr").find('td:eq(0)').text());
    $("#firstName2").val($(this).closest("tr").find('td:eq(1)').text());
    $("#lastName2").val($(this).closest("tr").find('td:eq(2)').text());
    $("#gender2").val($(this).closest("tr").find('td:eq(3)').text());
    $("#email2").val($(this).closest("tr").find('td:eq(4)').text());
    $("#password2").val($(this).closest("tr").find('td:eq(5)').text());
    $("#joinedDate2").val($(this).closest("tr").find('td:eq(6)').text());
    $("#phone2").val($(this).closest("tr").find('td:eq(7)').text());
    $("#specialization2").val($(this).closest("tr").find('td:eq(8)').text());
    $("#address2").val($(this).closest("tr").find('td:eq(9)').text());
    $("#NIC2").val($(this).closest("tr").find('td:eq(10)').text());
    $("#hospital_id2").val($(this).closest("tr").find('td:eq(11)').text());

});

// CLIENT- MODEL=========================================================================
function validateHospitalForm() {
    // DOCTORID
    if ($("#doctor_id2").val().trim() == "") {
        return "Insert Doctor ID.";
    }
    // FIRSTNAME
    if ($("#firstName2").val().trim() == "") {
        return "Insert First Name";
    }
    // LASTNAME
    if ($("#lastName2").val().trim() == "") {
        return "Insert Last Name";
    }
    // GENDER
    if ($("#gender2").val().trim() == "") {
        return "Insert Gender";
    }
    // EMAIL
    if ($("#email2").val().trim() == "") {
        return "Insert Email";
    }
    // PASSWORD
    if ($("#password2").val().trim() == "") {
        return "Insert Password";
    }
    // JOINEDDATE
    if ($("#joinedDate2").val().trim() == "") {
        return "Insert Joined Date";
    }
    // PHONE
    if ($("#phone2").val().trim() == "") {
        return "Insert Contact Number";
    }
    // SPECIALIZATION
    if ($("#specialization2").val().trim() == "") {
        return "Insert Specialization";
    }
    // ADDRESS
    if ($("#address2").val().trim() == "") {
        return "Insert Address";
    }
    // NIC
    if ($("#NIC2").val().trim() == "") {
        return "Insert NIC";
    }
    // HOSPITALID
    if ($("#hospital_id2").val().trim() == "") {
        return "Insert Hospital ID";
    }


    // is numerical value
    var doctor_id = $("#doctor_id2").val().trim();
    if (!$.isNumeric(doctor_id)) {
        return "Insert a numerical value for Doctor ID.";
    }
    var phone = $("#phone2").val().trim();
    if (!$.isNumeric(phone)) {
        return "Insert a numerical value for Phone.";
    }
    var hospital_id = $("#hospital_id2").val().trim();
    if (!$.isNumeric(hospital_id)) {
        return "Insert a numerical value for Hospital ID.";
    }

    //phone number length
    if (phone.length != 9 || phone.length != 10) {
        return "Insert a valid phone number";
    }

}