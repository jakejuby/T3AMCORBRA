package monitor.bedside;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import model.data.BedsideData;
import monitor.shared.DataReceiver;

public class UpdateTask extends Thread {

	private boolean cancel;
	private Map<String, Integer> subscription;
	private DataReceiver to;
	private int counter;
	private String name;
	
	public UpdateTask(String name, Map<String, Integer> subscription, DataReceiver to) {
		this.subscription = subscription;
		this.to = to;
		this.name = name;
		cancel = false;
		
		this.start();
	}
	
	@Override
	public void run() {
		Map<String, String> values = new HashMap<String, String>();
		while(!cancel) {
			for(String s : subscription.keySet()) {
				// if we have slept subscription number of seconds since the last transmission, add the value
				// to the update.
				if( (counter & 0xFFFF) % subscription.get(s) == 0 ) {
					if( s.equals("HeartBeat") ) {
						values.put(s, BedsideData.getHeartBeatHistory().get(BedsideData.getHeartBeatHistory().size() - 1));
					} else if( s.equals("BloodPressure") ) {
						values.put(s, BedsideData.getBloodPressureHistory().get(BedsideData.getBloodPressureHistory().size() - 1));
					} else if ( s.equals("RespiratoryRate")  ) {
						values.put(s, BedsideData.getRespiratoryRateHistory().get(BedsideData.getRespiratoryRateHistory().size() - 1));
					}
				}
			}

			try {
				to.dataUpdate(name, values);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			values.clear();
			counter++;
		}
	}

	public void cancel() {
		cancel = true;
	}

}
