import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class PicturePanel extends JPanel {

	PicturePanel(Picture picture) {
		super(new BorderLayout(3,3));
		LineBorder border=new LineBorder(Color.LIGHT_GRAY,1);
		Font planefont=new Font("SansSerif",Font.PLAIN,12); //단순히 PLAIN으로 변경
		setBorder(new LineBorder(Color.GRAY,1));
		
		JPanel northPanel=new JPanel(new GridLayout(1,2));
		northPanel.add(new JLabel(picture.getTimeStampToString()));
		String tagString=picture.getTags().toString();
		if(tagString.isBlank()) tagString=" "; //이거라도 있어야 모양이 나옴
		JLabel tags=new JLabel(tagString);
		tags.setBorder(border);
		tags.setFont(planefont);
		northPanel.add(tags);
		add(northPanel,BorderLayout.NORTH);
		
		add(new JLabel(
				new ImageIcon(new ImageIcon(picture.getPictureFileName())
				.getImage().getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING))
				),BorderLayout.WEST);
		//new ImageIcon(read file) -> getImage -> getScaledInstance -> new ImageIcon -> new JLabel
		JPanel stuffs=new JPanel(new GridLayout(picture.getStuffList().size(),1));
		for(Stuff stuff:picture.getStuffList()) {
			stuffs.add(new StuffPanel(stuff));
		}
		stuffs.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
		JScrollPane stuffPane=new JScrollPane(stuffs);
		stuffPane.setPreferredSize(new Dimension(300,200));
		add(stuffPane,BorderLayout.CENTER);
		
		String commentString=picture.getComment();
		if(commentString.isBlank()) commentString=" ";
		JLabel comment=new JLabel(commentString);
		comment.setBorder(border);
		comment.setFont(planefont);
		add(comment,BorderLayout.SOUTH);
	}

}