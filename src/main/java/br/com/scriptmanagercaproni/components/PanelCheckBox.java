package br.com.scriptmanagercaproni.components;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.com.scriptmanagercaproni.control.ScriptFolderControl;

public class PanelCheckBox extends JPanel {
	private static final long serialVersionUID = -6774516887777060499L;

	public void createCheckBox(String folder) {
		this.setLayout(new GridBagLayout());
		List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
		ScriptFolderControl scriptFolderControl = new ScriptFolderControl(folder);
		GridBagConstraints c = new GridBagConstraints();
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		int x = 0;
		int y = 0;
		for (String element : scriptFolderControl.getSystemDirectoriesAndName().values()) {

			JCheckBox box = new JCheckBox(element);
			checkboxes.add(box);
			c.weightx = 0.5;
			c.gridx = x;
			c.gridy = y;
			this.add(box, c);
			if (y >= 6) {
				x++;
				y = -1;
			}
			y++;

		}

	}
}