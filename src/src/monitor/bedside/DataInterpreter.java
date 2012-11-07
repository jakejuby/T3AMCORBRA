package monitor.bedside;

import gui.monitor.bedside.BedsideMonitor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Determines if readings are out of range; generates alarms with some kind of
 * algorithm.
 * 
 * @author jared; jeff
 */
public class DataInterpreter implements PropertyChangeListener {
	// Maps to hold onto our ranges for our measurements.
	private HashMap<String,String> heartRateRange;
	private HashMap<String,String> respRateRange;
	private HashMap<String,String> bpRange;
	
	private AlarmHandler alarmHandler;
	
	
	public static final String LOWER_BOUND_KEY = "lowerBound";
	public static final String UPPER_BOUND_KEY = "upperBound";
	public static final String BUFFER_KEY = "bufferCounter";
	
	/**
	 * Creates the DataInterpreter corresponding to the patient's bed.
	 */
	public DataInterpreter(AlarmHandler alarms){
		heartRateRange = new HashMap<String,String>();
		respRateRange = new HashMap<String,String>();
		bpRange = new HashMap<String,String>();
		heartRateRange.put(BUFFER_KEY, 0+"");
		respRateRange.put(BUFFER_KEY, 0+"");
		bpRange.put(BUFFER_KEY, 0+"");
		alarmHandler = alarms;
		
	}
	
