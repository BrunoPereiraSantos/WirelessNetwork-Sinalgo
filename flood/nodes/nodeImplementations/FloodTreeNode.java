package projects.flood.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import projects.flood.nodes.messages.ControlM;
import projects.flood.nodes.messages.DataM;
import projects.flood.nodes.timers.TimerControlM;
import projects.flood.nodes.timers.TimerDataM;
import projects.flood.nodes.timers.TimerPeriodically;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;
import sinalgo.tools.statistics.UniformDistribution;

public class FloodTreeNode extends Node {

	private Node next_hop = null;
	private int distance = Integer.MAX_VALUE;
	private int sink_id = -1;

	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message m = inbox.next();

			if (m instanceof ControlM) {
				ControlM cm = (ControlM) m;

				if (cm.getHops() < distance || cm.getSink_id() != sink_id) {
					next_hop = inbox.getSender();

					distance = cm.getHops();

					sink_id = cm.getSink_id();

					cm.setHops(distance + 1);

					TimerControlM tc = new TimerControlM(cm);
					tc.startRelative(1, this);
				}

			}
			if (m instanceof DataM) {
				DataM dm = (DataM) m;
				if (this.ID == sink_id) {
					String t = "Rcv from " + dm.getSenderID() + " temp_c " + dm.getTemp_c() + " time="
							+ inbox.getArrivingTime();
					Tools.appendToOutput(t);
					System.out.println(t);
				} else {
					if (next_hop != null) {
						TimerDataM td = new TimerDataM(dm);
						td.startRelative(1, this);
					}
				}
			}
		}

	}

	@Override
	public void preStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		next_hop = null;
		distance = Integer.MAX_VALUE;
		sink_id = -1;
	}

	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub

	}

	@NodePopupMethod(menuText = "Set sink")
	public void initSink() {
		distance = 0;
		next_hop = this;
		sink_id = this.ID;

		ControlM cm = new ControlM(distance + 1, this.ID);

		TimerControlM t = new TimerControlM(cm);
		t.startRelative(1, this);
	}

	@NodePopupMethod(menuText = "Send Data")
	public void sendData() {
		if (sink_id == this.ID) {
			Tools.appendToOutput("Sink não envia dados\n");
			return;
		}

		if (next_hop != null) {

			UniformDistribution ud = new UniformDistribution(-10, 50);

			DataM dm = new DataM(ud.nextSample(), this.ID);

			TimerDataM t = new TimerDataM(dm);
			t.startRelative(1, this);

			Tools.appendToOutput("Node " + this.ID + " sending data.\n");
		} else {
			Tools.appendToOutput("There is no NH to Node " + this.ID + ".\n");
		}

	}

	@NodePopupMethod(menuText = "Periodically")
	public void sendDataPeriodically() {
		if (sink_id == this.ID) {
			Tools.appendToOutput("Sink não envia dados\n");
			return;
		}

		TimerPeriodically tp = new TimerPeriodically();
		tp.startRelative(10, this);
	}

	@NodePopupMethod(menuText = "Show timers")
	public void showtimers() {
		System.out.println(this.getTimers().size());
	}

	@NodePopupMethod(menuText = "Stop least timer")
	public void stopPeriodically() {
		this.getTimers().forEach(t -> this.getTimers().remove(t));
	}

	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		String text = "id " + this.ID;
		if (next_hop != null) {
			text += " N " + next_hop.ID;
		}
		/*
		 * if (distance != Integer.MAX_VALUE) { text += " d " + distance; }
		 */

		super.drawNodeAsDiskWithText(g, pt, highlight, text, 10, Color.RED);
		if (this.ID == sink_id) {
			super.drawNodeAsSquareWithText(g, pt, highlight, text, 10, Color.GREEN);
		}

	}

	public Node getNext_hop() {
		return next_hop;
	}

	public void setNext_hop(Node next_hop) {
		this.next_hop = next_hop;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getSink_id() {
		return sink_id;
	}

	public void setSink_id(int sink_id) {
		this.sink_id = sink_id;
	}

	@Override
	public String toString() {
		return "distance=" + distance;

	}

}
