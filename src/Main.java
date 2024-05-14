import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JPanel {
    private JButton button;
    private JButton fileChooserButtonStage2;
    private JButton fileChooserButtonStage1; // Button for choosing Stage1 payload
    private String stage1_path; // Variable to store the Stage1 file path
    private String stage2_path; // Variable to store the Stage2 file path
    private JComboBox<String> firmwareComboBox;
    private String[] firmwares = {"900", "903", "904", "950", "951", "960", "1000", "1001", "1050", "1070", "1071", "1100"};

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 400, 600);
        frame.setTitle("PS4 PPPwn GUI");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main ps4_exploit_app = new Main();
        frame.add(ps4_exploit_app);
        frame.setVisible(true);
    }

    public Main() {
        setLayout(null);
        int centerX = 100; // Center position for all elements (200 width centered in 400 width frame)

        firmwareComboBox = new JComboBox<>(firmwares);
        firmwareComboBox.setBounds(centerX, 150, 200, 30);
        add(firmwareComboBox);

        fileChooserButtonStage1 = new JButton("Choose Stage1 Path");
        fileChooserButtonStage1.setBounds(centerX, 200, 200, 30);
        add(fileChooserButtonStage1);
        fileChooserButtonStage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("BIN Files", "bin");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    stage1_path = fileChooser.getSelectedFile().getPath();
                    System.out.println("Stage1 File chosen: " + stage1_path);
                }
            }
        });

        fileChooserButtonStage2 = new JButton("Choose Stage2 Path");
        fileChooserButtonStage2.setBounds(centerX, 250, 200, 30);
        add(fileChooserButtonStage2);
        fileChooserButtonStage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("BIN Files", "bin");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    stage2_path = fileChooser.getSelectedFile().getPath();
                    System.out.println("Stage2 File chosen: " + stage2_path);
                }
            }
        });

        button = new JButton("Submit");
        button.setBounds(centerX, 300, 200, 30);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firmware = (String) firmwareComboBox.getSelectedItem();
                System.out.println("Selected firmware: " + firmware);
                System.out.println("Stage1 payload path: " + stage1_path);
                System.out.println("Stage2 payload path: " + stage2_path);
                String hackor = "cmd.exe /c start .\\pppwn.exe --interface Ethernet --fw \"" + firmware + "\" --stage1 \"" + stage1_path + "\" --stage2 \"" + stage2_path + "\" --auto-retryK";

                try {
                    Runtime.getRuntime().exec(hackor);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
