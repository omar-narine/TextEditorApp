import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

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

		// Menu Bar
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");

		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);

		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		this.setJMenuBar(menuBar);
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

		else if (e.getSource() == fontBox) {
			textWritingArea.setFont(
					new Font((String) fontBox.getSelectedItem(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
		}

		else if (e.getSource() == openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			fileChooser.setFileFilter(filter);
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					
					if (file.isFile()) {
						textWritingArea.setText("");
						while (fileIn.hasNextLine()) {
							String line = fileIn.nextLine() + "\n";
							textWritingArea.append(line);
						}
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
				
			}
			
			
		}

		else if (e.getSource() == saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));

			int response = fileChooser.showSaveDialog(null);

			if (response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter writer = null;

				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					writer = new PrintWriter(file);
					writer.println(textWritingArea.getText());

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					writer.close();
				}
			}

		}

		else if (e.getSource() == exitItem) {
			System.exit(0);
		}

	}

}
