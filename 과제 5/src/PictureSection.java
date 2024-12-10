import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PictureSection extends JFrame {

	private PictureList fullList;
	private PictureList currentList;
	private JScrollPane picturePane;
	private JButton showAll;
	private JButton addPicture;
	private JButton deletePicture;
	private JButton load;
	private JButton save;
	private JButton search;

	PictureSection(PictureList pictureList) {
		super();
		fullList=pictureList;
		currentList=pictureList;
		setLayout(new BorderLayout());
		setSize(650,800);
		setTitle("Simple Picture Search");
		showAll=new JButton("Show All Pictures");
		add(showAll,BorderLayout.NORTH);
		
		JPanel eastPanel=new JPanel(new GridLayout(5,1));
		addPicture=new JButton("ADD");
		addPicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPictureSection();
				
			}
		});
		eastPanel.add(addPicture);
		deletePicture=new JButton("DELETE");
		eastPanel.add(deletePicture);
		load=new JButton("LOAD");
		eastPanel.add(load);
		save=new JButton("SAVE");
		eastPanel.add(save);
		search=new JButton("SEARCH");
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchPictureSection();
			}
		});
		eastPanel.add(search);
		add(eastPanel,BorderLayout.EAST);
		
		picturePane=new JScrollPane(drawPictureListPanelByCurrentList());
		add(picturePane,BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel drawPictureListPanelByCurrentList() {
		JPanel pictures=new JPanel(new GridLayout(currentList.size(),1));
		for(Picture picture:currentList.getPictureList()) {
			pictures.add(new PicturePanel(picture));
		}
		return pictures;
	}
}