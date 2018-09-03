package projects.pingPong.nodes.messages;

import sinalgo.nodes.messages.Message;

public class PingPongMessage extends Message {

	private int r, g, b;

	public PingPongMessage(int i, int j, int k) { this.r = i; this.g = j; this.b = k; }

	@Override
	public Message clone() { return new PingPongMessage(this.r, this.g, this.b); }

	/*Getters and Setters*/
}
