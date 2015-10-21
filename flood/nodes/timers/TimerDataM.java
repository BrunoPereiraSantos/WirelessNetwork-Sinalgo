package projects.flood.nodes.timers;

import projects.flood.nodes.messages.ControlM;
import projects.flood.nodes.messages.DataM;
import projects.flood.nodes.nodeImplementations.FloodTreeNode;
import sinalgo.nodes.messages.Message;
import sinalgo.nodes.timers.Timer;

public class TimerDataM extends Timer {
	private DataM m = null;

	public TimerDataM(DataM m) {
		super();
		this.m = m;
	}

	@Override
	public void fire() {
		((FloodTreeNode) node).send(m, ((FloodTreeNode) node).getNext_hop());

	}

}
