import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EmptyStuffPanel extends JPanel {

	private final JTextField type;
	private final JTextField name;
	private final JTextField tags;
	
	EmptyStuffPanel(){
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

	Stuff getStuff(boolean forObject) {
		return new Stuff(type.getText(),name.getText(),tags.getText(),forObject);
	}
}