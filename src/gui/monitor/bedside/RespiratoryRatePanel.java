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
public class RespiratoryRatePanel extends JPanel {

	private JLabel currentRespRate;
	private JButton setAlarms;
	private JLabel respRateLabel;
	private JPanel labelButtonWrapper;
	private JLabel alarm;

	/**
	 * @param layout
	 */
	public RespiratoryRatePanel() {
		super(new FlowLayout(FlowLayout.CENTER, 5, 5));

		labelButtonWrapper = new JPanel();
		add(labelButtonWrapper);
		labelButtonWrapper.setLayout(new GridLayout(2, 2, 2, 2));

		respRateLabel = new JLabel("Respiratory Rate:");
		respRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper.add(respRateLabel);

		//setAlarms = new JButton("Set Alarms");
		//labelButtonWrapper.add(setAlarms);

		currentRespRate = new JLabel("--RATE--");
		add(currentRespRate);
		
		alarm = new JLabel("");
		add(alarm);
	}

	public void setRespiratoryRateDisplay(String value) {
		currentRespRate.setText(value);
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
		currentRespRate.setText(value + " bpm");
	}

}
