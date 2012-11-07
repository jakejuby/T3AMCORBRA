/**
 * 
 */
package gui.monitor.nursestation;

import gui.monitor.bedside.BloodPressurePanel;
import gui.monitor.bedside.HeartBeatPanel;
import gui.monitor.bedside.RespiratoryRatePanel;
import gui.monitor.shared.InformationPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.data.Patient;

/**
 * @author Knoxie
 * 
 */
public class TabPanel extends JPanel implements ActionListener {

	public HeartBeatPanel heart;
	public BloodPressurePanel blood;
	public RespiratoryRatePanel breath;
	JPanel thisPan;
	JLabel callInfo;
	public InformationPanel infoPanel;

	public TabPanel() {
		this(new Patient());
	}

	public TabPanel(final Patient pat) {
		super(new GridLayout());
		thisPan = this;
		heart = new HeartBeatPanel();
		blood = new BloodPressurePanel();
		breath = new RespiratoryRatePanel();
		add(new JPanel() {
			{
				add(new ShowPatientPicture());
				infoPanel = new InformationPanel(pat);
				add(infoPanel);
			}
		});

		add(new JPanel(new GridLayout(0, 1)) {
			{
				add(heart);
				add(blood);
				add(breath);
			}
		});
		JPanel pan3 = new JPanel(new FlowLayout()) {
			{
				JButton button = new JButton("Discharge Patient");
				button.addActionListener((ActionListener) thisPan);
				add(button);
			}
		};
		add(pan3);
		
		JPanel pan4 = new JPanel(new FlowLayout()){
			{
				JLabel callLabel = new JLabel("Patient Call:");
				callInfo = new JLabel("");
				callInfo.setForeground(Color.red);
				add(callLabel);
				add(callInfo);
			}
		};
		add(pan4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NursesStation.tabbedPane.remove(this);
	}
	
	public void callNurse(){
		callInfo.setText("Patient Need's Help");
	}
	
	public void clearCall(){
		callInfo.setText("");
	}
	
	public void clearAlarm(){
		blood.setAlarm("");
		heart.setAlarm("");
		breath.setAlarm("");
	}
	
}
