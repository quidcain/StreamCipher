package net.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by stoat on 11/21/16.
 */
public class Main {
    private JFrame frame;
    private JPanel mainPanel;
    private String fileName;
    private JLabel labelLinkedFileFirstPanel;
    private JLabel labelLinkedFileSecondPanel;
    private JLabel labelLinkedFileThirdPanel;
    Main() {
        class SingleLfsrPanel extends JPanel {
            SingleLfsrPanel() {
                super(new GridBagLayout());
                JLabel labelInitialRegisterState = new JLabel("Начальное состояние регистра");
                JTextField textFieldInitialRegisterState = new JTextField();
                textFieldInitialRegisterState.setDocument(new TextFieldFormat(24));
                labelLinkedFileFirstPanel = new JLabel("Файл не привязан");
                JButton buttonEncrypt = new JButton("Шифернуть");
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.CENTER;
                add(labelInitialRegisterState,c);
                c.gridx = 0;
                c.gridy = 1;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                add(textFieldInitialRegisterState,c);
                c.gridx = 0;
                c.gridy = 2;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                add(labelLinkedFileFirstPanel,c);
                c.gridx = 0;
                c.gridy = 3;
                c.anchor = GridBagConstraints.CENTER;
                add(buttonEncrypt, c);
                buttonEncrypt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        TreatedFile treatedFile = new TreatedFile();
                        if (!treatedFile.read(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не привязан файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String stringKeyState = textFieldInitialRegisterState.getText();
                        if (stringKeyState.length() != 24) {
                            JOptionPane.showMessageDialog(frame, "Длина регистра должна быть равна 24", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        byte[] bitsKeyState = new byte[stringKeyState.length()];
                        for (int i = 0; i < bitsKeyState.length; ++i)
                            bitsKeyState[i] = (byte)Character.getNumericValue(stringKeyState.charAt(i));
                        treatedFile.encrypt(new SingleLfsrKeyHolder(bitsKeyState, treatedFile.getLength()));
                        if(!treatedFile.write(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не удалось записать файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                });
            }
        }
        class TripleLfsrPanel extends JPanel {
            TripleLfsrPanel() {
                super(new GridBagLayout());

                JLabel labelInitialRegisterState1 = new JLabel("Начальное состояние регистра1");
                JTextField textFieldInitialRegisterState1 = new JTextField();
                textFieldInitialRegisterState1.setDocument(new TextFieldFormat(24));
                textFieldInitialRegisterState1.setPreferredSize(new Dimension(280, 20));

                JLabel labelInitialRegisterState2 = new JLabel("Начальное состояние регистра2");
                JTextField textFieldInitialRegisterState2 = new JTextField();
                textFieldInitialRegisterState2.setDocument(new TextFieldFormat(32));

                JLabel labelInitialRegisterState3 = new JLabel("Начальное состояние регистра3");
                JTextField textFieldInitialRegisterState3 = new JTextField();
                textFieldInitialRegisterState3.setDocument(new TextFieldFormat(40));
                textFieldInitialRegisterState3.setPreferredSize(new Dimension(330, 20));

                labelLinkedFileSecondPanel = new JLabel("Файл не привязан");
                JButton buttonEncrypt = new JButton("Шифернуть");

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.CENTER;
                add(labelInitialRegisterState1,c);
                c.gridy = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(0, 0, 10, 0);
                add(textFieldInitialRegisterState1,c);

                c.gridy = 2;
                c.fill = GridBagConstraints.NONE;
                c.insets = new Insets(0, 0, 0, 0);
                add(labelInitialRegisterState2,c);
                c.gridy = 3;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(0, 0, 10, 0);
                add(textFieldInitialRegisterState2,c);

                c.gridy = 4;
                c.fill = GridBagConstraints.NONE;
                c.insets = new Insets(0, 0, 0, 0);
                add(labelInitialRegisterState3,c);
                c.gridy = 5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(0, 0, 10, 0);
                add(textFieldInitialRegisterState3,c);
                c.gridy = 6;
                c.fill = GridBagConstraints.NONE;
                c.insets = new Insets(0, 0, 0, 0);
                add(labelLinkedFileSecondPanel,c);
                c.gridy = 7;
                add(buttonEncrypt, c);
                buttonEncrypt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        TreatedFile treatedFile = new TreatedFile();
                        if (!treatedFile.read(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не привязан файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String stringRegisterState1 = textFieldInitialRegisterState1.getText();
                        if (stringRegisterState1.length() != 24 ) {
                            JOptionPane.showMessageDialog(frame, "Длина регистра 1 должна быть равна 24", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        byte[] bitsKeyState1 = new byte[stringRegisterState1.length()];
                        for (int i = 0; i < bitsKeyState1.length; ++i)
                            bitsKeyState1[i] = (byte)Character.getNumericValue(stringRegisterState1.charAt(i));

                        String stringRegisterState2 = textFieldInitialRegisterState2.getText();
                        if (stringRegisterState2.length() != 32) {
                            JOptionPane.showMessageDialog(frame, "Длина регистра 2 должна быть равна 32", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        byte[] bitsKeyState2 = new byte[stringRegisterState2.length()];
                        for (int i = 0; i < bitsKeyState2.length; ++i)
                            bitsKeyState2[i] = (byte)Character.getNumericValue(stringRegisterState2.charAt(i));

                        String stringRegisterState3 = textFieldInitialRegisterState3.getText();
                        if (stringRegisterState3.length() != 40) {
                            JOptionPane.showMessageDialog(frame, "Длина регистра 3 должна быть равна 40", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        byte[] bitsKeyState3 = new byte[stringRegisterState3.length()];
                        for (int i = 0; i < bitsKeyState3.length; ++i)
                            bitsKeyState3[i] = (byte)Character.getNumericValue(stringRegisterState3.charAt(i));
                        treatedFile.encrypt(new TripleLfsrKeyHolder(bitsKeyState1, bitsKeyState2, bitsKeyState3, treatedFile.getLength()));
                        if(!treatedFile.write(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не удалось записать файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                });
            }
        }
        class RcfourPanel extends JPanel {
            RcfourPanel() {
                super(new GridBagLayout());
                JLabel labelKey = new JLabel("Ключ");
                JTextField textFieldKey = new JTextField();
                labelLinkedFileThirdPanel = new JLabel("Файл не привязан");
                JButton buttonEncrypt = new JButton("Шифернуть");

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.CENTER;
                add(labelKey,c);
                c.gridy = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                add(textFieldKey,c);
                c.gridy = 2;
                c.fill = GridBagConstraints.NONE;
                add(labelLinkedFileSecondPanel,c);
                c.gridy = 3;
                add(buttonEncrypt,c);

                buttonEncrypt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //[12][0-9][0-9]
                        /*Pattern pattern = Pattern.compile("([12][0-9][0-9] |[1-9][0-9] |[0-9] )*[12][0-9][0-9]|[1-9][0-9]|[0-9]");
                        Matcher matcher = pattern.matcher(textFieldKey.getText());
                        if (!matcher.matches())
                            JOptionPane.showMessageDialog(frame, "Неподходящий формат", "Ошибка", JOptionPane.ERROR_MESSAGE);*/
                        TreatedFile treatedFile = new TreatedFile();
                        if (!treatedFile.read(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не привязан файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String stringKey = textFieldKey.getText();
                        String[] items = stringKey.split(" ");
                        int[] results = new int[items.length];

                        for (int i = 0; i < items.length; i++) {
                            try {
                                results[i] = Integer.parseInt(items[i]);
                                if (results[i] < 0 || results[i] > 255)
                                    JOptionPane.showMessageDialog(frame, "Числа должны быть в пределе от 0 до 255", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(frame, "Неподходящий формат", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        treatedFile.encrypt(new RcfourKeyHolder(results, treatedFile.getLength()));
                        if(!treatedFile.write(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не удалось записать файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }
                });
            }
        }
        class MenuBar extends JMenuBar {
            MenuBar() {
                JMenu menuFile = new JMenu("Файл");
                JMenuItem menuItemOpen = new JMenuItem("Открыть...");
                JMenuItem menuItemBinaryDisplay = new JMenuItem("Вывести в двоичном виде");
                menuFile.add(menuItemOpen);
                menuFile.add(menuItemBinaryDisplay);
                add(menuFile);

                JMenu menuMode = new JMenu("Режим");
                JMenuItem menuItemSingle = new JMenuItem("Один регистр");
                JMenuItem menuItemTriple = new JMenuItem("Три регистра");
                JMenuItem menuItemRcfour = new JMenuItem("Rc4");
                menuItemSingle.setActionCommand("singleLfsr");
                menuItemTriple.setActionCommand("tripleLfsr");
                menuItemRcfour.setActionCommand("rc4");
                menuMode.add(menuItemSingle);
                menuMode.add(menuItemTriple);
                menuMode.add(menuItemRcfour);
                add(menuMode);

                menuItemOpen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userDirLocation = System.getProperty("user.dir");
                        File userDir = new File(userDirLocation);
                        JFileChooser fc = new JFileChooser(userDir);
                        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            fileName = file.getName();
                            labelLinkedFileFirstPanel.setText("Файл: " + file.getName());
                            labelLinkedFileSecondPanel.setText("Файл: " + file.getName());
                            labelLinkedFileThirdPanel.setText("Файл: " + file.getName());
                        }
                    }
                });
                menuItemBinaryDisplay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        byte[] bytesFromFile = null;
                        try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName))){
                            bytesFromFile = new byte[stream.available()];
                            stream.read(bytesFromFile);
                        } catch(NullPointerException e1){
                            System.out.println("херня");
                            return;
                        }catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName + "Binary"))){
                            for (int i = 0; i < bytesFromFile.length; ++i) {
                                for (int j = 7; j >= 0; --j) {
                                    stream.write((bytesFromFile[i] >> j & 1) == 1 ? '1' : '0');
                                }
                            }
                        }catch (FileNotFoundException e1) {
                            System.out.println(e1.getMessage());
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                ActionListener actionChangeMode = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cl = (CardLayout)(mainPanel.getLayout());
                        cl.show(mainPanel, e.getActionCommand());
                        if (e.getActionCommand().equals("singleLfsr"))
                            frame.setMinimumSize(new Dimension(260, 160));
                        else if (e.getActionCommand().equals("tripleLfsr"))
                            frame.setMinimumSize(new Dimension(340, 320));
                    }
                };
                menuItemSingle.addActionListener(actionChangeMode);
                menuItemTriple.addActionListener(actionChangeMode);
                menuItemRcfour.addActionListener(actionChangeMode);

            }
        }

        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new SingleLfsrPanel(), "singleLfsr");
        mainPanel.add(new TripleLfsrPanel(), "tripleLfsr");
        mainPanel.add(new RcfourPanel(), "rc4");
        frame = new JFrame("Потоковое шифрование");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(260, 160));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setJMenuBar(new MenuBar());
        frame.add(mainPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Main();
            }
        });
    }
}
