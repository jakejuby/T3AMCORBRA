package testing;

import java.util.Random;

import gui.monitor.bedside.BedsideMonitor;

/**
 * The class which simulates a patient; probability of a reading which is out of a 50+/-5 range is
 * given as prob/10000. Of those which are outside the range, there is a 1/2 chance it will be higher
 * and a 1/2 chance it will be lower.
 * 
 * @author jeff
 */
public class PatientSim extends Thread {

	public static final int PROBABILITY_DENOM = 10000;
	
	private BedsideMonitor monitor;
	private int probability;
	private boolean cancel;
	
	private int minimumUpdate;
	private int upperUpdate;

	/**
	 * Construct the patient simulation. This thread is self starting; use cancel to kill
	 * the thread to exit gracefully. Values are generated every minimumUpdate + [0, upperUpdate)
	 * generated randomly.
	 * 
	 * @param mon - reference to the monitor monitoring the patient
	 * @param prob - the probability that any given reading will be out of range 50+/-5 is
	 * 				  prob/10000
	 */
	public PatientSim(BedsideMonitor mon, int prob, int minUdate, int upperUdate) {
		monitor = mon;
		probability = prob;
		this.cancel = false;
		
		minimumUpdate = minUdate;
		upperUpdate = upperUdate;

		this.start();
	}

	/**
	 * Kills the currently running patient sim thread.
	 */
	public void cancel() {
		cancel = true;
	}

	/**
	 * Run the simulation; generate values and add them at sporadic intervals.
	 */
	public void run() {
		Random r = new Random();
		while (!cancel) {
			if (r.nextInt(PROBABILITY_DENOM) <= probability) {
				if(r.nextInt(2) == 1) {
					monitor.getData().addBloodPressureValue("" + (65 + r.nextInt(10)));
				} else {
					monitor.getData().addBloodPressureValue("" + (15 + r.nextInt(10)));
				}
			} else {
				monitor.getData().addBloodPressureValue("" + (45 + r.nextInt(10)));
			}

			if (r.nextInt(PROBABILITY_DENOM) <= probability) {
				if(r.nextInt(2) == 1) {
					monitor.getData().addHeartBeatValue("" + (65 + r.nextInt(10)));
				} else {
					monitor.getData().addHeartBeatValue("" + (15 + r.nextInt(10)));
				}
			} else {
				monitor.getData().addHeartBeatValue("" + (45 + r.nextInt(10)));
			}

			if (r.nextInt(PROBABILITY_DENOM) <= probability) {
				if(r.nextInt(2) == 1) {
					monitor.getData().addRespiratoryRateValue("" + (65 + r.nextInt(10)));
				} else {
					monitor.getData().addRespiratoryRateValue("" + (15 + r.nextInt(10)));
				}
			} else {
				monitor.getData().addRespiratoryRateValue("" + (45 + r.nextInt(10)));
			}

			try {
				sleep(minimumUpdate + r.nextInt(upperUpdate));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
