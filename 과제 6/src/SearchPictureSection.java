import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class SearchPictureSection extends JFrame {

	private final JTextField timeFrom;
	private final JTextField timeTo;
	private final JTextField tags;
	private final JTextField comments;
	private final JTextField generalSearch;
	private final EmptyStuffPanel stuffToSearch;
	
	private static String dateTimeFormatToString="yyyy-MM-dd_HH:mm:ss";

	SearchPictureSection(PictureSection objectFrame) {
		super();
		setSize(600,300);
		setLayout(new BorderLayout());
		setTitle("Search between picture");
		
		JPanel timeSearch=new JPanel(new GridLayout(2, 3));
		timeSearch.add(new JLabel("From"));
		timeFrom=new JTextField();
		timeSearch.add(timeFrom);
		timeSearch.add(new JLabel("("+dateTimeFormatToString+")"));
		timeSearch.add(new JLabel("To"));
		timeTo=new JTextField();
		timeSearch.add(timeTo);
		timeSearch.setBorder(new TitledBorder("Time Search"));
		add(timeSearch,BorderLayout.NORTH);
		
		JPanel wordSearch=new JPanel(new BorderLayout());
		JPanel otherObjects=new JPanel(new GridLayout(2,2));
		otherObjects.add(new JLabel("Tags"));
		tags=new JTextField();
		otherObjects.add(tags);
		otherObjects.add(new JLabel("Comments"));
		comments=new JTextField();
		otherObjects.add(comments);
		wordSearch.add(otherObjects,BorderLayout.WEST);
		
		stuffToSearch=new EmptyStuffPanel();
		wordSearch.add(stuffToSearch,BorderLayout.CENTER);
		
		JPanel options=new JPanel(new GridLayout());
		options.add(new JLabel("General Search"));
		generalSearch=new JTextField();
		options.add(generalSearch);
		wordSearch.add(options,BorderLayout.SOUTH);
		wordSearch.setBorder(new TitledBorder("Keyword Search"));
		add(wordSearch,BorderLayout.CENTER);
		
		JPanel searchButtons=new JPanel();
		JButton andSearch=new JButton("AND SEARCH");
		andSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectFrame.setCurrentList(andFilter(objectFrame.getCurrentList()));
				dispose();
			}
		});
		searchButtons.add(andSearch);
		JButton orSearch=new JButton("OR SEARCH");
		orSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectFrame.setCurrentList(orFilter(objectFrame.getCurrentList()));
				dispose();
			}
		});
		searchButtons.add(orSearch);
		JButton close=new JButton("CLOSE");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		searchButtons.add(close);
		add(searchButtons,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private PictureList andFilter(PictureList initialList) {
		PictureList resultList=initialList;
		
		LocalDateTime startTime;
		LocalDateTime endTime;
		try {
			startTime=LocalDateTime.parse(timeFrom.getText(),
					DateTimeFormatter.ofPattern(dateTimeFormatToString));
		} catch(DateTimeParseException e) {
			startTime=null;
		}
		try {
			endTime=LocalDateTime.parse(timeTo.getText(),
					DateTimeFormatter.ofPattern(dateTimeFormatToString));
		} catch(DateTimeParseException e) {
			endTime=null;
		}
		if(startTime!=null||endTime!=null)
			resultList=resultList.selectTime(startTime, endTime);
		
		if(!tags.getText().isBlank())
			resultList=resultList.selectTags(new Tag(tags.getText()));
		
		try {
			resultList=resultList.selectStuffAndSearch(stuffToSearch.getStuff(false));
		}catch(IllegalStateException e) {
			//할 일 없음
		}
		
		if(!comments.getText().isBlank())
			resultList=resultList.selectComment(comments.getText());
		
		PictureList generalFilterResult=generalFilter(resultList);
		if(generalFilterResult!=null) 
			return generalFilterResult;
		else
			return resultList;
	}
	
	private PictureList orFilter(PictureList initialList) {

		List<PictureList> resultLists=new ArrayList<PictureList>();
		
		LocalDateTime startTime;
		LocalDateTime endTime;
		try {
			startTime=LocalDateTime.parse(timeFrom.getText(),
					DateTimeFormatter.ofPattern(dateTimeFormatToString));
		} catch(DateTimeParseException e) {
			startTime=null;
		}
		try {
			endTime=LocalDateTime.parse(timeTo.getText(),
					DateTimeFormatter.ofPattern(dateTimeFormatToString));
		} catch(DateTimeParseException e) {
			endTime=null;
		}
		if(startTime!=null||endTime!=null)
			resultLists.add(initialList.selectTime(startTime, endTime));

		if(!tags.getText().isBlank())
			resultLists.add(initialList.selectTags(new Tag(tags.getText())));
		try {
			resultLists.add(initialList.selectStuffOrSearch(stuffToSearch.getStuff(false)));
		}catch(IllegalStateException e) {
			//할 일 없음
		}
		if(!comments.getText().isBlank())
			resultLists.add(initialList.selectComment(comments.getText()));
		
		PictureList generalFilterResult=generalFilter(initialList);
		if(generalFilterResult!=null)
			resultLists.add(generalFilterResult);
		
		if(resultLists.size()!=0) {
			PictureList resultList=resultLists.remove(0);
			for(PictureList eachResult:resultLists)
				resultList.mergePictureList(eachResult);

			return resultList;
		}
		else
			return initialList;
	}
	
	private PictureList generalFilter(PictureList initialList) {
		String general=generalSearch.getText();
		if(general.isBlank()) {
			return null;
		} else {
			List<PictureList> resultLists=new ArrayList<PictureList>();
			
			resultLists.add(initialList.selectTags(general));
			resultLists.add(initialList.selectComment(general));
			resultLists.add(initialList.selectStuffOrSearch
					(new Stuff(general,general,general,false)));
			//어디 들어갈 지 모르니 일단 다 찾음

			PictureList resultList=resultLists.remove(0);
			for(PictureList eachResult:resultLists)
				resultList.mergePictureList(eachResult);
			
			return resultList;
		}
	}
}