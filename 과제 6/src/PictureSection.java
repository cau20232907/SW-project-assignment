import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PictureSection extends JFrame {

	private final PictureSection thisFrame; //ActionListener에서 this 접근을 위한 것
	private PictureList fullList;
	private PictureList currentList;
	private JScrollPane picturePane;
	private Picture latestClikedPicture;
	private String pictureListLoadfile;
	private String pictureListSavefile;
	
	PictureSection(PictureList pictureList, String pictureListLoadfile, String pictureListSavefile){
		super();
		fullList=pictureList;
		this.pictureListLoadfile=pictureListLoadfile;
		this.pictureListSavefile=pictureListSavefile;
		thisFrame=this;
		pictureSectionBulid();
	}
	
	PictureSection(String pictureListLoadfile, String pictureListSavefile){
		super();
		fullList=new PictureList();
		this.pictureListLoadfile=pictureListLoadfile;
		this.pictureListSavefile=pictureListSavefile;
		thisFrame=this;
		pictureSectionBulid();
	}
	
	PictureSection(PictureList pictureList){
		super();
		fullList=pictureList;
		thisFrame=this;
		pictureSectionBulid();
	}
	
	PictureSection(){
		super();
		fullList=new PictureList();
		thisFrame=this;
		pictureSectionBulid();
	}

	private void pictureSectionBulid() {
		fullList.sortByDate();
		currentList=fullList;
		setLayout(new BorderLayout());
		setSize(650,800);
		setTitle("Simple Picture Search");
		JButton showAll=new JButton("Show All Pictures");
		showAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentList=fullList;
				drawPictureListPanelByCurrentList(true);
			}
		});
		add(showAll,BorderLayout.NORTH);
		
		JPanel eastPanel=new JPanel(new GridLayout(5,1));
		JButton addPicture=new JButton("ADD");
		addPicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				latestClikedPicture=null;
				new AddPictureSection(thisFrame);
			}
		});
		eastPanel.add(addPicture);
		
		JButton deletePicture=new JButton("DELETE");
		deletePicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(latestClikedPicture!=null) {
					fullList.deletePicture(latestClikedPicture);
					currentList.deletePicture(latestClikedPicture);
					drawPictureListPanelByCurrentList(true);
				} else {
					JFrame dialog=new JFrame();
					dialog.setLayout(new BorderLayout());
					dialog.add(new JLabel("Pick a picture First."),BorderLayout.CENTER);
					JButton close=new JButton("OK");
					close.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.dispose();
						}
					});
					dialog.add(close,BorderLayout.SOUTH);
					dialog.setSize(200, 100);
					dialog.setVisible(true);
				}
			}
		});
		eastPanel.add(deletePicture);
		
		JButton load=new JButton("LOAD");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fullList=new PictureList(pictureListLoadfile);
				fullList.sortByDate();
				currentList=fullList;
				drawPictureListPanelByCurrentList(true);
			}
		});
		eastPanel.add(load);
		
		JButton save=new JButton("SAVE");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				latestClikedPicture=null;
				currentList.savePictureToFile(pictureListSavefile);
			}
		});
		eastPanel.add(save);
		
		JButton search=new JButton("SEARCH");
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				latestClikedPicture=null;
				new SearchPictureSection(thisFrame);
			}
		});
		eastPanel.add(search);
		add(eastPanel,BorderLayout.EAST);
		
		drawPictureListPanelByCurrentList(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void drawPictureListPanelByCurrentList(boolean ifRepaint) {
		latestClikedPicture=null;
		JPanel pictures=new JPanel(new GridLayout(currentList.size(),1));
		for(Picture picture:currentList.getPictureList()) {
			pictures.add(new PicturePanel(picture,this));
		}
		if(picturePane!=null) remove(picturePane);
		picturePane=new JScrollPane(pictures);
		add(picturePane,BorderLayout.CENTER);
		if(ifRepaint) {
			revalidate();
			repaint();
		}
	}
	
	void addPicture(Picture pic) {
		fullList.add(pic);
		fullList.sortByDate();
		currentList=fullList;
		drawPictureListPanelByCurrentList(true);
	}

	void setLatestClikedPicture(Picture latestClikedPicture) {
		this.latestClikedPicture = latestClikedPicture;
	}

	void setPictureListLoadfile(String pictureListLoadfile) {
		this.pictureListLoadfile = pictureListLoadfile;
	}

	void setPictureListSavefile(String pictureListSavefile) {
		this.pictureListSavefile = pictureListSavefile;
	}

	void setCurrentList(PictureList currentList) {
		currentList.sortByDate();
		this.currentList = currentList;
		drawPictureListPanelByCurrentList(true);
	}

	PictureList getCurrentList() {
		return currentList;
	}
}