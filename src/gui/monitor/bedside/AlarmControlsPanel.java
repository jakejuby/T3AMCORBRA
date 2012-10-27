/**
 * 
 */
package gui.monitor.bedside;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Knoxie
 * 
 */
public class AlarmControlsPanel extends JPanel {

	/**
	 * @param layout
	 */
	public AlarmControlsPanel(LayoutManager layout) {
		super(layout);
		JButton btnClearAlarms = new JButton("Clear Alarms");
		add(btnClearAlarms);
	}

}
