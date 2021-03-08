package sysaddLibrary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Font;

public class aboutdesign {

	private JFrame frmAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aboutdesign window = new aboutdesign();
					window.frmAbout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public aboutdesign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAbout = new JFrame();
		frmAbout.setIconImage(Toolkit.getDefaultToolkit().getImage(aboutdesign.class.getResource("/img/icon.png")));
		frmAbout.setTitle("About");
		frmAbout.setBounds(100, 100, 512, 234);
		frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAbout.getContentPane().setLayout(new MigLayout("", "[188.00][][]", "[][][][][]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(aboutdesign.class.getResource("/img/loginheader.png")));
		frmAbout.getContentPane().add(lblNewLabel, "cell 0 0 3 1");
		
		JLabel lblDevelopedByLibrary = new JLabel("Developed by: Library Boys");
		lblDevelopedByLibrary.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDevelopedByLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		frmAbout.getContentPane().add(lblDevelopedByLibrary, "cell 0 1 3 1,alignx center");
		
		JLabel lblLourenzLactaoen = new JLabel("Lourenz Lactaoen");
		lblLourenzLactaoen.setHorizontalAlignment(SwingConstants.CENTER);
		frmAbout.getContentPane().add(lblLourenzLactaoen, "cell 0 2,alignx center");
		
		JLabel lblLorenzMiguelC = new JLabel("Lorenz Miguel C. Durante");
		lblLorenzMiguelC.setHorizontalAlignment(SwingConstants.CENTER);
		frmAbout.getContentPane().add(lblLorenzMiguelC, "cell 2 2,alignx center");
		
		JLabel lblMarvinDanielDimaano = new JLabel("Marvin Daniel Dimaano");
		lblMarvinDanielDimaano.setHorizontalAlignment(SwingConstants.CENTER);
		frmAbout.getContentPane().add(lblMarvinDanielDimaano, "cell 0 3,alignx center");
		
		JLabel lblRowinCisnero = new JLabel("Rowin Cisnero");
		lblRowinCisnero.setHorizontalAlignment(SwingConstants.CENTER);
		frmAbout.getContentPane().add(lblRowinCisnero, "cell 2 3,alignx center");
		
		JLabel lblKarlvinLapuz = new JLabel("Karlvin Lapuz");
		frmAbout.getContentPane().add(lblKarlvinLapuz, "cell 1 4,alignx center");
	}

}
