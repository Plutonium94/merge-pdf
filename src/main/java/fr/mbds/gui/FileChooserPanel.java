package fr.mbds.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;


public class FileChooserPanel extends JPanel {

	private File selectedFile = null;
	private JFileChooser fileChooser = new JFileChooser(".");
	private JTextField chosenPathField = new JTextField(15);
	private JButton fileOpenButton = new JButton("...");
	private JButton anotherFileButton = new JButton("+");

	public FileChooserPanel() {

		fileChooser.setFileFilter(new FileNameExtensionFilter(".pdf files only", "pdf", "PDF"));

		anotherFileButton.setEnabled(false);
		// add(fileChooser);
		add(chosenPathField);
		add(fileOpenButton);
		add(anotherFileButton);
		fileOpenButton.addActionListener((ae)->{
			int fcDialogRes = fileChooser.showDialog(null, "Add to be merged");
			if(fcDialogRes == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				chosenPathField.setText(selectedFile.getAbsolutePath());
				anotherFileButton.setEnabled(true);
			}
		});

		anotherFileButton.addActionListener((ae)->{
			if(selectedFile != null) {
				Container parentContainer = FileChooserPanel.this.getParent();
				parentContainer.add(new FileChooserPanel());
				parentContainer.validate();
			}
		});

		chosenPathField.addKeyListener(new KeyAdapter() {

			/** if chosen file field is not empty enable button,
			* disable otherwise
			*/
			@Override
			public void keyPressed(KeyEvent ke) {
				anotherFileButton.setEnabled(! "".equals(chosenPathField.getText()));
			}
		});


	}


	File getSelectedFile() {
		return selectedFile;
	}


}