import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class StuffPanel extends JPanel {

	StuffPanel(Stuff stuff) {
		super(new BorderLayout());
		Font planefont=new Font("SansSerif",Font.PLAIN,12); //단순히 PLAIN으로 변경
		LineBorder objectBorder=new LineBorder(Color.LIGHT_GRAY,1);
		setBorder(new LineBorder(Color.GRAY,1));
		
		JPanel left=new JPanel(new GridLayout(3,1));
		JPanel right=new JPanel(new GridLayout(3,1));
		
		left.add(new JLabel("Type"));
		JLabel type=new JLabel(stuff.getType());
		type.setBorder(objectBorder);
		type.setFont(planefont);
		right.add(type);
		
		left.add(new JLabel("Name"));
		JLabel name=new JLabel(stuff.getName());
		name.setBorder(objectBorder);
		name.setFont(planefont);
		right.add(name);
		
		left.add(new JLabel("Tags"));
		JLabel tags=new JLabel(stuff.getTags().toString());
		tags.setBorder(objectBorder);
		tags.setFont(planefont);
		right.add(tags);
		
		this.add(left,BorderLayout.WEST);
		this.add(right,BorderLayout.CENTER);
	}
}