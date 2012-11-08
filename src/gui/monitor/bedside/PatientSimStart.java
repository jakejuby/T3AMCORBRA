package gui.monitor.bedside;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import testing.PatientSim;


public class PatientSimStart extends JPanel implements ActionListener {

	private BedsideMonitor monitor;
	private JTextField patientCondition;
	
	public PatientSimStart(LayoutManager layout, final BedsideMonitor monitor){
		super(layout);
		JButton start = new JButton("Start Sim");
		this.monitor = monitor;
		start.addActionListener(this);
		start.setName("start");
		add(start);
		JLabel label = new JLabel("Enter patient's condition:");
		patientCondition = new JTextField();
		patientCondition.setPreferredSize(new Dimension(150,20));
		add(label);
		add(patientCondition);
		
		/*JButton stop = new JButton("Stop Sim");
		stop.addActionListener(this);
		stop.setName("stop");
		add(stop);
		*/
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton button = (JButton)e.getSource();
		
		if (button.getName().equalsIgnoreCase("stop")){
			monitor.simulator = null;
		}
		else {
		
			try{
				int condition;
				if (patientCondition.getText().equalsIgnoreCase("")){
					condition = 1;
				}
				else {
					condition = Integer.parseInt(patientCondition.getText());
				}
			
			
				monitor.simulator = new PatientSim(monitor,condition,1000,1);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
}
