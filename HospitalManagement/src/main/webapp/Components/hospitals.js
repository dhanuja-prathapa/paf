$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
    $("#configerror").hide();
});

$(document).on("click","#btnReset",function () {
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    $("#configerror").hide();
    $("#hosID").text("");
    $("#hidHosIDSave").val("");
    $("#formHospital")[0].reset();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
// Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    console.log("save btn pressed");

//Form validation-------------------
    var status = validateForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

// If valid------------------------
    var type = ($("#hidHosIDSave").val() == "") ? "POST" : "PUT";
    console.log(type);
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

        var resultSet = JSON.parse(response);
        console.log(resultSet.status.trim());
        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved. ");
            $("#alertSuccess").show();
            $("#configerror").hide();
            $("#divHospitalsGrid").html(resultSet.data);
        }else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();
            $("#configerror").show();
        }

    } else if (status == "error") {

        $("#alertError").text("Error while saving");
        $("#alertError").show()
    } else {
        $("#alertError").text("Unknown error while saving");
        $("#alertError").show();
    }
    $("#hidHosIDSave").val("");
    $("#hosID").text("");
    $("#formHospital")[0].reset();


}

//DELETE
$(document).on("click", ".btnRemove", function (event) {
    hidealerts();
    $.ajax({
        url: "HospitalsAPI",
        type: "DELETE",
        data: "hospitalID=" + $(this).data("hospitalid"),
        dataType: "text",
        complete: function (response, status) {
            onHospitalDeleteComplete(response.responseText, status)
        }
    });

});

function onHospitalDeleteComplete(response, status) {


    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divHospitalsGrid").html(resultSet.data);

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
    hidealerts();
    $("#hidHosIDSave").val($(this).closest("tr").find('#hidHosIDUpdate').val());
    // $("#hospitalID").val($(this).closest("tr").find('td:eq(0)').text());
    $("#hosID").text($(this).closest("tr").find('td:eq(0)').text());
    $("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());
    $("#hospitalType").val($(this).closest("tr").find('td:eq(2)').text());
    $("#hospitalDesc").val($(this).closest("tr").find('td:eq(3)').text());
    $("#hospitalAddress").val($(this).closest("tr").find('td:eq(4)').text());
    $("#hospitalPhone").val($(this).closest("tr").find('td:eq(5)').text());
    $("#hosID").show();
    $("#hospitalType").focus();
});

// CLIENT- MODEL=========================================================================
function validateForm() {

    if ($("#hospitalName").val() == "") {
        return "Enter Hospital Name";
    }
    if ($("#hospitalType").val() == "") {
        return "Enter Hospital Type";
    }
    if ($("#hospitalDesc").val() == "") {
        return "Enter Hospital Description";
    }
    if ($("#hospitalAddress").val() == "") {
        return "Enter Hospital Address";
    }
    if ($("#hospitalPhone").val().trim() == "") {
        return "Enter Hospital Phone";
    }
    if($("#hospitalPhone").val().length > 14 || $("#hospitalPhone").val().length < 10){
        return "Enter valid phone number";
    }
    return true;
}

function hidealerts() {
    $("#alertError").hide();
    $("#alertSuccess").hide();
    $("#configerror").hide();
}