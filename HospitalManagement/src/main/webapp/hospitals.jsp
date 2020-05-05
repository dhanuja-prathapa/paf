<%--
  Created by IntelliJ IDEA.
  User: Dhanuja
  Date: 5/3/2020
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.healthcare.controller.HospitalController" %>
<%@ page import="com.project.healthcare.model.Hospital" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Views/bootstrap.min.css">
    <link rel="stylesheet" href="Views/style.css">
    <script src="Components/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="Components/hospitals.js"></script>
    <title>Hospital Management</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand mx-auto" href="hospitals.jsp" >Hospital Management</a>
</nav>
<div class="container" style="padding-top: 4em">
    <div class="row">
        <div class="col-lg-4 col-md-6 navbar-dark">
            <h6 class="bg-dark navbar-brand text-center col-12" style="padding: 0.7em; font-size: medium">Create/Update Hospital</h6>
        <div class="p-3 border bg-light rounded" style="margin-top: -8px">
<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
    Hospital ID:
<%--    <input id="hospitalID" name="hospitalID" type="text"--%>
<%--           class="form-control form-control-sm">--%>
    <div id="hosID" name="hosID" class="badge badge-info"></div>
    <br> Hospital name:
    <input id="hospitalName" name="hospitalName" type="text"
           class="form-control form-control-sm" list="reghos" placeholder="Name">
    <datalist id="reghos">
        <%
            HospitalController register = new HospitalController();
            out.print(register.reghos());
        %>
    </datalist>

    <br> Hospital type:
    <input id="hospitalType" name="hospitalType" type="text"
           class="form-control form-control-sm" placeholder="Type" list="regtype" maxlength="32">
    <datalist id="regtype">
        <option>Private Hospital</option>
        <option>National Hospital</option>
        <option>Base Hospital Type A</option>
        <option>Base Hospital Type B</option>
        <option>Divisional Hospital Type A</option>
        <option>Divisional Hospital Type B</option>
    </datalist>
    <br> Hospital description:
    <input id="hospitalDesc" name="hospitalDesc" type="text"
           class="form-control form-control-sm" placeholder="Description" maxlength="64">
    <br> Hospital address:
    <input id="hospitalAddress" name="hospitalAddress" type="text"
           class="form-control form-control-sm" placeholder="Address" maxlength="64">
    <br> Hospital phone:
    <input id="hospitalPhone" name="hospitalPhone" type="number"
           class="form-control form-control-sm" placeholder="Phone" maxlength="14">
    <br>
    <input id="btnSave" name="btnSave" type="button" value="Save"
           class="btn btn-primary">
    <input type="hidden" id="hidHosIDSave" name="hidHosIDSave" value="">
    <button type="reset" id="btnReset" name="btnReset" class="btn btn-outline-dark" value="Reset">Reset</button>
</form>
            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div>
            <br>
        </div>
    </div>
            <div id="divHospitalsGrid" class="col-lg-8 col-md-12">
                <%
                    HospitalController hospitalController = new HospitalController();
                    out.print(hospitalController.getAllHospitals());
                %>
            </div>
        </div>
</div>
<footer>
<div id="configerror" name="configerror" class="alert alert-danger alert-dismissible fade show" style=" position: fixed;left: 0;bottom: 0;width: 100%;text-align: center;margin-bottom: 0px" role="alert">
    <h4 class="alert-heading">Configure </h4>
    <p>You need to run the HospitalAuth service to run this properly.</p>
    <hr>
    <p class="mb-0">Run hospitalAuth on port 8090</p>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
</div>
</footer>
</body>
</html>
