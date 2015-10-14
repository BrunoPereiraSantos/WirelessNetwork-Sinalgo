package projects.pingPong.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;

import projects.pingPong.nodes.messages.PingPongMessage;
import projects.pingPong.nodes.timers.PingPongTimer;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;
import sinalgo.tools.statistics.UniformDistribution;

public class pingPongNode extends Node {
	
	private int local_r, local_g, local_b;

	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();
			if(message instanceof PingPongMessage){
				PingPongMessage pkt = (PingPongMessage) message;
				
				local_r = pkt.getR();
				local_g = pkt.getG();
				local_b = pkt.getB();
				
				this.setColor(new Color(local_r, local_g, local_b));
				
				Tools.appendToOutput("ID "+this.ID+": pkt rcv:\n  "
									+ " R="+pkt.getR()
									+ " G="+pkt.getG()
									+ " B="+pkt.getB()
									+ "\n");
				
				UniformDistribution ud = new UniformDistribution(0, 255);
				
				pkt.setR( (int) ud.nextSample());
				pkt.setG( (int) ud.nextSample());
				pkt.setB( (int) ud.nextSample());
				
				PingPongTimer timer = new PingPongTimer(pkt);
				timer.startRelative(1, this);
				
			}
		}

	}
	
	
	@NodePopupMethod(menuText = "Init PingPong")
	public void initPingPong() {		
		Tools.appendToOutput("ID "+this.ID+" inicia com cor:\n  "
							+ " R="+0
							+ " G="+0
							+ " B="+0
							+ "\n");
		
		PingPongTimer timer = new PingPongTimer(new PingPongMessage(0,0,0)); 
		timer.startRelative(1, this);
	}

	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		// TODO Auto-generated method stub
		String text = "ID:"+this.ID;
		
		
		super.drawNodeAsDiskWithText(g, pt, highlight, 
				text, 80, new Color(255-local_r, 255-local_g, 255-local_b));
	}



	@Override
	public void preStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

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

}
