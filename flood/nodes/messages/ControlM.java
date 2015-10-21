package projects.flood.nodes.messages;

import sinalgo.nodes.messages.Message;

public class ControlM extends Message {
	private int hops;
	private int sink_id;

	public ControlM(int hops, int sink_id) {
		super();
		this.hops = hops;
		this.sink_id = sink_id;
	}

	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return new ControlM(this.hops, this.sink_id);
	}

	public int getHops() {
		return hops;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}

	public int getSink_id() {
		return sink_id;
	}

	public void setSink_id(int sink_id) {
		this.sink_id = sink_id;
	}

}
