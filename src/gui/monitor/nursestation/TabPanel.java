/**
 * 
 */
package gui.monitor.nursestation;

import gui.monitor.bedside.BloodPressurePanel;
import gui.monitor.bedside.HeartBeatPanel;
import gui.monitor.bedside.PatientPanel;
import gui.monitor.bedside.RespiratoryRatePanel;
import gui.monitor.shared.InformationPanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.data.Patient;

/**
 * @author Knoxie
 * 
 */
public class TabPanel extends JPanel {

	HeartBeatPanel heart;
	BloodPressurePanel blood;
	RespiratoryRatePanel breath;

	public TabPanel() {
		super(new GridLayout());
		heart = new HeartBeatPanel();
		blood = new BloodPressurePanel();
		breath = new RespiratoryRatePanel();
		add(new JPanel() {
			{
				add(new showPatientPicture());
				add(new InformationPanel(new Patient()));
			}
		});

		add(new JPanel(new GridLayout(0, 1)) {
			{
				add(heart);
				add(blood);
				add(breath);
			}
		});
	}

}
