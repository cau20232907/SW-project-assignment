import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddPictureSection extends JFrame {
	
	private JTextField time;
	private JTextField tags;
	private JComponent image;
	private List<Stuff> stuffList;
	private JTextField comments;

	AddPictureSection() {
		super();
		setLayout(new BorderLayout(2,2));
		setSize(550,300);
		setTitle("Add a Picture");
		JPanel pictureInfo=new JPanel(new BorderLayout(2,2));
		
		JPanel north=new JPanel(new GridLayout(1,4));
		north.add(new JLabel("Time"));
		time=new JTextField();
		north.add(time);
		north.add(new JLabel("(Picture) Tags"));
		tags=new JTextField();
		north.add(tags);
		pictureInfo.add(north,BorderLayout.NORTH);
		
		image=new JButton("Select Image File");
		pictureInfo.add(image,BorderLayout.WEST);
		
		stuffList=new ArrayList<>();
		stuffList.add(new Stuff());
		JPanel stuffs=new JPanel(new GridLayout(stuffList.size(),1));
		stuffs.setBorder(new LineBorder(Color.GRAY,1));
		for(Stuff stuff:stuffList) {
			stuffs.add(new StuffPanel());
		}
		pictureInfo.add(stuffs,BorderLayout.CENTER);
		
		JPanel south=new JPanel(new GridLayout());
		south.add(new JLabel("Comments"));
		comments=new JTextField();
		south.add(comments);
		pictureInfo.add(south,BorderLayout.SOUTH);
		add(pictureInfo,BorderLayout.CENTER);
		
		JPanel buttom=new JPanel(new BorderLayout());
		buttom.add(new JButton("More Stuff"),BorderLayout.EAST);
		buttom.add(new JButton("OK - INPUT END"),BorderLayout.WEST);
		add(buttom,BorderLayout.SOUTH);
		setVisible(true);
	}

}