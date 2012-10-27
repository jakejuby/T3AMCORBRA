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
	private JTable patientInfoTable;

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
		
		JPanel patientPanel = new JPanel();
		frame.getContentPane().add(patientPanel, BorderLayout.WEST);
		patientPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel callNursePanel = new JPanel();
		patientPanel.add(callNursePanel);
		callNursePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel nurseCallPanel = new JPanel();
		callNursePanel.add(nurseCallPanel);
		
		JScrollPane patientInfoScroller = new JScrollPane();
		patientPanel.add(patientInfoScroller);
		
		patientInfoTable = new JTable();
		patientInfoScroller.setViewportView(patientInfoTable);
		
		JPanel vitalInfopanel = new JPanel();
		frame.getContentPane().add(vitalInfopanel, BorderLayout.CENTER);
		vitalInfopanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel heartBeatPanel = new JPanel();
		vitalInfopanel.add(heartBeatPanel);
		heartBeatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel labelButtonWrapper = new JPanel();
		heartBeatPanel.add(labelButtonWrapper);
		labelButtonWrapper.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		labelButtonWrapper.add(lblNewLabel_1);
		
		JButton button_2 = new JButton("Set Alarms");
		labelButtonWrapper.add(button_2);
		
		JLabel label_1 = new JLabel("Heart Beat:");
		heartBeatPanel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel bloodPressurePanel = new JPanel();
		vitalInfopanel.add(bloodPressurePanel);
		bloodPressurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel labelButtonWrapper3 = new JPanel();
		bloodPressurePanel.add(labelButtonWrapper3);
		labelButtonWrapper3.setLayout(new GridLayout(2, 2, 2, 2));
		
		JLabel label = new JLabel("Heart Beat:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper3.add(label);
		
		JButton button = new JButton("Set Alarms");
		labelButtonWrapper3.add(button);
		
		JLabel label_2 = new JLabel("New label");
		bloodPressurePanel.add(label_2);
		
		JPanel respiratoryRatePanel = new JPanel();
		vitalInfopanel.add(respiratoryRatePanel);
		respiratoryRatePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel labelButtonWrapper4 = new JPanel();
		respiratoryRatePanel.add(labelButtonWrapper4);
		labelButtonWrapper4.setLayout(new GridLayout(2, 2, 2, 2));
		
		JLabel label_3 = new JLabel("Heart Beat:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		labelButtonWrapper4.add(label_3);
		
		JButton button_1 = new JButton("Set Alarms");
		labelButtonWrapper4.add(button_1);
		
		JLabel label_4 = new JLabel("New label");
		respiratoryRatePanel.add(label_4);
		
		JPanel alarmControlsPanel = new JPanel();
		vitalInfopanel.add(alarmControlsPanel);
		
		JButton btnClearAlarms = new JButton("Clear Alarms");
		alarmControlsPanel.add(btnClearAlarms);
	}

}
