<%@page import="app.RfidModel"%>
<%RfidModel model = (RfidModel)request.getAttribute("model"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>RFid</title>
		<meta name="author" content="madrojudi">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="css/bootstrap.css" rel="stylesheet">
	    <link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- <link rel="stylesheet" href="css/style.css" />	 -->	
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
		<br>
		<label style="color:blue;"> <%=model.getCharge()%> </label>
		<div class="panel panel-info" id="conn">
			
	        	<div class="panel-body">
	        		<form action="/Rfid/" method="post">
	        			<input class="btn btn-primary col-md-2" type="submit" value="Reader" name="action"/>
	        			<textarea class="col-md-8 well col-lg-offset-1" name="area" rows="10" cols="150"><%=model.getEpc() %></textarea>
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