package projects.pingPong.nodes.timers;

import ...

public class PingPongTimer extends Timer {
	private PingPongMessage pkt = null; 
	
	public PingPongTimer(PingPongMessage pkt) {
		this.pkt = pkt;
	}

	@Override
	public void fire() {
		((pingPongNode)node).broadcast(this.pkt);
	}
}
