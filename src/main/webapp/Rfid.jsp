<%@page import="app.RfidModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%RfidModel model = (RfidModel)request.getAttribute("model"); %>
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
								<a href="/Rfid/parameter">
									<button  class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-user"></span> 
										Parameters
									</button>
								</a>
									
							</div> 									
						</nav>
					</div>														
			</div>	
	</header>
	<div class ="container ">	
		<label style="color:red;"> <%=model.getError()%> </label>
		<div class="panel panel-info" id="conn">
			
	        	<div class="panel-body">
	        		<form action="/Rfid/" method="post">
	        			<input class="btn btn-primary col-md-2" type="submit" value="Reader" name="action"/>
	        			<textarea class="col-md-8 well col-lg-offset-1" name="area" >
	        				<%=model.getEpc() %>
	        			</textarea>
		        		<br/>
		        		
	        		</form>
	        		
	        		
					
	        	</div>
				
				 
	        </div>
	        <div >
		        <form action="/Rfid/" method="post">
		        	<input class="btn btn-primary col-md-2 pull-right" type="submit" value="Send" name="action"/>
	        	</form>
	        </div>
	</div>
</body>
</html>