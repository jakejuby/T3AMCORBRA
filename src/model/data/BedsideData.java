/**
 * 
 */
package model.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * @author Knoxie
 * 
 */
public class BedsideData {

	private static ArrayList<String> hearbeatHistory = new ArrayList<String>();
	private static ArrayList<String> bloodPressureHistory = new ArrayList<String>();
	private static ArrayList<String> respiratoryRateHistory = new ArrayList<String>();

	private int maxHeartbeatHistoryVals = 20;
	private int maxBloodPressureHistoryVals = 20;
	private int maxRespiratoryRateHistoryVals = 20;

	public static enum PropertyName {
		HeartBeat, BloodPressure, RespiratoryRate;
	}

	public static ArrayList<String> getHeartBeatHistory() {
		return hearbeatHistory;
	}

	public static ArrayList<String> getBloodPressureHistory() {
		return bloodPressureHistory;
	}

	public static ArrayList<String> getRespiratoryRateHistory() {
		return respiratoryRateHistory;
	}

	public void addHeartBeatValue(String newVal) {
		String oldVal = "";
		if( hearbeatHistory.size() != 0 )
			oldVal = hearbeatHistory.get(hearbeatHistory.size() - 1);
		hearbeatHistory.add(newVal);
		if (hearbeatHistory.size() > maxHeartbeatHistoryVals) {
			hearbeatHistory.remove(0);
		}
		firePropertyChange(PropertyName.HeartBeat.toString(), oldVal, newVal);
	}

	public void addBloodPressureValue(String newVal) {
		String oldVal = "";
		if( bloodPressureHistory.size() != 0)
			oldVal = bloodPressureHistory.get(bloodPressureHistory.size() - 1);
		bloodPressureHistory.add(newVal);
		if (bloodPressureHistory.size() > maxBloodPressureHistoryVals) {
			bloodPressureHistory.remove(0);
		}
		firePropertyChange(PropertyName.BloodPressure.toString(), oldVal, newVal);
	}

	public void addRespiratoryRateValue(String newVal) {
		String oldVal = "";
		if( respiratoryRateHistory.size() != 0 )
			oldVal = respiratoryRateHistory.get(respiratoryRateHistory.size() - 1);
		respiratoryRateHistory.add(newVal);
		if (respiratoryRateHistory.size() > maxRespiratoryRateHistoryVals) {
			respiratoryRateHistory.remove(0);
		}
		firePropertyChange(PropertyName.RespiratoryRate.toString(), oldVal, newVal);
	}

	/**
	 * Set the number of values held in the heartbeat History
	 * 
	 * @param max
	 */
	public void maxHeartbeatValues(int max) {
		maxHeartbeatHistoryVals = max;
	}

	/**
	 * Set the number of values held in the blood pressure History
	 * 
	 * @param max
	 */
	public void maxBloodPressureValues(int max) {
		maxHeartbeatHistoryVals = max;
	}

	/*
	 * ####################### Property Change Support #######################
	 */
	private transient PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	public void firePropertyChange(String propertyName, String oldVal, String newVal) {
		if (oldVal != null && newVal != null && oldVal.compareTo(newVal) == 0) {
			return;
		}
		changeSupport
				.firePropertyChange(new PropertyChangeEvent(this, propertyName, oldVal, newVal));

	}

}
