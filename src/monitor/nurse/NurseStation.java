package monitor.nurse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;

public class NurseStation extends UnicastRemoteObject implements DataReceiver {

	private Set<DataProvider> monitors = new HashSet<DataProvider>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected NurseStation() throws RemoteException {
		super();
		
		
	}

	@Override
	public void setAlarm(DataProvider from, String message)  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataUpdate(Map<String, String> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAlarm(DataProvider from) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * adds a data provider to the list and subscribes to the sensors if possible.
	 * 
	 * @param monitor - the data provider being observed.
	 */
	@Override
	public void addDataProvider(DataProvider monitor) {
		try {
			monitor.subscribe(defaultSubscriptio(monitor.getSensors()), this);
			monitors.add(monitor);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sensors
	 * @return
	 */
	private Map<String, Integer> defaultSubscriptio(List<String> sensors) {
		Map<String, Integer> subs = new HashMap<String, Integer>();
		for(String s : sensors) {
			subs.put(s, 3);
		}
		
		return subs;
	}
}
