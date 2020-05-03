<%--
  Created by IntelliJ IDEA.
  User: Dhanuja
  Date: 5/3/2020
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.project.healthcare.controller.HospitalController"%>
<%@ page import="com.project.healthcare.model.Hospital" %>

<html>
<head>
    <link rel="stylesheet" href="Views/bootstrap.min.css">
    <script src="Components/jquery-3.2.1.min.js"></script>
    <script src="Components/hospitals.js"></script>
    <title>Hospital Management</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-6">
<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
    Hospital ID:
    <input id="hospitalID" name="hospitalID" type="text"
           class="form-control form-control-sm">
    <br> Hospital name:
    <input id="hospitalName" name="hospitalName" type="text"
           class="form-control form-control-sm">
    <br> Hospital type:
    <input id="hospitalType" name="hospitalType" type="text"
           class="form-control form-control-sm">
    <br> Hospital description:
    <input id="hospitalDesc" name="hospitalDesc" type="text"
           class="form-control form-control-sm">
    <br> Hospital address:
    <input id="hospitalAddress" name="hospitalAddress" type="text"
           class="form-control form-control-sm">
    <br>
    <br> Hospital phone:
    <input id="hospitalPhone" name="hospitalPhone" type="text"
           class="form-control form-control-sm">
    <br>
    <input id="btnSave" name="btnSave" type="button" value="Save"
           class="btn btn-primary">
    <input type="hidden" id="hidHosIDSave" name="hidHosIDSave" value="">
</form>
            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div>
            <br>
            <div id="divHospitalsGrid">
                <%
                    HospitalController hospitalController = new HospitalController();
                    out.print(hospitalController.getHospitals());
                %>
            </div>
        </div>
    </div>
</div>


</body>
</html>
