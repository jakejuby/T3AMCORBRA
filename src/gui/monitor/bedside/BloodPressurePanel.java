/**
 * 
 */
package gui.monitor.bedside;

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

		setAlarms = new JButton("Set Alarms");
		labelButtonWrapper.add(setAlarms);

		currentPressure = new JLabel("--Pressure--");
		add(currentPressure);
	}

	public void setBloodPressureDisplay(String value) {
		currentPressure.setText(value);

	}

}
