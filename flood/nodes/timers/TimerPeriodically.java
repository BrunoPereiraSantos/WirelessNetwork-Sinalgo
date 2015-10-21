package projects.flood.nodes.timers;

import projects.flood.nodes.nodeImplementations.FloodTreeNode;
import sinalgo.nodes.timers.Timer;

public class TimerPeriodically extends Timer {
	
	
	
	@Override
	public void fire() {
		((FloodTreeNode) node).sendData();
		((FloodTreeNode) node).sendDataPeriodically();
	}

}
