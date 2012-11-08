/**
 * VitalInfoPanel.java
 */
package gui.monitor.bedside;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import model.data.BedsideData;
import model.data.BedsideData.PropertyName;

/**
 * @author Knoxie
 * 
 */
public class VitalInfopanel extends JPanel implements PropertyChangeListener {
	private HeartBeatPanel heartBeatPanel;
	private BloodPressurePanel bloodPressurePanel;
	private RespiratoryRatePanel respPanel;

	private BedsideData patientData;

	/**
	 * @param layout
	 * @param patientData
	 */
	public VitalInfopanel(LayoutManager layout, BedsideData patientData, BedsideMonitor monitor) {
		super(layout);
		this.patientData = patientData;

		heartBeatPanel = new HeartBeatPanel();
		add(heartBeatPanel);

		bloodPressurePanel = new BloodPressurePanel();
		add(bloodPressurePanel);

		respPanel = new RespiratoryRatePanel();
		add(respPanel);

		add(new AlarmControlsPanel(new FlowLayout(), monitor));
		
		add(new PatientSimStart(new FlowLayout(), monitor));
	}

	public void setupSubscriptions() {
		patientData.addPropertyChangeListener(PropertyName.HeartBeat.toString(), this);
		patientData.addPropertyChangeListener(PropertyName.BloodPressure.toString(), this);
		patientData.addPropertyChangeListener(PropertyName.RespiratoryRate.toString(), this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if( evt.getPropertyName().equals("HeartBeat") ) {
			heartBeatPanel.setHeartBeatDisplay(evt.getNewValue().toString());
		} else if( evt.getPropertyName().equals("BloodPressure") ) {
			bloodPressurePanel.setBloodPressureDisplay(evt.getNewValue().toString());
		} else if ( evt.getPropertyName().equals("RespiratoryRate")  ) {
			respPanel.setRespiratoryRateDisplay(evt.getNewValue().toString());
		} else {
			System.out.println("No property action found.");
		}

	}
	
	
	public void updateValue(String type, String value){
		if (type.equalsIgnoreCase("heart")){
			heartBeatPanel.updateCurrent(value);
		}
		else if (type.equalsIgnoreCase("bloodPressure")) {
			bloodPressurePanel.updateCurrent(value);
		}
		else {
			respPanel.updateCurrent(value);
		}
	}
	
	public void setAlarm(String type, String value){
		if (type.equalsIgnoreCase("heart")){
			heartBeatPanel.setAlarm(value);
		}
		else if (type.equalsIgnoreCase("bloodPressure")){
			bloodPressurePanel.setAlarm(value);
		}
		else if(type.equalsIgnoreCase("respiratory")) {
			respPanel.setAlarm(value);
		}
		else {
			heartBeatPanel.setAlarm("");
			bloodPressurePanel.setAlarm("");
			respPanel.setAlarm("");
		}
		
	}
	
	
}
