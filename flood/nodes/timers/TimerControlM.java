package projects.flood.nodes.timers;

import projects.flood.nodes.messages.ControlM;
import projects.flood.nodes.nodeImplementations.FloodTreeNode;
import sinalgo.nodes.messages.Message;
import sinalgo.nodes.timers.Timer;

public class TimerControlM extends Timer {
	private ControlM m = null;

	public TimerControlM(ControlM m) {
		super();
		this.m = m;
	}

	@Override
	public void fire() {
		((FloodTreeNode) node).broadcast(m);
	}

}
