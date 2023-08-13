import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class WordCounter extends JFrame 
{
    private JTextArea tArea;
    private JLabel cLabel;

    public WordCounter()
     {
        setTitle("WORD COUNTER");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        tArea = new JTextArea();
        tArea.setLineWrap(true);

        JScrollPane sPane = new JScrollPane(tArea);
        p.add(sPane, BorderLayout.CENTER);

        JButton count_Button = new JButton("Count Words");
        count_Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = tArea.getText();
                int word_Count = countWords(text1);
                cLabel.setText("Total Word Count: " + word_Count);
            }
        });

        JButton file_Button = new JButton("Open File");
        file_Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file_Chooser = new JFileChooser();
                int choice = file_Chooser.showOpenDialog(WordCounter.this);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    try 
                    {
                        String file_Path = file_Chooser.getSelectedFile().getAbsolutePath();
                        String file_Content = readFile(file_Path);
                        tArea.setText(file_Content);
                    } 
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(WordCounter.this,
                                "Error reading this file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cLabel = new JLabel("Word Count: 0");
        cLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        JPanel button_Panel = new JPanel();
        button_Panel.setLayout(new FlowLayout());
        button_Panel.add(count_Button);
        button_Panel.add(file_Button);

        p.add(button_Panel, BorderLayout.SOUTH);
        p.add(cLabel, BorderLayout.NORTH);

        add(p);
        setVisible(true);
    }

    private int countWords(String text1) 
    {
        if (text1.isEmpty())
            return 0;
        String[] words = text1.split("\\W+");
        return words.length;
    }

    private String readFile(String file_Path) throws IOException {
        StringBuilder S_content = new StringBuilder();
        try (BufferedReader re = new BufferedReader(new FileReader(file_Path))) {
            String line;
            while ((line = re.readLine()) != null) {
                S_content.append(line).append("\n");
            }
        }
        return S_content.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCounter();
            }
        });
    }
}