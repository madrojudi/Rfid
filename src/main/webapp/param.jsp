<%@page import="app.ParameterModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%ParameterModel model = (ParameterModel)request.getAttribute("model"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RFid</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="author" content="madrojudi">
				
		<link rel="shortcut icon" href="../../docsassets/ico/favicon.png">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css" />		
	</head>
<body>
	<header>
		<div class="row">						
				<div class="col-md-12">
						<nav class="navbar navbar-inverse	">
							<div class="navbar-form pull-right">
								<a href="/Rfid/">
									<button  class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-user"></span> 
										Home
									</button>
								</a>
							</div> 									
						</nav>
					</div>														
			</div>	
	</header>
	<div class ="container ">	
		<div class="panel panel-info" id="conn">
			<br>
			<form action="/Rfid/parameter" method="post">
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Address :</label> <input class="col-md-3" type="text" name="address" value = "${model.getAddress() }"  />
				</div>
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Port : </label><input class="col-md-3" type="text" name="port" value = "${model.getPort() }"/>
				</div>
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Address IP : </label><input class="col-md-3" type="text" name="addressIp" value = "${model.getAddressIp() }"/>
				</div>
				<div class="row">
					<input class="btn btn-primary pull-right" type="submit" value="Save" name="action"/>
				</div>
			</form>
			<br>
		</div>
	</div>
</body>
</html>