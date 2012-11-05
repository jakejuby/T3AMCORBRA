package gui.monitor.nursestation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.data.Patient;

public class NursesStation {

	private JFrame frame;
	PatientTabPane tabbedPane;

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
		initialize();
		tabbedPane.addPatients(new ArrayList<Patient>() {
			{
				add(new Patient());
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new PatientTabPane(JTabbedPane.LEFT);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
}
