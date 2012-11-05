package monitor.nurse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;

/**
 * The NurseStation is a remote object that can receive alarms and data.
 * It is used to remotely monitor BedsideMonitor objects which update it based
 * on a subscription to its available sensors.
 * 
 * @author jeff
 */
public class NurseStation extends UnicastRemoteObject implements DataReceiver {

	private Map<String, DataProvider> monitors = new HashMap<String, DataProvider>();
	
	private boolean statistics;
	private int messagesReceived;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NurseStation(boolean stats) throws RemoteException {
		super();
		statistics = stats;
		messagesReceived = 0;
		
		if(statistics) {
			new StatsThread(this);
		}
	}

	@Override
	public void setAlarm(String from, String message)  {
		if(!statistics) {
			System.out.println(" Alarm from: " + from + "   Reads: " + message);
		}
		messagesReceived++;
	}

	@Override
	public void dataUpdate(String bedside, Map<String, String> values) {
		if(!statistics) {
			System.out.println("Bedside Monitor Update: " + bedside);
			for(String s : values.keySet()) {
				System.out.println("Sensor " + s + " reads: " + values.get(s));
			}
		}
		messagesReceived++;
	}

	@Override
	public void clearAlarm(String from) {
		if(!statistics) {
			System.out.println("Alarm cleared from: " + from);
		}
		messagesReceived++;
	}

	/**
	 * adds a data provider to the list and subscribes to the sensors if possible.
	 * 
	 * @param monitor - the data provider being observed.
	 */
	@Override
	public void addDataProvider(String bedside, DataProvider monitor) {
		try {
			System.out.println("DataProvider found; subscribing...");
			monitor.subscribe(defaultSubscription(monitor.getSensors()));
			monitors.put(bedside, monitor);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sensors
	 * @return
	 */
	private Map<String, Integer> defaultSubscription(List<String> sensors) {
		Map<String, Integer> subs = new HashMap<String, Integer>();
		for(String s : sensors) {
			subs.put(s, 1);
		}
		
		return subs;
	}

	@Override
	public void setRequest(String bedside, String message)
			throws RemoteException {
		if(!statistics) {
			System.out.println("Call request: " + message);
		}
		messagesReceived++;
	}
	
	void clearReceived() {
		messagesReceived = 0;
	}
	
	int messages() {
		return messagesReceived;
	}
}
