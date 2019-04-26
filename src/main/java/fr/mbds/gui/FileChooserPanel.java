package fr.mbds.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;


public class FileChooserPanel extends AbstractFileChooserPanel {

	private JButton fileOpenButton = new JButton("...");
	private JButton anotherFileButton = new JButton("+"); 

	private FileChooserGroupPanel fileChooserGroup;

	public FileChooserPanel(FileChooserGroupPanel fcg) {
		super();
		fileChooserGroup = fcg;
		setFileFilter(new FileNameExtensionFilter(".pdf files only", "pdf", "PDF"));

		anotherFileButton.setEnabled(false);
		// add(fileChooser);
		// add(chosenPathField);
		add(fileOpenButton);
		add(anotherFileButton);
		fileOpenButton.addActionListener((ae)->{
			JFileChooser fc = getFileChooser();
			int fcDialogRes = fc.showDialog(null, "Add to be merged");
			if(fcDialogRes == JFileChooser.APPROVE_OPTION) {
				setSelectedFile(fc.getSelectedFile());
				// chosenPathField.setText(selectedFile.getAbsolutePath());
				anotherFileButton.setEnabled(true);
			}
		});

		anotherFileButton.addActionListener((ae)->{
			if(isSelected()) {
				/*Container parentContainer = FileChooserPanel.this.getParent();
				parentContainer.add(new FileChooserPanel());
				parentContainer.validate();*/
				FileChooserPanel nextFileChooserPanel = new FileChooserPanel(fileChooserGroup);
				fileChooserGroup.add(nextFileChooserPanel);
				System.out.println(getSelectedFile().getParentFile());
				nextFileChooserPanel.setCurrentDirectory(getSelectedFile().getParentFile());
				System.out.println(fileChooserGroup.getNumberOfFileChoosers());
			}
		});

		addKeyListener(new KeyAdapter() {

			/** if chosen file field is not empty enable button,
			* disable otherwise
			*/
			@Override
			public void keyPressed(KeyEvent ke) {
				anotherFileButton.setEnabled(! isSelected());
			}
		});
	}



}