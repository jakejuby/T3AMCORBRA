package gui.monitor.nursestation;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import model.data.Patient;

public class PatientTabPane extends JTabbedPane {
	/**
	 * 
	 */
	public PatientTabPane(int left) {
		super(left);
		add("Admit patient", new admitPatientPanel(this));

	}

	public void addPatients(ArrayList<Patient> patients) {
		for (Patient p : patients) {
			add(p.getLastName() + ", " + p.getFirstName(), new TabPanel());
		}
	}
}
