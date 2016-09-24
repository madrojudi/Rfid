package app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import com.sun.istack.internal.NotNull;

import UHF.Reader18;

public class MetierController {

	private Reader18 reader = new Reader18();
	
	public MetierController(){
		try {
	           System.load(getClass().getResource("/UHF/UHF_Reader18.dll").getPath());
	       } catch (UnsatisfiedLinkError ex) {
//	         System.err.println("Native code library failed to load.\n" + ex);
	    	   throw new UnsatisfiedLinkError(ex.toString());
	       }
	}
	
	public String readerRfid(int addressRfid, int port, String addressIp){
		
		int[] response = reader.OpenNetPort(addressRfid, port, addressIp);
		if(response[0]==0){
			return null;
		}
		int arr[] = new int[2];
		arr[0] = response[1];
		arr[1] = response[2];
		int[] arr1 = reader.Inventory_G2(arr);
		String ans = "";
        for (int m=0;m<arr1.length;m++)
        {
           System.out.println(arr1[m]);
           ans += Integer.toHexString(arr1[m])+",";
        }
		return ans;
	}
	
	public boolean send(@NotNull String txt){
		if(txt.isEmpty())return false;
		
		byte[] file = txt.getBytes();
		
		URL url;
		DataOutputStream dos = null;
		HttpURLConnection http = null;
		String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String fileName = new Date().getTime()+".txt"; 
        int bytesRead = 0, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        
		try {
			url = new URL("");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true); // Allow Inputs
			http.setDoOutput(true); // Allow Outputs
            http.setUseCaches(false); 
			http.setRequestMethod("POST");
			http.setRequestProperty("", "");
			http.setRequestProperty("ENCTYPE", "multipart/form-data");
			http.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			http.setRequestProperty("uploaded_file", fileName);
			
			dos = new DataOutputStream(http.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);
            
            do {
                bytesAvailable = file.length - bytesRead;
                // create a buffer of  maximum size
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                System.arraycopy(file, 0, buffer, 0, bufferSize);
                bytesRead += bufferSize;
                dos.write(buffer, 0, bufferSize);
            }while (bytesRead < file.length);

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = http.getResponseCode();
            String serverResponseMessage = http.getResponseMessage();
            
            System.out.println("response code : "+serverResponseCode+"\nresponseMessage : "+serverResponseMessage);
            dos.flush();
            dos.close();
            if(serverResponseCode == 200){
            	return true;
            }

            //close the streams //
            
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
}
