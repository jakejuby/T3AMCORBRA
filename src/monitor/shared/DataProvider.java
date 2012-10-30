package monitor.shared;

import java.util.List;
import java.util.Map;

public interface DataProvider {

	/**
	 * Subscribe to updates at a particular frequency on a per sensor basis.
	 * @param subscription - a map of key string sensor name and value integer 
	 * 						update period in seconds
	 */
	void subscribe(Map<String, Integer> subscription);
	
	/**
	 * Return the list of possible sensors to subscribe to.
	 * @return a list of string names for the sensors
	 */
	List<String> getSensors();
}
