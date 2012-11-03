/**
 * 
 */
package gui.monitor.bedside;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.data.BedsideData;

/**
 * @author Knoxie
 * 
 */
public class PatientPanel extends JPanel implements MouseListener {

	private JTable patientInfoTable;

	/**
	 * @param layout
	 */
	public PatientPanel(LayoutManager layout) {
		super(layout);

		CallNursePanel callNursePanel = new CallNursePanel();
		add(callNursePanel);
		callNursePanel.setAlignmentY(CENTER_ALIGNMENT);
		callNursePanel.addMouseListener(this);

		JPanel nurseCallPanel = new JPanel();
		nurseCallPanel.add(new Label("Call Nurse"));
		add(nurseCallPanel);

		JScrollPane patientInfoScroller = new JScrollPane();
		add(patientInfoScroller);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("I NEED A NURSE!!!!!!!!!!!!!!");

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
