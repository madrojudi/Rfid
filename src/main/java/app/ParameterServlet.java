package app;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParameterServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7979760464963888561L;
	private String address = "192.168.8.120";
	private int port = 1024;
	private String numRfid = "FF";
	
	private MetierController metier;
	private String errorDll = "";
	ParameterModel model;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
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
        model = new ParameterModel();
        model.setAddress(response.split(" ")[0]);
        model.setPort( Integer.parseInt(response.split(" ")[1]));
        model.setAddressIp(response.split(" ")[2]);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("model", model);
		req.getRequestDispatcher("param.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("Save")){
			model.setAddress(request.getParameter("address"));
	        model.setPort( Integer.parseInt(request.getParameter("port")));
	        model.setAddressIp(request.getParameter("addressIp"));
	        save();
		}
		request.setAttribute("model", model);
		request.getRequestDispatcher("param.jsp").forward(request, response);
	}
	
	public void save(){
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(getClass().getResource("/config.ini").getPath())));
			out.write((model.getAddress() + " "+model.getPort() + " "+model.getAddressIp()).getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