	/**
	 * Method used to check the HeartRate of the patient.  If the 
	 * Heart rate is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param heartRate
	 * @throws RemoteException 
	 */
	public void checkHeartRate(String heartRate) throws RemoteException{
		// upper and lower bound
		int lowerBound = Integer.parseInt(heartRateRange.get(LOWER_BOUND_KEY));
		int upperBound = Integer.parseInt(heartRateRange.get(UPPER_BOUND_KEY));
		
		// counter to keep track of how long we are in the buffer region.
		int bufferCounter = Integer.parseInt(heartRateRange.get(BUFFER_KEY));
		
		int currentRate = Integer.parseInt(heartRate);
		
		// check to see if heart rate is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
			
			// check to see if the rate is in the buffer range
			if (difference >= 0 && difference <= 5){
				
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
					
					// generate a level1 alarm
					alarmHandler.generateAlarm(1,"Heart Rate","low");
					bufferCounter++;
					heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
				// else you need to generate a level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2, "Heart Rate", "low");
					bufferCounter = 0;
					heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
				
			}
			// you are outside of the buffer range and too low, so generate a level3 alarm.
			else {
				alarmHandler.generateAlarm(3, "Heart Rate", "low");
				bufferCounter = 0;
				heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
			}
			
		}
		// if your heart rate is too high
		else if (currentRate >= upperBound){
			int difference = currentRate - upperBound;
			
			// check to see if the rate is in the buffer range.
			if (difference >= 0 && difference <= 5){
				
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
					// generate a level1 alarm
					alarmHandler.generateAlarm(1, "Heart Rate", "high");
					bufferCounter++;
					heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
				// else you need to generate level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2,"Heart Rate","high");
					bufferCounter = 0;
					heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
			}
			// you are outside of the buffer range and too high, so generate a level3 alarm.
			else {
				alarmHandler.generateAlarm(3, "Heart Rate","high");
				bufferCounter = 0;
				heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
			}
		}
		// you have a normal heart rate measurement, make sure you reset the buffer
		else {
			bufferCounter = 0;
			heartRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
		}
		
	}
	
	
	/**
	 * Method used to check the Respiratory Rate of the patient.  If the
	 * Respiratory Rate is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param respRate
	 * @throws RemoteException 
	 */
	public void checkRespiratoryRate(String respRate) throws RemoteException{
		int lowerBound = Integer.parseInt(respRateRange.get(LOWER_BOUND_KEY));
		int upperBound = Integer.parseInt(respRateRange.get(UPPER_BOUND_KEY));
		int bufferCounter = Integer.parseInt(respRateRange.get(BUFFER_KEY));
		int currentRate = Integer.parseInt(respRate);
		
		// check to see if respiratory rate is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
					
			// check to see if the rate is in the buffer range
			if (difference >= 0 && difference <= 5){
						
			// if you've been in the buffer range for less than 5 measurements.
			if (bufferCounter < 5){
							
					// generate a level1 alarm
					alarmHandler.generateAlarm(1,"Respiratory Rate","low");
					bufferCounter++;
					respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
				// else you need to generate a level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2, "Respiratory Rate", "low");
						bufferCounter = 0;
						respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
						
				}
				// you are outside of the buffer range and too low, so generate a level3 alarm.
				else {
					alarmHandler.generateAlarm(3, "Respiratory Rate", "low");
					bufferCounter = 0;
					respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
					
			}
			// if your respiratory rate is too high
			else if (currentRate >= upperBound){
				int difference = currentRate - upperBound;
					
				// check to see if the rate is in the buffer range.
				if (difference >= 0 && difference <= 5){
						
					// if you've been in the buffer range for less than 5 measurements.
					if (bufferCounter < 5){
						// generate a level1 alarm
						alarmHandler.generateAlarm(1, "Respiratory Rate", "high");
						bufferCounter++;
						respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
					// else you need to generate level2 alarm after having 5 consecutive buffer
					// range measurements.
					else {
						alarmHandler.generateAlarm(2,"Respiratory Rate","high");
						bufferCounter = 0;
						respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
				}
				// you are outside of the buffer range and too high, so generate a level3 alarm.
				else {
					alarmHandler.generateAlarm(3, "Respiratory Rate","high");
					bufferCounter = 0;
					respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
			}
			// you have a normal respiratory rate measurement, make sure you reset the buffer
			else {
				bufferCounter = 0;
				respRateRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
			}
	}
	
	
	/**
	 * Method used to check the Blood Pressure of the patient.  If the 
	 * Blood Pressure is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param bloodPressure
	 * @throws RemoteException 
	 */
	public void checkBloodPressure(String bloodPressure) throws RemoteException{
		int lowerBound = Integer.parseInt(bpRange.get(LOWER_BOUND_KEY));
		int upperBound = Integer.parseInt(bpRange.get(UPPER_BOUND_KEY));
		int bufferCounter = Integer.parseInt(bpRange.get(BUFFER_KEY));
		int currentRate = Integer.parseInt(bloodPressure);
		
		
		// check to see if blood pressure is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
							
			// check to see if the blood pressure is in the buffer range
				if (difference >= 0 && difference <= 5){
								
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
									
						// generate a level1 alarm
						alarmHandler.generateAlarm(1,"Blood Pressure","low");
						bufferCounter++;
						bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
					// else you need to generate a level2 alarm after having 5 consecutive buffer
					// range measurements.
					else {
						alarmHandler.generateAlarm(2, "Blood Pressure", "low");
							bufferCounter = 0;
							bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
						}
							
					}
					// you are outside of the buffer range and too low, so generate a level3 alarm.
					else {
						alarmHandler.generateAlarm(3, "Blood Pressure", "low");
						bufferCounter = 0;
						bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
						
				}
				// if your blood pressure is too high
				else if (currentRate >= upperBound){
					int difference = currentRate - upperBound;
						
					// check to see if the rate is in the buffer range.
					if (difference >= 0 && difference <= 5){
							
						// if you've been in the buffer range for less than 5 measurements.
						if (bufferCounter < 5){
							// generate a level1 alarm
							alarmHandler.generateAlarm(1, "Blood Pressure", "high");
							bufferCounter++;
							bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
						}
						// else you need to generate level2 alarm after having 5 consecutive buffer
						// range measurements.
						else {
							alarmHandler.generateAlarm(2,"Blood Pressure","high");
							bufferCounter = 0;
							bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
						}
					}
					// you are outside of the buffer range and too high, so generate a level3 alarm.
					else {
						alarmHandler.generateAlarm(3, "Blood Pressure","high");
						bufferCounter = 0;
						bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
					}
				}
				// you have a normal blood pressure measurement, make sure you reset the buffer
				else {
					bufferCounter = 0;
					bpRange.put(BUFFER_KEY, Integer.toString(bufferCounter));
				}
	}
	
	/**
	 * Method used to modify the configuration of the range for the HeartRate.
	 * First it will find the rangeValue variable in the map using the rangeValueKey,
	 * then it will update the value to the rangeValue.
	 * 
	 * @param rangeValueKey		Key to help find the rangeValue to change in the map.
	 * @param rangeValue		update value.
	 */
	public void setHeartRateRangeValue(String rangeValueKey, String rangeValue){
		heartRateRange.put(rangeValueKey, rangeValue);
	}
	
	
	/**
	 * Method used to modify the configuration of the range for the Respiratory Rate.
	 * First it will find the rangeValue variable in the map using the rangeValueKey,
	 * then it will update the value to the rangeValue.
	 * 
	 * @param rangeValueKey		Key to help find the rangeValue to change in the map.
	 * @param rangeValue		update value.
	 */
	public void setRespRateRangeValue(String rangeValueKey, String rangeValue){
		respRateRange.put(rangeValueKey, rangeValue);
	}
	
	
	/**
	 * Method used to modify the configuration of the range for the Blood Pressure.
	 * First it will find the rangeValue variable in the map using the rangeValueKey,
	 * then it will update the value to the rangeValue.
	 * 
	 * @param rangeValueKey		Key to help find the rangeValue to change in the map.
	 * @param rangeValue		update value.
	 */
	public void setBPRateRangeValue(String rangeValueKey, String rangeValue){
		bpRange.put(rangeValueKey, rangeValue);
	}

	@Override
	/**
	 * The method that reacts to changed properties in the BedsideData class
	 * 
	 * @param evt  The event that is fired from BedsideData
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			if( evt.getPropertyName().equals("HeartBeat") ) {
				this.checkHeartRate(evt.getNewValue().toString());
			} else if( evt.getPropertyName().equals("BloodPressure") ) {
				this.checkBloodPressure(evt.getNewValue().toString());
			} else if ( evt.getPropertyName().equals("RespiratoryRate")  ) {
				this.checkRespiratoryRate(evt.getNewValue().toString());
			}
		} catch (RemoteException re) {
			//ignore
			System.out.println("RMI, you son of a bitch");
		}
	}

}
