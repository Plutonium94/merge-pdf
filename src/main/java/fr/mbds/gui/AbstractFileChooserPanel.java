package fr.mbds.gui;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.*;

public abstract class AbstractFileChooserPanel extends JPanel implements ActionListener {

	private File selectedFile = null;
	private JFileChooser fileChooser = new JFileChooser(".");
	private JTextField chosenPathField = new JTextField(15);
	private File currentDirectory = null;

	public AbstractFileChooserPanel() {
		// add(fileChooser);
		add(chosenPathField);
	}

	public AbstractFileChooserPanel(String initDirectoryName) {
		this();
		selectedFile = new File(initDirectoryName);
		fileChooser.setCurrentDirectory(selectedFile);
		chosenPathField.setText(initDirectoryName);
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File f) {
		selectedFile = f;
		chosenPathField.setText(f.getAbsolutePath());
	}

	public void setFileFilter(FileFilter f) {
		fileChooser.setFileFilter(f);
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		selectedFile = fileChooser.getSelectedFile();
		// chosenPathField.setText(selectedFile.getSelectedFile());				
	}

	public void addKeyListener(KeyListener kl ) {
		chosenPathField.addKeyListener(kl);
	}



	public String getText() {
		return chosenPathField.getText();
	}

	public boolean isSelected() {
		return selectedFile != null;
	}

	public File getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
		fileChooser.setCurrentDirectory(currentDirectory);
	}

}