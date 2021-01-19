/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import static java.lang.Integer.parseInt;

public class View {

	public View () {

		JFrame frame = new JFrame("Direct Mapping Cache Simulator");
        frame.setSize(1000, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

	// Program Flow
		JLabel flowLabel = new JLabel("Program Flow");
        flowLabel.setBounds(50, 35, 200, 26);
        flowLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        frame.add(flowLabel);

		JTextArea flowArea = new JTextArea();
        flowArea.setLineWrap(true);
        // flowArea.setEditable(false);

		JScrollPane flowScroll = new JScrollPane(flowArea);
        flowScroll.setBounds(50, 70, 420, 420);
        frame.add(flowScroll);
        // flowScroll.setVisible(true);

	// Cache Size
		JLabel cmLabel = new JLabel("Cache size");
		cmLabel.setBounds(510, 70, 200, 25);
		cmLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(cmLabel);

		JTextField cmInput = new JTextField();
		cmInput.setBounds(510, 100, 200, 25);
		frame.add(cmInput);

		// Cache memory radio buttons
		JRadioButton cmBlock = new JRadioButton("Block");
		cmBlock.setBounds(510, 130, 80, 25);
		cmBlock.setSelected(true);
		frame.add(cmBlock);

		JRadioButton cmWord = new JRadioButton("Word");
		cmWord.setBounds(590, 130, 80, 25);
		frame.add(cmWord);

		// Group cache memory radio buttons
		ButtonGroup cmGroup = new ButtonGroup();
		cmGroup.add(cmBlock);
		cmGroup.add(cmWord);

	// Main Memory Size
		JLabel mmLabel = new JLabel("Main memory size");
		mmLabel.setBounds(730, 70, 200, 25);
		mmLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(mmLabel);
		
		JTextField mmInput = new JTextField();
		mmInput.setBounds(730, 100, 200, 25);
		frame.add(mmInput);
		
		// Main memory radio buttons
		JRadioButton mmBlock = new JRadioButton("Block");
		mmBlock.setBounds(730, 130, 80, 25);
		mmBlock.setSelected(true);
		frame.add(mmBlock);

		JRadioButton mmWord = new JRadioButton("Word");
		mmWord.setBounds(810, 130, 80, 25);
		frame.add(mmWord);

		// Group main memory radio buttons
		ButtonGroup mmGroup = new ButtonGroup();
		mmGroup.add(mmBlock);
		mmGroup.add(mmWord);

	// Block size in words
		JLabel blockSizeLabel = new JLabel("Block Size in Words");
		blockSizeLabel.setBounds(510, 180, 420, 28);
		blockSizeLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(blockSizeLabel);
		
		JTextField blockSize = new JTextField();
		blockSize.setBounds(510, 210, 420, 28);
		frame.add(blockSize);

	// Cache access time
		JLabel catLabel = new JLabel("Cache Access Time (in nanoseconds)");
		catLabel.setBounds(510, 260, 420, 28);
		catLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(catLabel);
		
		JTextField cat = new JTextField();
		cat.setBounds(510, 290, 420, 28);
		frame.add(cat);

	// Memory access time
		JLabel matLabel = new JLabel("Memory Access Time (in nanoseconds)");
		matLabel.setBounds(510, 340, 420, 28);
		matLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(matLabel);
		
		JTextField mat = new JTextField();
		mat.setBounds(510, 370, 420, 28);
		frame.add(mat);

	// Run button
		JButton runButton = new JButton("Run");
		runButton.setBounds(670, 430, 100, 28);
		frame.add(runButton);

	// Output screen

	// Output label
		JLabel outputLabel = new JLabel("Output");
        outputLabel.setBounds(50, 35, 200, 26);
        outputLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        frame.add(outputLabel);

	// Reset and export buttons
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(600, 430, 100, 28);
		frame.add(resetButton);

		JButton exportButton = new JButton("Export");
		exportButton.setBounds(720, 430, 100, 28);
		frame.add(exportButton);



	// Conditional -- comment out output visibility to see output screen
		// Input screen
		// flowLabel.setVisible(false);
		// runButton.setVisible(false);

		// Output screen
		outputLabel.setVisible(false);
		resetButton.setVisible(false);
		exportButton.setVisible(false);

        frame.setVisible(true);
	}

	// temp main to see view
	public static void main(String[] args) {
		new View();
	}
}
