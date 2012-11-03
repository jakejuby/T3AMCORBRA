package gui.monitor.bedside;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import model.data.BedsideData;
import model.data.BedsideData.PropertyName;
import monitor.bedside.AlarmHandler;
import monitor.bedside.DataInterpreter;
import monitor.bedside.UpdateTask;
import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;

/**
 * BedsideMonitor is the provider of data to the nursestation.
 * It contains the GUI component if desired; it can also be run
 * headless for testing purposes.
 * 
 * @author jeff; greg
 */
public class BedsideMonitor extends UnicastRemoteObject implements DataProvider {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private PatientPanel patientPanel;
	private BedsideData patientData;
	private List<String> sensors;
	private UpdateTask updateTask;
	private DataReceiver nurseStation;
	private AlarmHandler alarm;
	private DataInterpreter dataInt;
	
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

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	/**
	 * Create the application.
	 */
	public BedsideMonitor() throws RemoteException {
		patientData = new BedsideData();

		sensors = new ArrayList<String>();
		for (PropertyName p : PropertyName.values()) {
			sensors.add(p.toString());
		}

		getNurseStation();

		alarm = new AlarmHandler(this, nurseStation);
		dataInt = new DataInterpreter(alarm);
		
		initialize();
	}

	/**
	 * private helper to retrieve the nurses station from the RMI registry
	 */
	private void getNurseStation() {
		try {
			nurseStation = (DataReceiver) Naming
					.lookup("rmi://localhost/nurse-station");
			
			nurseStation.addDataProvider(this.toString(), this);
		} catch (Exception e) {
			System.err.println("Could not access nurses station");
		}
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

		VitalInfopanel vitalInfopanel = new VitalInfopanel(new GridLayout(0, 1,
				0, 0), patientData);
		frame.getContentPane().add(vitalInfopanel, BorderLayout.CENTER);
	}

	/**
	 * Get the reference to the patient data for adding readings.
	 * 
	 * @return the patient data to add or retrieve readings
	 */
	public BedsideData getData() {
		return patientData;
	}

	/**
	 * Get the list of sensors which can provide data.
	 */
	@Override
	public List<String> getSensors() {
		return sensors;
	}

	/**
	 * Set the subscription to the sensors available; this will generate a new 
	 * UpdateTask thread with the proper subscription info.
	 */
	@Override
	public void subscribe(Map<String, Integer> subscription) {
		if (updateTask != null)
			updateTask.cancel();

		updateTask = new UpdateTask(this.toString(), subscription, nurseStation);
	}
}
