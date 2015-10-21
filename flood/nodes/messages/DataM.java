package projects.flood.nodes.messages;

import sinalgo.nodes.messages.Message;

public class DataM extends Message {
	private double temp_c;
	private int senderID;

	public DataM(double temp_c, int senderID) {
		super();
		this.temp_c = temp_c;
		this.senderID = senderID;
	}

	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return new DataM(this.temp_c, this.senderID);
	}

	public double getTemp_c() {
		return temp_c;
	}

	public void setTemp_c(double temp_c) {
		this.temp_c = temp_c;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

}
