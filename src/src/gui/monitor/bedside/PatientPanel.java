/**
 * 
 */
package gui.monitor.bedside;

import gui.monitor.shared.InformationPanel;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.data.BedsideData;
import model.data.Patient;

/**
 * @author Knoxie; Jared
 * 
 */
public class PatientPanel extends JPanel implements MouseListener {

	private JTable patientInfoTable;
	private BedsideMonitor monitor;
	public InformationPanel panel;

	/**
	 * @param layout
	 */
	public PatientPanel(LayoutManager layout, Patient patient, BedsideMonitor monitor) {
		super(layout);
		
		this.monitor = monitor;

		CallNursePanel callNursePanel = new CallNursePanel();
		add(callNursePanel);
		callNursePanel.setAlignmentY(CENTER_ALIGNMENT);
		callNursePanel.addMouseListener(this);

		JPanel nurseCallPanel = new JPanel();
		nurseCallPanel.add(new Label("Call Nurse"));
		add(nurseCallPanel);

		panel = new InformationPanel(patient);
		add(panel);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("I NEED A NURSE!!!!!!!!!!!!!!");
		monitor.generateCall();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
