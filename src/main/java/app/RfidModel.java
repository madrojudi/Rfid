package app;

public class RfidModel {
	
	private String epc;
	private String error;
	private String send;
	private String charge;

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getEpc() {
		return epc;
	}

	public void setEpc(String epc) {
		this.epc = epc;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public RfidModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RfidModel(String epc) {
		super();
		this.epc = epc;
	}
	
	
}
