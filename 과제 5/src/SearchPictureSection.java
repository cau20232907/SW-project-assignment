import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class SearchPictureSection extends JFrame {

	private JTextField timeFrom;
	private JTextField timeTo;
	private JTextField tags;
	private JTextField comments;
	private JTextField generalSearch;
	private StuffPanel stuffToSearch;
	private JButton andSearch;
	private JButton orSearch;
	private JButton close;
	
	private static String dateTimeFormatToString="yyyy-MM-dd_HH:mm:ss";

	SearchPictureSection() {
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
		
		stuffToSearch=new StuffPanel();
		wordSearch.add(stuffToSearch,BorderLayout.CENTER);
		
		JPanel options=new JPanel(new GridLayout());
		options.add(new JLabel("General Search"));
		generalSearch=new JTextField();
		options.add(generalSearch);
		wordSearch.add(options,BorderLayout.SOUTH);
		wordSearch.setBorder(new TitledBorder("Keyword Search"));
		add(wordSearch,BorderLayout.CENTER);
		
		JPanel searchButtons=new JPanel();
		andSearch=new JButton("AND SEARCH");
		searchButtons.add(andSearch);
		orSearch=new JButton("OR SEARCH");
		searchButtons.add(orSearch);
		close=new JButton("CLOSE");
		searchButtons.add(close);
		add(searchButtons,BorderLayout.SOUTH);
		
		setVisible(true);
	}

}