import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditor extends JFrame implements ActionListener {

	JTextArea textWritingArea;
	JScrollPane scrollPane;
	JSpinner fontSizeSpinner;
	JLabel fontSizeLabel;
	JButton fontColorButton;
	JComboBox fontBox;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;


	public TextEditor() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(800, 900);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);



		textWritingArea = new JTextArea();
		textWritingArea.setLineWrap(true);
		textWritingArea.setWrapStyleWord(true);
		textWritingArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// Text are is added to the scroll pane which is then added to the frame
		scrollPane = new JScrollPane(textWritingArea);
		scrollPane.setPreferredSize(new Dimension(750, 775));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				textWritingArea.setFont(
						new Font(textWritingArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
			}

		});

		fontSizeLabel = new JLabel("Font Size: ");

		fontColorButton = new JButton("Font Color");
		fontColorButton.addActionListener(this);

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Times New Roman");

		this.add(fontSizeLabel);
		this.add(fontSizeSpinner); // Add spinners to the frame first so that they can be added to the top of the
									// frame
		this.add(fontColorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fontColorButton) {
			JColorChooser fontColor = new JColorChooser();
			Color color = fontColor.showDialog(null, "Choose a Font Color", Color.black);
			textWritingArea.setForeground(color);
		}

		if (e.getSource() == fontBox) {
			textWritingArea.setFont(
					new Font((String) fontBox.getSelectedItem(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
		}

	}

}
