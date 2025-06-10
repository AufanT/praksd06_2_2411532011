package pekan8;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class BubbleSortGUI extends JFrame {

	// Aufan_Taufiqurrahman
    // 2411532011
	private static final long serialVersionUID = 1L;
	private int[] array;
	private JLabel[] labelArray;
	private JButton stepButton, resetButton, setButton;
	private JTextField inputField;
	private JPanel panelArray;
	private JTextArea stepArea;

	private int i = 0, j;
	private boolean sorting = false;
	private int stepCount = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BubbleSortGUI frame = new BubbleSortGUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BubbleSortGUI() {
		setTitle("Bubble Sort Langkah per langkah");
		setSize(750, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel textPanel = new JPanel(new FlowLayout());
		inputField = new JTextField(30);
		setButton = new JButton("Set array");
		textPanel.add(new JLabel("text array (pisahkan dengan koma):"));
		textPanel.add(inputField);
		textPanel.add(setButton);

		panelArray = new JPanel();
		panelArray.setLayout(new FlowLayout());

		JPanel controlPanel = new JPanel();
		stepButton = new JButton("Langkah selanjutnya");
		resetButton = new JButton("Reset");
		stepButton.setEnabled(false);
		controlPanel.add(stepButton);
		controlPanel.add(resetButton);

		stepArea = new JTextArea(8, 60);
		stepArea.setEditable(false);
		stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(stepArea);

		add(textPanel, BorderLayout.NORTH);
		add(panelArray, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.EAST);

		setButton.addActionListener(e -> setArrayFromText());
		stepButton.addActionListener(e -> performStep());
		resetButton.addActionListener(e -> reset());
		
	}

	private void setArrayFromText() {
		String text = inputField.getText().trim();
		if (text.isEmpty()) return;
		String[] parts = text.split(",");
		array = new int[parts.length];

		try {
			for (int k = 0; k < parts.length; k++) {
				array[k] = Integer.parseInt(parts[k].trim());
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Input tidak valid. Pastikan hanya angka yang dipisahkan dengan koma.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		i = 0;
		j = 0;
		stepCount = 1;
		sorting = true;
		stepButton.setEnabled(true);
		stepArea.setText("");
		labelArray = new JLabel[parts.length];
		panelArray.removeAll();
		resetButton.setEnabled(true);

		for (int k = 0; k < array.length; k++) {
			labelArray[k] = new JLabel(String.valueOf(array[k]));
			labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[k].setOpaque(true);
            labelArray[k].setBackground(Color.WHITE);
			labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			labelArray[k].setPreferredSize(new Dimension(50, 50));
			labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
			panelArray.add(labelArray[k]);
		}

		panelArray.revalidate();
		panelArray.repaint();
	}

	private void updateLabels() {
		for (int k = 0; k < array.length; k++) {
			labelArray[k].setText(String.valueOf(array[k]));
		}
	}

	private void resetHighlights() {
		for (JLabel label : labelArray) {
			label.setBackground(Color.WHITE);
		}
	}

	private void reset() {
		inputField.setText("");
		panelArray.removeAll();
		panelArray.revalidate();
		panelArray.repaint();
		stepArea.setText("");
		stepButton.setEnabled(false);
		sorting = false;
		i = 1;
		j = 0;
		stepCount = 1;
	}

	private String arrayToString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < array.length; k++) {
			sb.append(array[k]);
			if (k < array.length - 1) sb.append(", ");
		}
		return sb.toString();
	}	

	private void performStep() {
	    if (!sorting || i >= array.length) {
	        sorting = false;
	        stepButton.setEnabled(false);
	        resetHighlights(); // Tambahkan ini
	        JOptionPane.showMessageDialog(this, "Sorting selesai!");
	        return;
	    }

	    resetHighlights();
	    StringBuilder stepLog = new StringBuilder();
	    labelArray[j].setBackground(Color.CYAN);
	    labelArray[j + 1].setBackground(Color.CYAN);

	    if (array[j] > array[j + 1]) {
	        int temp = array[j];
	        array[j] = array[j + 1];
	        array[j + 1] = temp;
	        labelArray[j].setBackground(Color.RED);
	        labelArray[j + 1].setBackground(Color.RED);
	        stepLog.append("Langkah ").append(stepCount).append(": Menukar elemen ke-")
	               .append(j + 1).append(" dan ke-").append(j + 2).append("\n")
	               .append("Hasil: ").append(Arrays.toString(array)).append("\n\n");
	    } else {
	        stepLog.append("Langkah ").append(stepCount).append(": Tidak ada pertukaran antara ke-")
	               .append(j + 1).append(" dan ke-").append(j + 2).append("\n")
	               .append("Hasil: ").append(Arrays.toString(array)).append("\n\n");
	    }

	    stepLog.append("Array saat ini: ").append(arrayToString()).append("\n\n");
	    stepArea.append(stepLog.toString());
	    updateLabels();

	    j++;
	    if (j >= array.length - i - 1) {
	        j = 0;
	        i++;
	    }
	    stepCount++;

	    if (i >= array.length) {
	        sorting = false;
	        stepButton.setEnabled(false);
	        resetHighlights(); // Tambahkan ini
	        JOptionPane.showMessageDialog(this, "Sorting selesai!");
	    }
	}

}