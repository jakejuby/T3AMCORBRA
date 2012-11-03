package testing;

import java.rmi.RemoteException;

import gui.monitor.bedside.BedsideMonitor;

/**
 * Simple test main for instantiating a single bedside monitor in GUI mode.
 * @author jeff
 */
public class BedsideTestMain {

	public static void main(String[] args) {
		try {
			BedsideMonitor bm = new BedsideMonitor();
			bm.setVisible(true);
			
			PatientSim ps = new PatientSim(bm, 1, 1000, 1);
			Thread.sleep(30000);
			
			ps.cancel();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
