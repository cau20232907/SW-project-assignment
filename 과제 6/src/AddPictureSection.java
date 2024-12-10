import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddPictureSection extends JFrame {
	
	private JButton imageButton;
	private JLabel image;
	private final JPanel pictureInfo;
	private String imageFileName;
	private final List<EmptyStuffPanel> stuffPanelList;

	AddPictureSection(PictureSection objectFrame) {
		super();
		setLayout(new BorderLayout(2,2));
		setSize(550,300);
		setTitle("Add a Picture");
		pictureInfo=new JPanel(new BorderLayout(2,2));
		
		JPanel north=new JPanel(new GridLayout(1,4));
		north.add(new JLabel("Time"));
		JTextField time=new JTextField();
		north.add(time);
		north.add(new JLabel("(Picture) Tags"));
		JTextField tags=new JTextField();
		north.add(tags);
		pictureInfo.add(north,BorderLayout.NORTH);
		
		imageButton=new JButton("Select Image File");
		imageButton.addActionListener(new imageEvent());
		pictureInfo.add(imageButton,BorderLayout.WEST);
		
		stuffPanelList=new ArrayList<EmptyStuffPanel>();
		EmptyStuffPanel stuffPanel=new EmptyStuffPanel();
		stuffPanelList.add(stuffPanel);
		JPanel stuffs=new JPanel(new GridLayout(stuffPanelList.size(),1));
		stuffs.setBorder(new LineBorder(Color.GRAY,1));
		stuffs.add(stuffPanel);
		
		pictureInfo.add(new JScrollPane(stuffs),BorderLayout.CENTER);
		
		JPanel south=new JPanel(new GridLayout());
		south.add(new JLabel("Comments"));
		JTextField comments=new JTextField();
		south.add(comments);
		pictureInfo.add(south,BorderLayout.SOUTH);
		add(pictureInfo,BorderLayout.CENTER);
		
		JPanel buttom=new JPanel(new BorderLayout());
		JButton addStuff=new JButton("More Stuff");
		addStuff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmptyStuffPanel stuffPanel=new EmptyStuffPanel();
				stuffPanelList.add(stuffPanel);
				stuffs.setLayout(new GridLayout(stuffPanelList.size(),1));
				stuffs.add(stuffPanel);
				validate();
				repaint();
			}
		});
		buttom.add(addStuff,BorderLayout.WEST);
		JButton endInput=new JButton("OK - INPUT END");
		endInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<Stuff> stuffList=getStuffInput();
					if(imageFileName==null)
						throw new IllegalStateException("No image");
					Picture resultPicture=new Picture("m_"+imageFileName,time.getText(),
							new Image("IMG"+time.getText(),imageFileName,null,null),
							stuffList,new Tag(tags.getText()),comments.getText());
					objectFrame.addPicture(resultPicture);

					dispose();
				} catch(IllegalStateException e2) {
					//할 일 없음(추가만 안 함)
				}
			}
		});
		buttom.add(endInput,BorderLayout.EAST);
		add(buttom,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	private class imageEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser imageFileChoose=new JFileChooser();
			if(imageFileChoose.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File imageFile=imageFileChoose.getSelectedFile();
				try {
					JLabel newImage=new JLabel(new ImageIcon(
							new ImageIcon(imageFile.getPath()).getImage()
							.getScaledInstance(200, 200, java.awt.Image.SCALE_AREA_AVERAGING)));
					//new ImageIcon(read file) -> getImage -> getScaledInstance
					//		-> new ImageIcon -> new JLabel

					if(imageButton!=null) {
						pictureInfo.remove(imageButton);
						imageButton=null;
					} else {
						pictureInfo.remove(image);
					}
					image=newImage;
					image.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							actionPerformed(null);
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
					});
					pictureInfo.add(image,BorderLayout.WEST);
					imageFileName=imageFile.getPath();
					revalidate();
					repaint();
				} catch(Exception e2) {
					//할 일 없음
				}
			}
		}
	}
	
	private List<Stuff> getStuffInput() {
		List<Stuff> stuffList=new ArrayList<Stuff>();
		for(EmptyStuffPanel stuff:stuffPanelList) {
			try {
			stuffList.add(StuffList.addStuff(stuff.getStuff(true)));
			} catch(IllegalStateException e) {
				//할 일 없음(추가만 안 함)
			}
		}
		return stuffList;
	}
}