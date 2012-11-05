/**
 * 
 */
package gui.monitor.shared;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.data.Patient;

/**
 * @author Knoxie
 * 
 */
public class InformationPanel extends JPanel {

	public InformationPanel(Patient patient) {
		super(new GridLayout(0, 2));

		add(new JLabel("Patient Info:"));
		add(new JLabel());
		add(new JLabel("Name:"));
		add(new JLabel(patient.getFirstName() + " " + patient.getLastName()));
		add(new JLabel("Birthdate:"));
		add(new JLabel(patient.getBirthdate()));
		add(new JLabel("Blood Type:"));
		add(new JLabel(patient.getBloodType()));
		add(new JLabel("Allergies:"));
		add(new JLabel(patient.getAllergies().toString().replace('[', ' ')));
	}

}
