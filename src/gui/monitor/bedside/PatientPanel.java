/**
 * 
 */
package gui.monitor.bedside;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Knoxie
 * 
 */
public class PatientPanel extends JPanel {

	private JTable patientInfoTable;

	/**
	 * @param layout
	 */
	public PatientPanel(LayoutManager layout) {
		super(layout);
		JPanel callNursePanel = new JPanel();
		add(callNursePanel);
		callNursePanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel nurseCallPanel = new JPanel();
		callNursePanel.add(nurseCallPanel);

		JScrollPane patientInfoScroller = new JScrollPane();
		add(patientInfoScroller);

		patientInfoTable = new JTable();
		patientInfoScroller.setViewportView(patientInfoTable);
	}

}
