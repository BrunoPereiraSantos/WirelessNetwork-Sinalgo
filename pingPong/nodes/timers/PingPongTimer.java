package projects.pingPong.nodes.timers;

import projects.pingPong.nodes.messages.PingPongMessage;
import projects.pingPong.nodes.nodeImplementations.pingPongNode;
import sinalgo.nodes.timers.Timer;

public class PingPongTimer extends Timer {
	private PingPongMessage pkt = null; 
	
	public PingPongTimer(PingPongMessage pkt) {
		this.pkt = pkt;
	}
	
	@Override
	public void fire() {
		((pingPongNode)node).broadcast(pkt);
	}

}
