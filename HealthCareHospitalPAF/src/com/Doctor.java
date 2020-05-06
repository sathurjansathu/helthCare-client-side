
//IT18086058


package com;

import java.sql.*;

public class Doctor {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctordb", "root", "Sathu@0096");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public String readDoctor()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Doctor Name</th><th>Address</th><th>Gender</th><th>Specialization</th><th>RegisteredHospital</th><th>Email</th><th>Phone</th><th>Update</th><th>Remove</th></tr>"; 

String query = "select * from doctor";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
	  String DoctorID = rs.getString("DoctorID");
	  String Name = rs.getString("Name");
	  String Address = rs.getString("Address");
	  String Gender = rs.getString("Gender");
	  String Specialization = rs.getString("Specialization");
	  String RegisteredHospital = rs.getString("RegisteredHospital");
	  String Email = rs.getString("Email");
	  String Phone = Double.toString(rs.getDouble("Phone"));
	  
 
 // Add into the html table
 output += "<tr><td><input id='DoctorIDUpdate' name='DoctorIDUpdate' type='hidden' value='" + DoctorID + "'>" + Name + "</td>";
 output += "<td>" + Address + "</td>";
 output += "<td>" + Gender + "</td>";
 output += "<td>" + Specialization + "</td>";
 output += "<td>" + RegisteredHospital + "</td>";
 output += "<td>" + Email + "</td>";
 output += "<td>" + Phone + "</td>";
 
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-doctorid='"
 + DoctorID + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Doctor.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String insertDoctor(String DoctorID, String Name, String Address, String Gender, String Specialization,String RegisteredHospital, String Email, String Phone) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
		// create a prepared statement
		String query = " insert into doctor(DoctorID,Name,Address,Gender,Specialization,RegisteredHospital,Email,Phone)  values (?, ?, ?, ?, ?, ?, ? ,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, Name);
		preparedStmt.setString(3, Address);
		preparedStmt.setString(4, Gender);
		preparedStmt.setString(5, Specialization);
		preparedStmt.setString(6, RegisteredHospital);
		preparedStmt.setString(7, Email);
		preparedStmt.setDouble(8, Double.parseDouble(Phone));

 // execute the stat
 preparedStmt.execute();
 con.close();
 String newDoctor = readDoctor();
 output = "{\"status\":\"success\", \"data\": \"" +
 newDoctor + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateDoctor(String doctorid, String name, String address, String gender, String special,
		String registhospital, String email, String phone)
	  {
	  String output = "";
	  try
	  {
	  Connection con = connect();
	  if (con == null)
	  {return "Error while connecting to the database for updating."; }
	  // create a prepared statement
	  String query = "UPDATE doctor SET  Name=?,Address=?,Gender=?,Specialization=?,RegisteredHospital=?,Email=?,Phone=?  WHERE DoctorID=?"; 

	  PreparedStatement preparedStmt = con.prepareStatement(query);
	  // binding values
	  preparedStmt.setString(1, name);
	  preparedStmt.setString(2, address);
	  preparedStmt.setString(3, gender);
	  preparedStmt.setString(4, special);
	  preparedStmt.setString(5, registhospital);
	  preparedStmt.setString(6, email);
	  preparedStmt.setDouble(7, Double.parseDouble(phone));
	  preparedStmt.setInt(8, Integer.parseInt(doctorid));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newDoctor = readDoctor();
 output = "{\"status\":\"success\", \"data\": \"" +
 newDoctor + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String deleteDoctor(String doctorid) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for deleting.";
		}
		// create a prepared statement
		String query = "delete from doctor where DoctorID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, doctorid);
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newDoctor = readDoctor();
 output = "{\"status\":\"success\", \"data\": \"" +
 newDoctor + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Doctor.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}
