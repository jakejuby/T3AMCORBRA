package gui.monitor.bedside;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import testing.PatientSim;


public class PatientSimStart extends JPanel implements ActionListener {

	private BedsideMonitor monitor;
	
	public PatientSimStart(LayoutManager layout, BedsideMonitor monitor){
		super(layout);
		JButton start = new JButton("Start Sim");
		this.monitor = monitor;
		start.addActionListener(this);
		add(start);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		try{
			monitor.simulator = new PatientSim(monitor,1,1000,1);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
