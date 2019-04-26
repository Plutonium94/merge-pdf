package fr.mbds.gui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;

public class SaveFolderChooserPanel extends AbstractFileChooserPanel {

	private JButton dialogOpenButton = new JButton("...");
	private FileChooserGroupPanel fileChooserGroupPanel = null;


	public SaveFolderChooserPanel() {
		super();
		init();
	}

	public SaveFolderChooserPanel(FileChooserGroupPanel fcgp) {
		super();
		this.fileChooserGroupPanel = fcgp;
		init();
	}

	private void init() {
		JFileChooser fc = getFileChooser();

		add(dialogOpenButton);

		dialogOpenButton.addActionListener((ae)->{

			if(fileChooserGroupPanel != null) {
				File currentDirectory = fileChooserGroupPanel.getFirstFileDirectory();
				if(currentDirectory != null) {
					fc.setCurrentDirectory(currentDirectory);
				}
			}

			int fcDialogRes = fc.showDialog(null, "Choose target location");
			if(fcDialogRes == JFileChooser.APPROVE_OPTION) {
				setSelectedFile(fc.getSelectedFile());
			}
			
		});

		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		fc.setFileFilter(new FileFilter(){

			public boolean accept(File f) {
				return f.isDirectory();
			}

			public String getDescription() {
				return "accepts folders only";
			}
		});

		// setFileChooser(fileChooser);

		// setChosenPathFieldText("Select destination folder");
	}
}