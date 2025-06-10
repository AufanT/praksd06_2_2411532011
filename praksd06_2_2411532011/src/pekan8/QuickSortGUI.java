package pekan8;

import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class QuickSortGUI extends JFrame {

	// Aufan Taufiqurrahman
	// 2411532011
    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private Stack<int[]> stack = new Stack<>();
    private int i = 0, j;
    private boolean sorting = false;
    private int stepCount = 1;
    private int low, high, pivot;
    private boolean partitioning = false;
    private int pivotIndex;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuickSortGUI frame = new QuickSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public QuickSortGUI() {
        setTitle("Quick Sort Langkah per langkah");
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

        setButton.addActionListener(e -> setArrayFromInput());
        stepButton.addActionListener(e -> performStep());
        resetButton.addActionListener(e -> reset());
    }

    private void setArrayFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        String[] parts = text.split(",");
        array = new int[parts.length];

        try {
            for (int k = 0; k < parts.length; k++) {
                array[k] = Integer.parseInt(parts[k].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka dipisahkan koma!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        labelArray = new JLabel[array.length];
        panelArray.removeAll();
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

        stack.clear();
        if (array.length > 1) {
            stack.push(new int[]{0, array.length - 1});
        }
        sorting = true;
        partitioning = false;
        stepCount = 1;
        stepArea.setText("");
        stepButton.setEnabled(true);

        panelArray.revalidate();
        panelArray.repaint();
    }

    private void performStep() {
        resetHighlights();
        
        if (!partitioning) {
            while (!stack.isEmpty()) {
                int[] range = stack.pop();
                low = range[0];
                high = range[1];
                
                if (low < high) {
                    pivotIndex = high;
                    pivot = array[high];
                    i = low - 1;
                    j = low;
                    partitioning = true;
                    
                    stepArea.append("Langkah " + stepCount++ + ": Mulai partition dari index " 
                        + low + " ke " + high + " dengan pivot " + pivot + " (index " + pivotIndex + ")\n");
                    highlightPivot(pivotIndex);
                    return;
                }
            }
            
            sorting = false;
            stepButton.setEnabled(false);
            stepArea.append("Quick Sort selesai.\n");
            JOptionPane.showMessageDialog(this, "Quick Sort selesai!");
            return;
        }

        if (j < high) {
            highlightCompare(j, pivotIndex);
            
            stepArea.append("Langkah " + stepCount++ + ": Bandingkan " + array[j] + " dengan pivot " + pivot);
            
            if (array[j] < pivot) {
                i++;
                stepArea.append(" -> " + array[j] + " < " + pivot + ", pindahkan ke kiri\n");
                if (i != j) {
                    int valueJ = array[j];
                    int valueI = array[i];
                    swap(i, j);
                    stepArea.append("   Tukar " + valueJ + " (index " + j + ") dengan " 
                        + valueI + " (index " + i + ")\n");
                    updateLabels();
                } else {
                    stepArea.append("   " + array[j] + " sudah di posisi yang benar\n");
                }
            } else {
                stepArea.append(" -> " + array[j] + " >= " + pivot + ", biarkan di kanan\n");
            }
            j++;
            return;
        }

        int finalPivotPos = i + 1;
        if (finalPivotPos != pivotIndex) {
            int pivotValue = array[pivotIndex];
            int valueAtFinalPos = array[finalPivotPos];
            swap(finalPivotPos, pivotIndex);
            stepArea.append("Langkah " + stepCount++ + ": Pindahkan pivot " + pivotValue 
                + " ke posisi " + finalPivotPos + " (tukar dengan " + valueAtFinalPos + ")\n");
            updateLabels();
        } else {
            stepArea.append("Langkah " + stepCount++ + ": Pivot sudah di posisi yang benar\n");
        }

        partitioning = false;

        if (low < finalPivotPos - 1) {
            stack.push(new int[]{low, finalPivotPos - 1});
            stepArea.append("  -> Tambahkan subarray kiri: [" + low + ".." + (finalPivotPos - 1) + "]\n");
        }
        if (finalPivotPos + 1 < high) {
            stack.push(new int[]{finalPivotPos + 1, high});
            stepArea.append("  -> Tambahkan subarray kanan: [" + (finalPivotPos + 1) + ".." + high + "]\n");
        }
        
        stepArea.append("Partition selesai. Pivot " + pivot + " berada di posisi " + finalPivotPos + "\n\n");
    }

    private void highlightPivot(int index) {
        if (index >= 0 && index < labelArray.length) {
            labelArray[index].setBackground(Color.YELLOW);
        }
    }

    private void highlightCompare(int jIndex, int pivotIndex) {
        if (jIndex >= 0 && jIndex < labelArray.length) {
            labelArray[jIndex].setBackground(Color.CYAN);
        }
        if (pivotIndex >= 0 && pivotIndex < labelArray.length) {
            labelArray[pivotIndex].setBackground(Color.YELLOW);
        }
    }

    private void resetHighlights() {
        if (labelArray != null) {
            for (JLabel label : labelArray) {
                label.setBackground(Color.WHITE);
            }
        }
    }

    private void swap(int a, int b) {
        if (a >= 0 && a < array.length && b >= 0 && b < array.length) {
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
    }

    private void updateLabels() {
        if (labelArray != null && array != null) {
            for (int k = 0; k < Math.min(array.length, labelArray.length); k++) {
                labelArray[k].setText(String.valueOf(array[k]));
            }
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        stack.clear();
        sorting = false;
        partitioning = false;
        stepCount = 1;
        array = null;
        labelArray = null;
    }
}