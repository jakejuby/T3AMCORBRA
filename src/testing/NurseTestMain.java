package testing;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import monitor.nurse.*;

public class NurseTestMain {

	public static void main(String[] args) {
		NurseStation server;
		try {
			server = new NurseStation();
			Naming.rebind("nurse-station", server);
			
			System.out.println("Nurse's Station Started...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
