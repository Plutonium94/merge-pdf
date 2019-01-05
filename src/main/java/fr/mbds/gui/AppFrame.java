package fr.mbds.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import fr.mbds.PDFMerger;

public class AppFrame extends JFrame {


	private JButton mergeButton = new JButton("Merge");
	private JPanel panel = new JPanel();
	private JLabel messageLabel = new JLabel();

	private static final String CTRL_W_PRESSED = "ctrl+w typed";

	public AppFrame() {
		setLocationRelativeTo(null);
		setSize(600,600);
		setTitle("PDF Merger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(new JLabel("Choose file to be added"));
		panel.add(new FileChooserPanel());
		add(panel, BorderLayout.CENTER);
		setupSouth();
		setListeners();
		setVisible(true);
	}

	private void setupSouth()  {
		JPanel southPanel = new JPanel();
		southPanel.add(messageLabel);
		southPanel.add(mergeButton);
		add(southPanel, BorderLayout.SOUTH);
	}

	private void setListeners() {
		JRootPane rootPane = this.getRootPane();
		InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(new Character('W'), InputEvent.CTRL_DOWN_MASK, false), CTRL_W_PRESSED);
		ActionMap am = rootPane.getActionMap();

		am.put(CTRL_W_PRESSED, new AbstractAction() {

			public void actionPerformed(ActionEvent ae) {
				AppFrame.this.dispose();
			}
		});

		mergeButton.addActionListener((ae)-> {
			/*System.out.println(AppFrame.this.getAllComponents());
			try {
				boolean mergeRes = PDFMerger.merge();
				messageLabel.setText("Merge " + ((mergeRes)?"":"un") + "successful");
			} catch(IOException ioe) {
				messageLabel.setText(ioe.getMessage());
			}*/
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