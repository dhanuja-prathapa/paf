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
    console.log("save btn pressed");

// Form validation-------------------
//     var status = validateHospitalForm();
//     if (status != true) {
//         $("#alertError").text(status);
//         $("#alertError").show();
//         return;
//     }

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

            $("#divHospitalsGrid").html(resultSet.data);
        }else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show()
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
    $("#hidHosIDSave").val($(this).closest("tr").find('#hidHosIDUpdate').val());
    // $("#hospitalID").val($(this).closest("tr").find('td:eq(0)').text());
    $("#hosID").text($(this).closest("tr").find('td:eq(0)').text());
    $("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());
    $("#hospitalType").val($(this).closest("tr").find('td:eq(2)').text());
    $("#hospitalDesc").val($(this).closest("tr").find('td:eq(3)').text());
    $("#hospitalAddress").val($(this).closest("tr").find('td:eq(4)').text());
    $("#hospitalPhone").val($(this).closest("tr").find('td:eq(5)').text());
    $("#hosID").show();
});

// CLIENT- MODEL=========================================================================
// function validateForm() {
//     var name = document.forms["formHospital"]["hospitalName"].value;
//     var type = document.forms["formHospital"]["hospitalType"].value;
//     console.log(name);
//     if(name == ""){
//         $("#alertError").text("Need to have a valid name").show();
//         return false;
//     }else if (type==""){
//         $("#alertError").text("Need to have a valid type").show();
//         return false;
//     }
// }