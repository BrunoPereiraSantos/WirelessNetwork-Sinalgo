public void handleMessages(Inbox inbox)
	while (inbox.hasNext())
		Message message = inbox.next();
		if(message instanceof PingPongMessage)
			PingPongMessage pkt = (PingPongMessage) message;
			this.local_r = pkt.getR();
			this.local_g = pkt.getG();
			this.local_b = pkt.getB();
			
			this.setColor(new Color(local_r, local_g, local_b));
			
			UniformDistribution ud = new UniformDistribution(0, 255);
			
			pkt.setR( (int) ud.nextSample());
			pkt.setG( (int) ud.nextSample());
			pkt.setB( (int) ud.nextSample());
			
			PingPongTimer timer = new PingPongTimer(pkt);
			timer.startRelative(1, this);