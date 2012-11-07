package monitor.nurse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;
import gui.monitor.nursestation.*;

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
	private NursesStation nurseUI;
	
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
	
	public void setUI(NursesStation station){
		nurseUI = station;
	}

	@Override
	public void setAlarm(String from, String message)  {
		if(!statistics) {
			System.out.println(" Alarm from: " + from + "   Reads: " + message);
			
			String type = message.substring(message.indexOf("Type"));
			TabPanel panel = (TabPanel)nurseUI.tabbedPane.getSelectedComponent();
		
			if (type.contains("Blood Pressure")){
				if (message.contains("Alarm level: 1")){
					panel.blood.setAlarm("level1");
				}
				else if (message.contains("Alarm level: 2")) {
					panel.blood.setAlarm("level2");
				}
				else  {
					panel.blood.setAlarm("level3");
				}
			}
			else if (type.contains("Respiratory Rate")){
				if (message.contains("Alarm level: 1")){
					panel.blood.setAlarm("level1");
				}
				else if (message.contains("Alarm level: 2")) {
					panel.blood.setAlarm("level2");
				}
				else{
					panel.blood.setAlarm("level3");
				}
			}
			else {
				if (message.contains("Alarm level: 1")){
					panel.blood.setAlarm("level1");
				}
				else if (message.contains("Alarm level: 2")) {
					panel.blood.setAlarm("level2");
				}
				else {
					panel.blood.setAlarm("level3");
				}
			}
		
			
			
		}
		messagesReceived++;
		
		
		
		
		
		
	}

	@Override
	public void dataUpdate(String bedside, Map<String, String> values)  {
		if(!statistics) {
			System.out.println("Bedside Monitor Update: " + bedside);
			TabPanel panel = (TabPanel)nurseUI.tabbedPane.getSelectedComponent();
			for(String s : values.keySet()) {
				System.out.println("Sensor " + s + " reads: " + values.get(s));
				if(s.equalsIgnoreCase("BloodPressure")){
					panel.blood.updateCurrent(values.get(s) + " mmHg");
				}
				else if (s.equalsIgnoreCase("RespiratoryRate")){
					panel.breath.updateCurrent(values.get(s) + " bpm");
				}
				else {
					panel.heart.updateCurrent(values.get(s) + " bpm");
				}
			}
			
			
			
			
		}
		messagesReceived++;
		
		
		
	}

	@Override
	public void clearAlarm(String from) {
		if(!statistics) {
			System.out.println("Alarm cleared from: " + from);
			TabPanel panel = (TabPanel)nurseUI.tabbedPane.getSelectedComponent();
			panel.clearCall();
			panel.clearAlarm();
			
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
			TabPanel panel = (TabPanel)nurseUI.tabbedPane.getSelectedComponent();
			panel.callNurse();
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
