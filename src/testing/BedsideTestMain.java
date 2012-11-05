package testing;

import gui.monitor.bedside.BedsideMonitor;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple test main for instantiating a single bedside monitor in GUI mode.
 * @author jeff
 */
public class BedsideTestMain {

	public static void main(String[] args) {
		try {
			List<PatientSim> patients = new LinkedList<PatientSim>();
			
			for(int i = 0; i < Integer.parseInt(args[0]); i++) {
				BedsideMonitor bm = new BedsideMonitor();
				//bm.setVisible(true);
				PatientSim ps = new PatientSim(bm, i, 1000, 1);
				patients.add(ps);
			}
			Thread.sleep(30000);
			
			Iterator<PatientSim> it = patients.iterator();
			while(it.hasNext()) {
				it.next().cancel();
			}
			
			//System.exit(0);
			
			//ps.cancel();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
