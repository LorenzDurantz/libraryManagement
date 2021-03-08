package sysaddLibrary;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class about {

	public static void init() {
		JFrame frmAbout = new JFrame();
		frmAbout.setVisible(true);
		frmAbout.setIconImage(Toolkit.getDefaultToolkit().getImage(aboutdesign.class.getResource("/img/icon.png")));
		frmAbout.setTitle("About");
		frmAbout.setBounds(100, 100, 512, 234);
		//frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
