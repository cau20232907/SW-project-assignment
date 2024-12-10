import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StuffPanel extends JPanel {

	private JComponent type;
	private JComponent name;
	private JComponent tags;
	
	StuffPanel(){
		super(new BorderLayout());
		setBorder(new LineBorder(Color.GRAY,1));
		
		JPanel left=new JPanel(new GridLayout(3,1));
		JPanel right=new JPanel(new GridLayout(3,1));
		
		left.add(new JLabel("Type"));
		type=new JTextField();
		right.add(type);
		
		left.add(new JLabel("Name"));
		name=new JTextField();
		right.add(name);
		
		left.add(new JLabel("Tags"));
		tags=new JTextField();
		right.add(tags);
		
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.CENTER);
	}

	StuffPanel(Stuff stuff) {
		super(new BorderLayout());
		Font planefont=new Font("SansSerif",Font.PLAIN,12); //단순히 PLAIN으로 변경
		LineBorder objectBorder=new LineBorder(Color.LIGHT_GRAY,1);
		setBorder(new LineBorder(Color.GRAY,1));
		
		JPanel left=new JPanel(new GridLayout(3,1));
		JPanel right=new JPanel(new GridLayout(3,1));
		
		left.add(new JLabel("Type"));
		type=new JLabel(stuff.getType());
		type.setBorder(objectBorder);
		type.setFont(planefont);
		right.add(type);
		
		left.add(new JLabel("Name"));
		name=new JLabel(stuff.getName());
		name.setBorder(objectBorder);
		name.setFont(planefont);
		right.add(name);
		
		left.add(new JLabel("Tags"));
		tags=new JLabel(stuff.getTags().toString());
		tags.setBorder(objectBorder);
		tags.setFont(planefont);
		right.add(tags);
		
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.CENTER);
	}

}