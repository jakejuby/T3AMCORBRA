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
public class VitalInfopanel extends JPanel {
	/**
	 * @param layout
	 */
	public VitalInfopanel(LayoutManager layout) {
		super(layout);
		HeartBeatPanel heartBeatPanel = 
				new HeartBeatPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(heartBeatPanel);

		BloodPressurePanel bloodPressurePanel = new BloodPressurePanel(
				new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(bloodPressurePanel);

		RespiratoryRatePanel respPanel = new RespiratoryRatePanel(
				new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(respPanel);

		add(new AlarmControlsPanel(new FlowLayout()));
	}
}
