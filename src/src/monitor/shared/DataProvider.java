package monitor.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Inter face for a DataProvider; it has a list of sensors which can
 * be subscribed to at whatever update interval desired.
 * 
 * @author jeff
 */
public interface DataProvider extends Remote {

	/**
	 * Subscribe to updates at a particular frequency on a per sensor basis.
	 * @param subscription - a map of key string sensor name and value integer 
	 * 						update period in seconds
	 * @param to - the dataprovider to send the updates to
	 */
	void subscribe(Map<String, Integer> subscription) 
		throws RemoteException;
	
	/**
	 * Return the list of possible sensors to subscribe to.
	 * @return a list of string names for the sensors
	 */
	List<String> getSensors() 
		throws RemoteException;
}
