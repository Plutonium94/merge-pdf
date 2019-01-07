package fr.mbds.gui;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.List;


public class FileChooserGroupPanel extends JPanel {

	private List<FileChooserPanel> fileChooserPanels = new ArrayList<FileChooserPanel>();

	public FileChooserGroupPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new FileChooserPanel(this));

	}

	public void add(FileChooserPanel fcp) {
		super.add(fcp);
		fileChooserPanels.add(fcp);
		revalidate();
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

	public int getNumberOfFileChoosers() {
		return fileChooserPanels.size();
	}

	public String toString() {
		return fileChooserPanels.toString();
	}

}