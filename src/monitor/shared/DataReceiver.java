package monitor.shared;

import java.util.Map;

public interface DataReceiver {

	void setAlarm(DataProvider from, String message);
	
	/**
	 * send a data update to the DataReceiver; coarse grained interface
	 * allows a batch of updates in the form of a map keying the string name
	 * of the sensor updating data with an integer value to update with.
	 * @param values the message containing the data update.
	 */
	void dataUpdate(Map<String, Integer> values);
	
	/**
	 * Eliminate the alarm/call condition.
	 * @param from - the bedside monitor clearing the alarm/call
	 */
	void clearAlarm(DataProvider from);
	
	/**
	 * Add a new DataProvider to watch and configure to.
	 * @param monitor - the data provider for the receiver to observe
	 */
	void addDataProvider(DataProvider monitor);
}
