<%@page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Doctor.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<form id="formDoctor" name="formDoctor" method="post"
					action="Doctor.jsp">


					DoctorID : <input id="doctorid" name="doctorid" type="text" class="form-control form-control-sm"> <br> 
					
					Name: <input id="name" name="name" type="text" class="form-control form-control-sm"> <br>
					
					 Address: <input id="address" name="address" type="text"	class="form-control form-control-sm"> <br> 
					
					Gender: <input id="gender" name="gender" type="text"	class="form-control form-control-sm"> <br>

					Specialization: <input id="specialization" name="specialization" type="text" class="form-control form-control-sm"> <br>

					Registered Hospital: <input id="registHospital"	name="registHospital" type="text" class="form-control form-control-sm"> <br>
					
					 Email: <input
						id="email" name="email" type="text"	class="form-control form-control-sm"> <br> 
					
					Phone: <input
						id="phone" name="phone" type="text"	class="form-control form-control-sm"> <br> 
						
						
						<input
						id="btnSave" name="btnSave" type="button" value="Save"	class="btn btn-primary"> <input type="hidden"
						id="DoctorIDSave" name="DoctorIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divDoctorGrid">
					<%
						Doctor DocObj = new Doctor();
						out.print(DocObj.readDoctor());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>