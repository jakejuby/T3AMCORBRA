package testing;

import java.rmi.RemoteException;

import gui.monitor.bedside.BedsideMonitor;

public class BedsideTestMain {

	public static void main(String[] args) {
		try {
			BedsideMonitor bm = new BedsideMonitor();
			bm.setVisible(true);
			
			PatientSim ps = new PatientSim(bm, 50);
			Thread.sleep(30000);
			
			ps.cancel();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
