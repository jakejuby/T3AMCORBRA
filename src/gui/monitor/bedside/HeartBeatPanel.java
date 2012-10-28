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
public class HeartBeatPanel extends JPanel {

	private JPanel labelButtonWrapper;
	private JLabel heartBeatLabel;
	private JButton alarmsButton;
	private JLabel currentBeatsLabel;

	/**
	 * @param layout
	 * @param patientData
	 */
	public HeartBeatPanel(LayoutManager layout) {
		super(layout);
		labelButtonWrapper = new JPanel();
		add(labelButtonWrapper);
		labelButtonWrapper.setLayout(new GridLayout(2, 2, 2, 2));

		heartBeatLabel = new JLabel("Hearbeat Rate:");
		heartBeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper.add(heartBeatLabel);

		alarmsButton = new JButton("Set Alarms");
		labelButtonWrapper.add(alarmsButton);

		currentBeatsLabel = new JLabel("--Beats--");
		add(currentBeatsLabel);
	}

	public void setHeartBeatDisplay(String value) {
		currentBeatsLabel.setText(value);
	}
}
