package monitor.nurse;


public class StatsThread extends Thread {
	
	private boolean cancel;
	private NurseStation self;
	
	public StatsThread(NurseStation self) {
		cancel = false;
		this.self = self;
		
		this.start();
	}
	
	@Override
	public void run() {
		self.clearReceived();
		while(!cancel) {
			try {
				Thread.sleep(2000);
				System.out.println("Messages/sec in last 2sec: " + (self.messages()/2));
				self.clearReceived();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void cancel() {
		cancel = true;
	}

}
