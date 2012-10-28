/**
 * 
 */
package gui.monitor.bedside;

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

	/**
	 * @param layout
	 */
	public RespiratoryRatePanel(LayoutManager layout) {
		super(layout);

		labelButtonWrapper = new JPanel();
		add(labelButtonWrapper);
		labelButtonWrapper.setLayout(new GridLayout(2, 2, 2, 2));

		respRateLabel = new JLabel("Respiratory Rate:");
		respRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper.add(respRateLabel);

		setAlarms = new JButton("Set Alarms");
		labelButtonWrapper.add(setAlarms);

		currentRespRate = new JLabel("--RATE--");
		add(currentRespRate);
	}

	public void setRespiratoryRateDisplay(String value) {
		currentRespRate.setText(value);
	}

}
