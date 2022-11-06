import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TextEditor extends JFrame implements ActionListener{
	
	JTextArea textWritingArea;
	JScrollPane scrollPane;
	
	public TextEditor() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(800,800);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		textWritingArea = new JTextArea();
		textWritingArea.setLineWrap(true);
		textWritingArea.setWrapStyleWord(true);
		textWritingArea.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		// Text are is added to the scroll pane which is then added to the frame
		scrollPane = new JScrollPane(textWritingArea);
		scrollPane.setPreferredSize(new Dimension(750, 750));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane);
		this.setVisible(true);
		
	}
		
	
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
