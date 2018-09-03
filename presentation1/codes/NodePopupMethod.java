@NodePopupMethod(menuText = "Init pingPong")
public void initPingPong() {		
	PingPongMessage msg = new PingPongMessage(0,0,0);
	PingPongTimer t = new PingPongTimer(msg); 
	t.startRelative(1, this);
}