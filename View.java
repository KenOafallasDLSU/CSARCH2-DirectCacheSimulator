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
	public JFrame frame;

	public JLabel flowLabel;
	public JTextArea flowArea;
	public JScrollPane flowScroll;

	public JLabel cmLabel;
	public JTextField cmInput;
	public JRadioButton cmBlock;
	public JRadioButton cmWord;

	public JLabel mmLabel;
	public JTextField mmInput;
	public JRadioButton mmBlock;
	public JRadioButton mmWord;
	
	public JLabel blockSizeLabel;
	public JTextField blockSize;

	public JLabel catLabel;
	public JTextField cat;

	public JLabel matLabel;
	public JTextField mat;

	public JButton runButton;
	
	public JLabel outputLabel;
	public JButton resetButton;
	public JButton exportButton;

	public View () {
		frame = new JFrame("Direct Mapping Cache Simulator");
        frame.setSize(1000, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

	// Program Flow
		flowLabel = new JLabel("Program Flow");
        flowLabel.setBounds(50, 35, 200, 26);
        flowLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        frame.add(flowLabel);
		flowLabel.setVisible(true);

		flowArea = new JTextArea();
        flowArea.setLineWrap(true);

		flowScroll = new JScrollPane(flowArea);
        flowScroll.setBounds(50, 70, 420, 420);
        frame.add(flowScroll);
        // flowScroll.setVisible(true);

	// Cache Size
		cmLabel = new JLabel("Cache size");
		cmLabel.setBounds(510, 70, 200, 25);
		cmLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(cmLabel);

		cmInput = new JTextField();
		cmInput.setBounds(510, 100, 200, 25);
		frame.add(cmInput);

		// Cache memory radio buttons
		cmBlock = new JRadioButton("Block");
		cmBlock.setBounds(510, 130, 80, 25);
		cmBlock.setSelected(true);
		frame.add(cmBlock);

		cmWord = new JRadioButton("Word");
		cmWord.setBounds(590, 130, 80, 25);
		frame.add(cmWord);

		// Group cache memory radio buttons
		ButtonGroup cmGroup = new ButtonGroup();
		cmGroup.add(cmBlock);
		cmGroup.add(cmWord);

	// Main Memory Size
		mmLabel = new JLabel("Main memory size");
		mmLabel.setBounds(730, 70, 200, 25);
		mmLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(mmLabel);
		
		mmInput = new JTextField();
		mmInput.setBounds(730, 100, 200, 25);
		frame.add(mmInput);
		
		// Main memory radio buttons
		mmBlock = new JRadioButton("Block");
		mmBlock.setBounds(730, 130, 80, 25);
		mmBlock.setSelected(true);
		frame.add(mmBlock);

		mmWord = new JRadioButton("Word");
		mmWord.setBounds(810, 130, 80, 25);
		frame.add(mmWord);

		// Group main memory radio buttons
		ButtonGroup mmGroup = new ButtonGroup();
		mmGroup.add(mmBlock);
		mmGroup.add(mmWord);

	// Block size in words
		blockSizeLabel = new JLabel("Block Size in Words");
		blockSizeLabel.setBounds(510, 180, 420, 28);
		blockSizeLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(blockSizeLabel);
		
		blockSize = new JTextField();
		blockSize.setBounds(510, 210, 420, 28);
		frame.add(blockSize);

	// Cache access time
		catLabel = new JLabel("Cache Access Time (in nanoseconds)");
		catLabel.setBounds(510, 260, 420, 28);
		catLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(catLabel);
		
		cat = new JTextField();
		cat.setBounds(510, 290, 420, 28);
		frame.add(cat);

	// Memory access time
		matLabel = new JLabel("Memory Access Time (in nanoseconds)");
		matLabel.setBounds(510, 340, 420, 28);
		matLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		frame.add(matLabel);
		
		mat = new JTextField();
		mat.setBounds(510, 370, 420, 28);
		frame.add(mat);

	// Run button
		runButton = new JButton("Run");
		runButton.setBounds(670, 430, 100, 28);
		frame.add(runButton);
		runButton.setVisible(true);

	// Output screen

	// Output label
		outputLabel = new JLabel("Output");
        outputLabel.setBounds(50, 35, 200, 26);
        outputLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        frame.add(outputLabel);

	// Reset and export buttons
		resetButton = new JButton("Reset");
		resetButton.setBounds(600, 430, 100, 28);
		frame.add(resetButton);

		exportButton = new JButton("Export");
		exportButton.setBounds(720, 430, 100, 28);
		frame.add(exportButton);
		
		
		flowLabel.setVisible(false);
		runButton.setVisible(false);
		outputLabel.setVisible(false);
		resetButton.setVisible(false);
		exportButton.setVisible(false);

        frame.setVisible(true);
	}

	public void outputScreen() {
        flowArea.setEditable(false);

		flowLabel.setVisible(false);
		runButton.setVisible(false);

		outputLabel.setVisible(true);
		resetButton.setVisible(true);
		exportButton.setVisible(true);
	}

	public void inputScreen() {
        flowArea.setEditable(true);

		flowLabel.setVisible(true);
		runButton.setVisible(true);

		outputLabel.setVisible(false);
		resetButton.setVisible(false);
		exportButton.setVisible(false);
	}

	// temp main to see view
	public static void main(String[] args) {
		View view = new View();

		view.inputScreen();
		// view.outputScreen();
	}
}
