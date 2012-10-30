package gui.monitor.bedside;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.swing.JFrame;

import model.data.BedsideData;
import model.data.BedsideData.PropertyName;
import monitor.bedside.UpdateTask;
import monitor.shared.DataProvider;
import monitor.shared.DataReceiver;

public class BedsideMonitor implements DataProvider {

	private JFrame frame;
	private PatientPanel patientPanel;
	private BedsideData patientData;
	private List<String> sensors;
	private Map<DataReceiver, Timer> updaters;
	
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

		for(PropertyName p : PropertyName.values()) {
			sensors.add(p.toString());
		}
		
		updaters = new HashMap<DataReceiver, Timer>();
		
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

	public BedsideData getData() {
		return patientData;
	}

	@Override
	public List<String> getSensors() {
		return sensors;
	}

	@Override
	public void subscribe(Map<String, Integer> subscription, DataReceiver to) {
		if( updaters.keySet().contains(to) ) {
			updaters.get(to).cancel();
		}
		
		Timer t = new Timer();
		for( String s : subscription.keySet() ) {
			t.schedule(new UpdateTask(), 0, subscription.get(s));
		}
		
		updaters.put(to, t);
	}

}
