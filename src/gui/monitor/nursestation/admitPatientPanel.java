/**
 * 
 */
package gui.monitor.nursestation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.data.Patient;
import monitor.nurse.NurseStation;

/**
 * @author Greg
 * 
 */
public class admitPatientPanel extends JPanel implements ActionListener {

	JTextField first_name;
	JTextField last_name;
	JTextField birthdate;
	JTextArea allergies;
	JButton admitButton;
	PatientTabPane parent;
	NurseStation station;

	/**
	 * Create new patient input panel
	 */
	public admitPatientPanel(PatientTabPane parent, NurseStation station) {
		super();
		JPanel panel = new JPanel(new GridLayout(0, 2, 10, 20));
		this.parent = parent;
		first_name = new JTextField();
		last_name = new JTextField();
		birthdate = new JTextField();
		allergies = new JTextArea();
		admitButton = new JButton("Admit Patient");

		panel.add(new JLabel("First Name:"));
		panel.add(first_name);

		panel.add(new JLabel("Last Name:"));
		panel.add(last_name);

		panel.add(new JLabel("Birthdate:"));
		panel.add(birthdate);

		panel.add(new JLabel("Allergies:"));
		panel.add(allergies);

		panel.add(admitButton);
		this.add(panel);
		admitButton.addActionListener((ActionListener) this);
		
		this.station = station;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] allergiesList = allergies.getText().split(" ,");
		Patient patient = new Patient(first_name.getText(), last_name.getText(),
				birthdate.getText(), new ArrayList<String>(Arrays.asList(allergiesList)));
		
		parent.add(
				last_name.getText() + ", " + first_name.getText(),
				new TabPanel(patient));
		
		try{
			
			gui.monitor.bedside.BedsideMonitor monitor = new gui.monitor.bedside.BedsideMonitor();
		    monitor.setVisible(true);
		    
		    monitor.setPatient(patient);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
