/**
 * 
 */
package gui.monitor.bedside;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Knoxie
 * 
 */
public class BloodPressurePanel extends JPanel {
	private JPanel labelButtonWrapper;
	private JButton setAlarms;
	private JLabel bloodPressureLabel;
	private JLabel currentPressure;
	private JLabel alarm;

	/**
	 * @param layout
	 */
	public BloodPressurePanel() {
		super(new FlowLayout(FlowLayout.CENTER, 5, 5));
		labelButtonWrapper = new JPanel();
		add(labelButtonWrapper);
		labelButtonWrapper.setLayout(new GridLayout(2, 2, 2, 2));

		bloodPressureLabel = new JLabel("Blood Pressure: ");
		bloodPressureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper.add(bloodPressureLabel);

		//setAlarms = new JButton("Set Alarms");
		//labelButtonWrapper.add(setAlarms);

		currentPressure = new JLabel("--Pressure--");
		add(currentPressure);
		
		alarm = new JLabel("");
		add(alarm);
	}

	public void setBloodPressureDisplay(String value) {
		currentPressure.setText(value);

	}
	
	public void setAlarm(String level){
		if (level.equalsIgnoreCase("level1")){
			alarm.setText("Level 1 Alarm");
			alarm.setForeground(Color.blue);
		}
		else if (level.equalsIgnoreCase("level2")){
			alarm.setText("Level 2 Alarm");
			alarm.setForeground(Color.yellow);
		}
		else if (level.equalsIgnoreCase("level3")){
			alarm.setText("Level 3 Alarm");
			alarm.setForeground(Color.red);
		}
		else {
			alarm.setText("");
			alarm.setForeground(Color.black);
		}
	}
	
	public void updateCurrent(String value){
		currentPressure.setText(value + " mmHg");
	}
	

}
