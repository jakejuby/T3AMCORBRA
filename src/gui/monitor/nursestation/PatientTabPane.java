package gui.monitor.nursestation;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import model.data.Patient;
import monitor.nurse.NurseStation;

public class PatientTabPane extends JTabbedPane {
	/**
	 * 
	 */
	public PatientTabPane(int left, NurseStation station) {
		super(left);
		add("Admit patient", new admitPatientPanel(this,station));

	}

	public void addPatients(ArrayList<Patient> patients) {
		for (Patient p : patients) {
			add(p.getLastName() + ", " + p.getFirstName(), new TabPanel());
		}
	}
}
