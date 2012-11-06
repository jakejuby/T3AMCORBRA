package monitor.bedside;

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
		inAlarm = false;
		inCall = false;
	}

	
	/**
	 * Method used to generate an alarm to send to the nurse's station.
	 * @throws RemoteException 
	 */
	public void generateAlarm(int level, String type, String problem) throws RemoteException {
		if(!inAlarm) {
			station.setAlarm(bedside.toString(), "Alarm level: " + level + "    " + type + "; " + problem);
			inAlarm = true;
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
		inAlarm = false;
		inCall = false;
	}
}
