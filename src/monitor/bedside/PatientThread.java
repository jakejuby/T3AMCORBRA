package monitor.bedside;

import gui.monitor.bedside.BedsideMonitor;

public class PatientThread extends Thread {

	private int probability;
	private BedsideMonitor monitor;
	
	/**
	 * Instantiate the patient thread with the probability a reading will be critical expressed as a percentage.
	 * @param probabilityBad - the probability a sensor reading will be critical; expressed as a percentage.
	 * @param myMonitor - a reference to the bedside monitor to send data too.
	 */
	public PatientThread(int probabilityBad, BedsideMonitor myMonitor) {
		probability = probabilityBad;
		monitor = myMonitor;
	}
	
	public void run() {
		while( true ) {

			
			
			
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}
