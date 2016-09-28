package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7979760464963888561L;
	private String address = "192.168.8.120";
	private int port = 1024;
	private int numRfid = 255;
	
	private MetierController metier;
	private String errorDll = "";
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			metier = new MetierController();
			BufferedReader in = new BufferedReader(
	                new InputStreamReader(getClass().getResourceAsStream("/config.ini")));
	        String inputLine;
	        String response = "";
	        try {
	            while((inputLine=in.readLine())!=null){
	                response += inputLine;
	            }
	        } catch (IOException ex) {
	            Logger.getLogger(ParameterServlet.class.getName()).log(Level.SEVERE, null, ex);
	        }finally{
	        	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        numRfid = (Integer.parseInt(response.split(" ")[0]));
	        port = ( Integer.parseInt(response.split(" ")[1]));
	        address = (response.split(" ")[2]);
	       } catch (UnsatisfiedLinkError ex) {
	         errorDll = ex.getMessage();
	         System.err.println(""+ex);
	       }
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RfidModel model = new RfidModel();
		model.setError(errorDll);
		model.setSend(" ");
		req.setAttribute("model", model);
		req.getRequestDispatcher("Rfid.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RfidModel model = new RfidModel();
		String action = request.getParameter("action");
		String area = request.getParameter("area");
		model.setEpc(area);
		if(action.equals("Reader")){
			model.setEpc(metier.readerRfid(numRfid, port, address));
			model.setSend("");
		}else if(action.equals("Send")){
			if(area==null){
				model.setSend("No data to send!");
			}else{
				if(metier.send(area)){
					model.setSend("Upload file with success!");
				}else{
					model.setSend("Upload file failed!");
				}
			}
			
		}
		model.setError(errorDll);
		request.setAttribute("model", model);
		request.getRequestDispatcher("Rfid.jsp").forward(request, response);
	}

}
