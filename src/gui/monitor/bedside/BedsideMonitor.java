package gui.monitor.bedside;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;

import model.data.BedsideData;

public class BedsideMonitor {

	private JFrame frame;
	private PatientPanel patientPanel;
	private BedsideData patientData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BedsideMonitor window = new BedsideMonitor();
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
	public BedsideMonitor() {
		patientData = new BedsideData();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1036, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		patientPanel = new PatientPanel(new GridLayout(0, 1, 0, 0));

		frame.getContentPane().add(patientPanel, BorderLayout.WEST);

		VitalInfopanel vitalInfopanel = new VitalInfopanel(new GridLayout(0, 1, 0, 0), patientData);
		frame.getContentPane().add(vitalInfopanel, BorderLayout.CENTER);
		
		

	}

}
