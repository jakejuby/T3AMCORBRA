package monitor.bedside;

import gui.monitor.bedside.BedsideMonitor;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import model.data.BedsideData;
import monitor.shared.DataReceiver;

public class UpdateTask extends Thread {
	
	private static final long POLL_PERIOD = 250;
	private boolean cancel;
	private Map<String, Integer> subscription;
	private DataReceiver to;
	private BedsideMonitor self;
	private int counter;
	private int clearAlarm;
	
	public UpdateTask(BedsideMonitor self, Map<String, Integer> subscription, DataReceiver to, int autoClearAlarm) {
		this.subscription = subscription;
		this.to = to;
		cancel = false;
		this.self = self;
		clearAlarm = autoClearAlarm;
		
		this.start();
	}
	
	@Override
	public void run() {
		Map<String, String> values = new HashMap<String, String>();
		while(!cancel) {
			for(String s : subscription.keySet()) {
				// if we have slept subscription number of seconds since the last transmission, add the value
				// to the update.
				if( (counter & 0xFFFF) != 0 && (counter & 0xFFFF) % subscription.get(s) == 0 ) {
					if( s.equals(BedsideData.PropertyName.HeartBeat.toString()) ) {
						values.put(s, self.getData().getHeartBeatHistory().get(self.getData().getHeartBeatHistory().size() - 1));
					} else if( s.equals(BedsideData.PropertyName.BloodPressure.toString()) ) {
						values.put(s, self.getData().getBloodPressureHistory().get(self.getData().getBloodPressureHistory().size() - 1));
					} else if ( s.equals(BedsideData.PropertyName.RespiratoryRate.toString())  ) {
						values.put(s, self.getData().getRespiratoryRateHistory().get(self.getData().getRespiratoryRateHistory().size() - 1));
					}
				}
			}

			if( clearAlarm > 0 && (counter & 0xFFFF) != 0 && (counter & 0xFFFF) % clearAlarm == 0 ) {
				try {
					to.clearAlarm(self.toString());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
			try {
				//if theres updates to send; send it
				if( !values.keySet().isEmpty() ) {
					to.dataUpdate(self.toString(), values);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			try {
				sleep(POLL_PERIOD);
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
