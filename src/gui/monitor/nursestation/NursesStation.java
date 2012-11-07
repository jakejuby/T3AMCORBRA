package gui.monitor.nursestation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.Naming;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.data.Patient;
import monitor.nurse.NurseStation;

public class NursesStation {

	private JFrame frame;
	public static PatientTabPane tabbedPane;
	private NurseStation station;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NursesStation window = new NursesStation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NursesStation() {
		try{
		   station = new NurseStation(false);
		   Naming.rebind("nurse-station", station);
		   
		   System.out.println("Nurse's Station Started...");
			
		}
		catch(Exception ex){
			System.out.println("Exception thrown : ");
			ex.printStackTrace();
		}
		
		initialize();
		tabbedPane.addPatients(new ArrayList<Patient>() {
			{
				//add(new Patient());
			}
		});
		station.setUI(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new PatientTabPane(JTabbedPane.LEFT, station);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
}
