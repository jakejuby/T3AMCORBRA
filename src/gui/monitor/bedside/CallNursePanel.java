/**
 * 
 */
package gui.monitor.bedside;

import java.awt.FlowLayout;
import java.awt.Label;
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
public class CallNursePanel extends JPanel {

	private BufferedImage image;
	private JLabel picLabel;
	private File imageFile;

	/**
	 * 
	 */
	public CallNursePanel() {
		super(new FlowLayout(FlowLayout.CENTER));
		try {

			imageFile = new File(System.getProperty("user.dir") + "/Images/redCross.jpg");
			image = ImageIO.read(imageFile);

			picLabel = new JLabel(new ImageIcon(image));

			add(picLabel);
			picLabel.setAlignmentY(CENTER_ALIGNMENT);

		} catch (IOException ex) {
			System.err.println("Exception displaying nurse call button");
		}
	}
}
