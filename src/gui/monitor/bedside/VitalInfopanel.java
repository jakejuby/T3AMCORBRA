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
	public VitalInfopanel(LayoutManager layout, BedsideData patientData) {
		super(layout);
		this.patientData = patientData;

		heartBeatPanel = new HeartBeatPanel();
		add(heartBeatPanel);

		bloodPressurePanel = new BloodPressurePanel();
		add(bloodPressurePanel);

		respPanel = new RespiratoryRatePanel();
		add(respPanel);

		add(new AlarmControlsPanel(new FlowLayout()));
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
}
