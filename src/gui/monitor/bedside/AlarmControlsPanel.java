/**
 * 
 */
package gui.monitor.bedside;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;

/**
 * @author Knoxie
 * 
 */
public class AlarmControlsPanel extends JPanel implements ActionListener {

	private JLabel callInfo;
	private BedsideMonitor monitor;
	
	/**
	 * @param layout
	 */
	public AlarmControlsPanel(LayoutManager layout, BedsideMonitor monitor) {
		super(layout);
		JButton btnClearAlarms = new JButton("Clear Alarms");
		this.monitor = monitor;
		btnClearAlarms.addActionListener(this);
		add(btnClearAlarms);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			monitor.alarm.clearAlarm();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	

	
	

}
