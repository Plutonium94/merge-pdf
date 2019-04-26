package fr.mbds.gui;

import fr.mbds.PDFMerger;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.File;


public class AppFrame extends JFrame {


	private JButton mergeButton = new JButton("Merge");
	private JLabel messageLabel = new JLabel();
	/** The central panel occupying the BorderLayout.CENTER */
	private JPanel panel = new JPanel();

	private FileChooserGroupPanel fileChooserGroup = new FileChooserGroupPanel();

	private SaveFolderChooserPanel saveFolderChooser = new SaveFolderChooserPanel(fileChooserGroup);

	private static final String CTRL_W_PRESSED = "ctrl+w typed";

	public AppFrame() {
		setLocationRelativeTo(null);
		setSize(600,600);
		setTitle("PDF Merger");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setupCenter();
		setupSouth();
		setListeners();
		setVisible(true);
		System.out.println(fileChooserGroup);
	}

	private void setupCenter() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(panel.getPreferredSize());
		panel.add(new JLabel("Choose file to be added"));
		panel.add(fileChooserGroup);
		panel.add(new JLabel("Choose save file location"));
		panel.add(saveFolderChooser);
		panel.validate();
		add(panel, BorderLayout.CENTER);
	}

	private void setupSouth()  {
		JPanel southPanel = new JPanel();
		southPanel.add(messageLabel);
		southPanel.add(mergeButton);
		add(southPanel, BorderLayout.SOUTH);
	}

	private void setListeners() {
		JRootPane rootPane = this.getRootPane();

		/* Close on Ctrl + W */
		InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(new Character('W'), InputEvent.CTRL_DOWN_MASK, false), CTRL_W_PRESSED);
		ActionMap am = rootPane.getActionMap();

		am.put(CTRL_W_PRESSED, new AbstractAction() {

			public void actionPerformed(ActionEvent ae) {
				AppFrame.this.dispose();
			}
		});

		/* merge PDF files */
		mergeButton.addActionListener((ae)-> {

			System.out.println(Arrays.toString(fileChooserGroup.getAllFileNames()));
			try {
				String destination = saveFolderChooser.getSelectedFile() + File.separator + "merged.pdf";
				System.out.println(destination);
				boolean mergeRes = PDFMerger.mergeInto(destination, fileChooserGroup.getAllFileNames());
				messageLabel.setText("Merge " + ((mergeRes)?"":"un") + "successful");
			} catch(IOException ioe) {
				messageLabel.setText(ioe.getMessage());
			}
		});


	}

	List<Component> getAllComponents() {
		List<Component> res = new ArrayList<Component>();
		Stack<Component> pile = new Stack<Component>();
		pile.add(this);
		while(!pile.isEmpty()) {
			Component cmp = pile.pop();
			res.add(cmp);
			if(cmp instanceof Container) {
				Container parent = (Container)cmp;
				for(Component c : parent.getComponents()) {
					pile.add(c);
				}
			}
		}
		return res;
	}

	


}