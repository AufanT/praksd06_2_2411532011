package pekan8;

import java.awt.*;
import javax.swing.*;

public class MergeSortGUI extends JFrame {

    // Aufan_Taufiqurrahman
    // 2411532011
    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private boolean isMerging = false;
    private int[] temp;
    private int left, mid, right;
    private boolean copying = false;
    private int k = 0;
    private java.util.Queue<int[]> mergeQueue = new java.util.LinkedList<>();
    private int i = 0, j;
    private int stepCount = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MergeSortGUI frame = new MergeSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MergeSortGUI() {
        setTitle("Merge Sort Langkah per langkah");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set array");
        textPanel.add(new JLabel("Masukkan array (pisahkan dengan koma):"));
        textPanel.add(inputField);
        textPanel.add(setButton);

        panelArray = new JPanel();
        panelArray.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

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
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] parts = text.split(",");
        array = new int[parts.length];

        try {
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        labelArray = new JLabel[array.length];
        panelArray.removeAll();
        for (int i = 0; i < array.length; i++) {
            labelArray[i] = new JLabel(String.valueOf(array[i]));
            labelArray[i].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[i].setOpaque(true);
            labelArray[i].setBackground(Color.WHITE);
            labelArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[i].setPreferredSize(new Dimension(50, 50));
            labelArray[i].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[i]);
        }

        mergeQueue.clear();
        generateMergeSteps(0, array.length - 1); // This method is now defined
        stepButton.setEnabled(true);
        stepArea.setText("Array telah di-set. Klik 'Langkah selanjutnya' untuk memulai.\n");
        stepCount = 1;
        isMerging = false;
        panelArray.revalidate();
        panelArray.repaint();
    }

    private void generateMergeSteps(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            generateMergeSteps(l, m);
            generateMergeSteps(m + 1, r);
            mergeQueue.add(new int[]{l, m, r});
        }
    }

    private void performStep() {
        resetHighlights();

        if (!isMerging && !mergeQueue.isEmpty()) {
            int[] range = mergeQueue.poll();
            left = range[0];
            mid = range[1];
            right = range[2];
            temp = new int[right - left + 1];
            i = left;
            j = mid + 1;
            k = 0;
            copying = false;
            isMerging = true;
            stepArea.append("Langkah " + stepCount++ + ": Mulai merge antara indeks " + left + " dan " + right + "\n");
            for(int idx = left; idx <= right; idx++) {
                labelArray[idx].setBackground(Color.YELLOW);
            }
            return;
        }

        if (isMerging && !copying) {
            if(i <= mid) labelArray[i].setBackground(Color.CYAN);
            if(j <= right) labelArray[j].setBackground(Color.CYAN);

            if (i <= mid && (j > right || array[i] <= array[j])) {
                stepArea.append("   -> Bandingkan " + (j > right ? "" : array[i] + " <= " + array[j]) + ". Salin " + array[i] + " ke temp.\n");
                temp[k++] = array[i++];
            } else if (j <= right) {
                stepArea.append("   -> Bandingkan " + array[i] + " > " + array[j] + ". Salin " + array[j] + " ke temp.\n");
                temp[k++] = array[j++];
            } else {
                 copying = true;
                 k = 0; 
                 stepArea.append("Langkah " + stepCount++ + ": Selesai membandingkan. Mulai salin dari temp ke array utama.\n");
            }
            return;
        }

        if (copying && k < temp.length) {
            array[left + k] = temp[k];
            labelArray[left + k].setText(String.valueOf(temp[k]));
            labelArray[left + k].setBackground(Color.GREEN); 
            stepArea.append("   -> Salin " + temp[k] + " ke posisi " + (left + k) + "\n");
            k++;
            return;
        }

        if (copying && k == temp.length) {
            isMerging = false;
            copying = false;
            stepArea.append("   -> Merge untuk range ini selesai.\n");
        }

        if (mergeQueue.isEmpty() && !isMerging) {
            stepArea.append("SELESAI. Array telah terurut.\n");
            stepButton.setEnabled(false);
            resetHighlights();
            for(JLabel label : labelArray) {
                label.setBackground(Color.GREEN);
            }
            JOptionPane.showMessageDialog(this, "Merge Sort selesai!");
        } else if (!isMerging) {
            performStep();
        }
    }

    private void resetHighlights() {
        if (labelArray == null) return;
        for (JLabel label : labelArray) {
            if (label != null) {
                label.setBackground(Color.WHITE);
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
        mergeQueue.clear();
        isMerging = false;
        stepCount = 1;
        array = null;
        labelArray = null;
    }
}
