package monitor.bedside;

import java.util.HashMap;

public class DataInterpreter {
	// Maps to hold onto our ranges for our measurements.
	private HashMap<String,String> heartRateRange;
	private HashMap<String,String> respRateRange;
	private HashMap<String,String> bpRange;
	
	private AlarmHandler alarmHandler;
	
	/**
	 * Creates the DataInterpreter corresponding to the patient's bed.
	 */
	public DataInterpreter(){
		heartRateRange = new HashMap<String,String>();
		respRateRange = new HashMap<String,String>();
		bpRange = new HashMap<String,String>();
		alarmHandler = new AlarmHandler();
	}
	
	/**
	 * Method used to check the HeartRate of the patient.  If the 
	 * Heart rate is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param heartRate
	 */
	public void checkHeartRate(String heartRate){
		// upper and lower bound
		int lowerBound = Integer.parseInt(heartRateRange.get("lowerBound"));
		int upperBound = Integer.parseInt(heartRateRange.get("upperBound"));
		
		// counter to keep track of how long we are in the buffer region.
		int bufferCounter = Integer.parseInt(heartRateRange.get("bufferCounter"));
		
		int currentRate = Integer.parseInt(heartRate);
		
		// check to see if heart rate is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
			
			// check to see if the rate is in the buffer range
			if (difference >= 0 && difference <= 10){
				
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
					
					// generate a level1 alarm
					alarmHandler.generateAlarm(1,"Heart Rate","low");
					bufferCounter++;
					heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
				// else you need to generate a level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2, "Heart Rate", "low");
					bufferCounter = 0;
					heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
				
			}
			// you are outside of the buffer range and too low, so generate a level3 alarm.
			else {
				alarmHandler.generateAlarm(3, "Heart Rate", "low");
				bufferCounter = 0;
				heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
			}
			
		}
		// if your heart rate is too high
		else if (currentRate >= upperBound){
			int difference = currentRate - upperBound;
			
			// check to see if the rate is in the buffer range.
			if (difference >= 0 && difference <= 10){
				
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
					// generate a level1 alarm
					alarmHandler.generateAlarm(1, "Heart Rate", "high");
					bufferCounter++;
					heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
				// else you need to generate level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2,"Heart Rate","high");
					bufferCounter = 0;
					heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
			}
			// you are outside of the buffer range and too high, so generate a level3 alarm.
			else {
				alarmHandler.generateAlarm(3, "Heart Rate","high");
				bufferCounter = 0;
				heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
			}
		}
		// you have a normal heart rate measurement, make sure you reset the buffer
		else {
			bufferCounter = 0;
			heartRateRange.put("bufferCounter", Integer.toString(bufferCounter));
		}
		
	}
	
	
	/**
	 * Method used to check the Respiratory Rate of the patient.  If the
	 * Respiratory Rate is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param respRate
	 */
	public void checkRespiratoryRate(String respRate){
		int lowerBound = Integer.parseInt(respRateRange.get("lowerBound"));
		int upperBound = Integer.parseInt(respRateRange.get("upperBound"));
		int bufferCounter = Integer.parseInt(respRateRange.get("bufferCounter"));
		int currentRate = Integer.parseInt(respRate);
		
		// check to see if respiratory rate is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
					
			// check to see if the rate is in the buffer range
			if (difference >= 0 && difference <= 10){
						
			// if you've been in the buffer range for less than 5 measurements.
			if (bufferCounter < 5){
							
					// generate a level1 alarm
					alarmHandler.generateAlarm(1,"Respiratory Rate","low");
					bufferCounter++;
					respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
				// else you need to generate a level2 alarm after having 5 consecutive buffer
				// range measurements.
				else {
					alarmHandler.generateAlarm(2, "Respiratory Rate", "low");
						bufferCounter = 0;
						respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
						
				}
				// you are outside of the buffer range and too low, so generate a level3 alarm.
				else {
					alarmHandler.generateAlarm(3, "Respiratory Rate", "low");
					bufferCounter = 0;
					respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
					
			}
			// if your respiratory rate is too high
			else if (currentRate >= upperBound){
				int difference = currentRate - upperBound;
					
				// check to see if the rate is in the buffer range.
				if (difference >= 0 && difference <= 10){
						
					// if you've been in the buffer range for less than 5 measurements.
					if (bufferCounter < 5){
						// generate a level1 alarm
						alarmHandler.generateAlarm(1, "Respiratory Rate", "high");
						bufferCounter++;
						respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
					// else you need to generate level2 alarm after having 5 consecutive buffer
					// range measurements.
					else {
						alarmHandler.generateAlarm(2,"Respiratory Rate","high");
						bufferCounter = 0;
						respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
				}
				// you are outside of the buffer range and too high, so generate a level3 alarm.
				else {
					alarmHandler.generateAlarm(3, "Respiratory Rate","high");
					bufferCounter = 0;
					respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
				}
			}
			// you have a normal respiratory rate measurement, make sure you reset the buffer
			else {
				bufferCounter = 0;
				respRateRange.put("bufferCounter", Integer.toString(bufferCounter));
			}
	}
	
	
	/**
	 * Method used to check the Blood Pressure of the patient.  If the 
	 * Blood Pressure is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param bloodPressure
	 */
	public void checkBloodPressure(String bloodPressure){
		int lowerBound = Integer.parseInt(bpRange.get("lowerBound"));
		int upperBound = Integer.parseInt(bpRange.get("upperBound"));
		int bufferCounter = Integer.parseInt(bpRange.get("bufferCounter"));
		int currentRate = Integer.parseInt(bloodPressure);
		
		
		// check to see if blood pressure is too low
		if (currentRate <= lowerBound){
			int difference = lowerBound - currentRate;
							
			// check to see if the blood pressure is in the buffer range
				if (difference >= 0 && difference <= 10){
								
				// if you've been in the buffer range for less than 5 measurements.
				if (bufferCounter < 5){
									
						// generate a level1 alarm
						alarmHandler.generateAlarm(1,"Blood Pressure","low");
						bufferCounter++;
						bpRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
					// else you need to generate a level2 alarm after having 5 consecutive buffer
					// range measurements.
					else {
						alarmHandler.generateAlarm(2, "Blood Pressure", "low");
							bufferCounter = 0;
							bpRange.put("bufferCounter", Integer.toString(bufferCounter));
						}
							
					}
					// you are outside of the buffer range and too low, so generate a level3 alarm.
					else {
						alarmHandler.generateAlarm(3, "Blood Pressure", "low");
						bufferCounter = 0;
						bpRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
						
				}
				// if your blood pressure is too high
				else if (currentRate >= upperBound){
					int difference = currentRate - upperBound;
						
					// check to see if the rate is in the buffer range.
					if (difference >= 0 && difference <= 10){
							
						// if you've been in the buffer range for less than 5 measurements.
						if (bufferCounter < 5){
							// generate a level1 alarm
							alarmHandler.generateAlarm(1, "Blood Pressure", "high");
							bufferCounter++;
							bpRange.put("bufferCounter", Integer.toString(bufferCounter));
						}
						// else you need to generate level2 alarm after having 5 consecutive buffer
						// range measurements.
						else {
							alarmHandler.generateAlarm(2,"Blood Pressure","high");
							bufferCounter = 0;
							bpRange.put("bufferCounter", Integer.toString(bufferCounter));
						}
					}
					// you are outside of the buffer range and too high, so generate a level3 alarm.
					else {
						alarmHandler.generateAlarm(3, "Blood Pressure","high");
						bufferCounter = 0;
						bpRange.put("bufferCounter", Integer.toString(bufferCounter));
					}
				}
				// you have a normal blood pressure measurement, make sure you reset the buffer
				else {
					bufferCounter = 0;
					bpRange.put("bufferCounter", Integer.toString(bufferCounter));
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

}
