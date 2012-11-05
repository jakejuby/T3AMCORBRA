/**
 * 
 */
package gui.monitor.nursestation;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Knoxie
 * 
 */
public class showPatientPicture extends JPanel {

	private File imageFile;
	private BufferedImage image;
	private JLabel picLabel;

	public showPatientPicture() {
		super(new FlowLayout(FlowLayout.CENTER));
		try {

			imageFile = new File(System.getProperty("user.dir") + "/Images/patientPicture.jpg");
			image = ImageIO.read(imageFile);

			picLabel = new JLabel(new ImageIcon(image));

			add(picLabel);
			picLabel.setAlignmentY(CENTER_ALIGNMENT);

		} catch (IOException ex) {
			System.err.println("Exception displaying nurse call button");
		}
	}
}
