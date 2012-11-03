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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NurseStation() throws RemoteException {
		super();
	}

	@Override
	public void setAlarm(String from, String message)  {
		System.out.println(" Alarm from: " + from + "   Reads: " + message);
	}

	@Override
	public void dataUpdate(String bedside, Map<String, String> values) {
		System.out.println("Bedside Monitor Update: " + bedside);
		for(String s : values.keySet()) {
			System.out.println("Sensor " + s + " reads: " + values.get(s));
		}
	}

	@Override
	public void clearAlarm(String from) {
		System.out.println("Alarm cleared from: " + from);
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
			subs.put(s, 3);
		}
		
		return subs;
	}

	@Override
	public void setRequest(String bedside, String message)
			throws RemoteException {
		System.out.println("Call request: " + message);
	}
}
