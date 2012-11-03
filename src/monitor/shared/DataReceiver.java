package monitor.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * DataReceivers can set alarms and calls as well as clear them via the clearAlarm function.
 * @author jeff
 */
public interface DataReceiver extends Remote {

	/**
	 * 
	 * @param bedside
	 * @param message
	 * @throws RemoteException
	 */
	void setAlarm(String bedside, String message) 
		throws RemoteException;
	
	/**
	 * 
	 * @param bedside
	 * @param message
	 * @throws RemoteException
	 */
	void setRequest(String bedside, String message) 
		throws RemoteException;
	
	/**
	 * send a data update to the DataReceiver; coarse grained interface
	 * allows a batch of updates in the form of a map keying the string name
	 * of the sensor updating data with an integer value to update with.
	 * @param values the message containing the data update.
	 */
	void dataUpdate(String bedside, Map<String, String> values)
		throws RemoteException;
	
	/**
	 * Eliminate the alarm/call condition.
	 * @param from - the bedside monitor clearing the alarm/call
	 */
	void clearAlarm(String bedside)
		throws RemoteException;
	
	/**
	 * Add a new DataProvider to watch and configure to.
	 * @param monitor - the data provider for the receiver to observe
	 */
	void addDataProvider(String bedside, DataProvider monitor)
		throws RemoteException;
}
