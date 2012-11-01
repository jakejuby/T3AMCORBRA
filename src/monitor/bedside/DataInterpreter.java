package monitor.bedside;

public class DataInterpreter {
	// Maps to hold onto our ranges for our measurements.
	private HashMap<String,String> heartRateRange;
	private HashMap<String,String> respRateRange;
	private HashMap<String,String> bpRateRange;
	
	private AlarmHandler alarmHandler;
	
	/**
	 * Creates the DataInterpreter corresponding to the patient's bed.
	 */
	public DataInterpreter(){
		heartRateRange = new HashMap<String,String>();
		respRateRange = new HashMap<String,String>();
		bpRateRange = new HashMap<String,String>();
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
		
	}
	
	
	/**
	 * Method used to check the Respiratory Rate of the patient.  If the
	 * Respiratory Rate is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param respRate
	 */
	public void checkRespiratoryRate(String respRate){
		
	}
	
	
	/**
	 * Method used to check the Blood Pressure of the patient.  If the 
	 * Blood Pressure is outside of the acceptable range, then function will
	 * cause an alarm to be sent to the nurse's station.
	 * 
	 * @param bloodPressure
	 */
	public void checkBloodPressure(String bloodPressure){
		
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
		
	}

}
