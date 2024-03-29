package monitor.bedside;

import gui.monitor.bedside.BedsideMonitor;

import java.rmi.RemoteException;

import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;

/**
 * Submits alarm and call related method invocations to the NurseStation
 * Only allows one call to
 * 
 * @author jeff
 */
public class AlarmHandler {

	public static final String CALL_MESSAGE = "Nurse requested to bedside.";
	
	private DataReceiver station;
	private DataProvider bedside;
	
	private boolean inAlarm;
	private boolean inCall;
	
	/**
	 * Generate a new AlarmHandler
	 */
	public AlarmHandler(DataProvider bedside, DataReceiver station) {
		this.station = station;
		this.bedside = bedside;
		inCall = false;
		inAlarm = false;
	}

	
	/**
	 * Method used to generate an alarm to send to the nurse's station.
	 * @throws RemoteException 
	 */
	public void generateAlarm(int level, String type, String problem) throws RemoteException {
	
			station.setAlarm(bedside.toString(), "Alarm level: " + level + ";Type: " + type + "; " + problem);
			
			BedsideMonitor monitor = (BedsideMonitor)this.bedside;
			if (type.equalsIgnoreCase("Blood Pressure")){
				monitor.vitalInfopanel.setAlarm("bloodPressure", "level"+level);
			}
			else if (type.equalsIgnoreCase("Respiratory Rate")){
				monitor.vitalInfopanel.setAlarm("respiratory", "level" + level);
			}
			else {
				monitor.vitalInfopanel.setAlarm("heart","level"+level);
			}
		
		
	}
	
	/**
	 * Method used to generate a call to send to the nurse's station.
	 * @throws RemoteException if the call to the nurse's station fails
	 */
	public void generateCall() throws RemoteException {
		if(!inCall) {
			station.setRequest(bedside.toString(), CALL_MESSAGE);
			inCall = true;
		}
	}

	/**
	 * Clears the alarm state at the nurse's station and at the bedside.
	 * @throws RemoteException if the call to the nurse's station fails
	 */
	public void clearAlarm() throws RemoteException {
		station.clearAlarm(bedside.toString());
		BedsideMonitor monitor = (BedsideMonitor)this.bedside;
		monitor.vitalInfopanel.setAlarm("","");
		
		inAlarm = false;
		inCall = false;
	}
}
