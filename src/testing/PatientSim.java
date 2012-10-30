package testing;

import java.util.Random;

import gui.monitor.bedside.BedsideMonitor;

public class PatientSim extends Thread {

	private BedsideMonitor monitor;
	private int probability;
	private boolean cancel;
	
	public PatientSim(BedsideMonitor mon, int prob) {
		monitor = mon;
		probability = prob;
		this.cancel = false;
		
		this.start();
	}
	
	public void cancel() {
		cancel = true;
	}
	
	public void run() {
		Random r = new Random();
		while(!cancel) {
			
			monitor.getData().addBloodPressureValue(""+r.nextInt(100));
			monitor.getData().addHeartBeatValue(""+r.nextInt(100));
			monitor.getData().addRespiratoryRateValue(""+r.nextInt(100));
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
