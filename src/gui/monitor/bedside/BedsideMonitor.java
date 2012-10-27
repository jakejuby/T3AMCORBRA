package gui.monitor.bedside;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class BedsideMonitor {

	private JFrame frame;
	private PatientPanel patientPanel;

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

		VitalInfopanel vitalInfopanel = new VitalInfopanel(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(vitalInfopanel, BorderLayout.CENTER);
		
		

	}

}
