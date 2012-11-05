package gui.monitor.nursestation;

import gui.monitor.bedside.PatientPanel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import model.data.Patient;

public class PatientTabPane extends JTabbedPane {
	/**
	 * 
	 */
	public PatientTabPane(int left) {
		super(left);

	}

	public void addPatients(ArrayList<Patient> patients) {
		for (Patient p : patients) {
			add(p.getLastName() + ", " + p.getFirstName(), new TabPanel());
		}
	}
}
