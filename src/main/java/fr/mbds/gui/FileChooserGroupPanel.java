package fr.mbds.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.io.File;


public class FileChooserGroupPanel extends JPanel {

	private List<FileChooserPanel> fileChooserPanels = new ArrayList<FileChooserPanel>();

	public FileChooserGroupPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new FileChooserPanel(this));
		// this.setBorder(new EmptyBorder(0, this.getWidth(), this.getHeight(), 0));
		setMaximumSize(getPreferredSize());

		System.out.println(getWidth() + ", " + getHeight());
	}

	public void add(FileChooserPanel fcp) {
		super.add(fcp);
		fileChooserPanels.add(fcp);
		setMaximumSize(getPreferredSize());
		revalidate();
		System.out.println(getWidth() + ", " + getHeight());
	}

	public void remove(FileChooserPanel fcp) {
		fileChooserPanels.remove(fcp);
	}

	public String[] getAllFileNames() {
		List<String> res = new ArrayList<String>(fileChooserPanels.size());
		for(FileChooserPanel fcp : fileChooserPanels) {
			res.add(fcp.getSelectedFile().getAbsolutePath());
		}
		return res.toArray(new String[0]);
	}

	public File getFileAt(int i) {
		if(i < 0 || i >= fileChooserPanels.size()) { return null; }
		return fileChooserPanels.get(i).getSelectedFile();
	}

	public File getFirstFileDirectory() {
		if(fileChooserPanels.isEmpty()) { return null; }
		return fileChooserPanels.get(0).getSelectedFile().getParentFile();
	}

	public int getNumberOfFileChoosers() {
		return fileChooserPanels.size();
	}

	public String toString() {
		return fileChooserPanels.toString();
	}

}