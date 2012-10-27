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

	/**
	 * @param layout
	 */
	public RespiratoryRatePanel(LayoutManager layout) {
		super(layout);

		JPanel labelButtonWrapper4 = new JPanel();
		add(labelButtonWrapper4);
		labelButtonWrapper4.setLayout(new GridLayout(2, 2, 2, 2));

		JLabel label_3 = new JLabel("Respiratory Rate:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper4.add(label_3);

		JButton button_1 = new JButton("Set Alarms");
		labelButtonWrapper4.add(button_1);

		JLabel label_4 = new JLabel("--RATE--");
		add(label_4);
	}

}
