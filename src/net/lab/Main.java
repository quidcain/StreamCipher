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
    JLabel labelLinkedFile;
    Main() {
        class SingleLfsrPanel extends JPanel {
            SingleLfsrPanel() {
                super(new GridBagLayout());
                //setPreferredSize(new Dimension(320,600));
                JLabel labelInitialKeyState = new JLabel("Начальное состояние ключа");
                JTextField textFieldInitialKeyState = new JTextField();
                textFieldInitialKeyState.setDocument(new TextFieldFormat(24));
                JLabel labelTaps = new JLabel("Параметры");
                JTextField textFieldTaps = new JTextField();
                textFieldTaps.setDocument(new TextFieldFormat(24));
                labelLinkedFile = new JLabel("Файл не привязан");
                JButton buttonEncrypt = new JButton("Шифернуть");
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.CENTER;
                add(labelInitialKeyState,c);
                c.gridx = 0;
                c.gridy = 1;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                add(textFieldInitialKeyState,c);
                c.gridx = 0;
                c.gridy = 2;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                add(labelTaps,c);
                c.gridx = 0;
                c.gridy = 3;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                add(textFieldTaps,c);
                c.gridx = 0;
                c.gridy = 4;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                add(labelLinkedFile,c);
                c.gridx = 0;
                c.gridy = 5;
                c.anchor = GridBagConstraints.CENTER;
                add(buttonEncrypt, c);
                buttonEncrypt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        byte[] bytesFromFile = null;
                        TreatedFile treatedFile = new TreatedFile();
                        if (!treatedFile.read(fileName)) {
                            JOptionPane.showMessageDialog(frame, "Не привязан файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        treatedFile.encrypt(new KeyHolder().getBytes(treatedFile.getLength()));
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
                menuItemOpen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = new JFileChooser("/home/stoat/programming/IdeaProjects/StreamCipherBegin");
                        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            fileName = file.getName();
                            labelLinkedFile.setText("Файл: " + file.getName());
                        }
                    }
                });
                menuItemBinaryDisplay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        byte[] bytesFromFile = null;
                        try(BufferedInputStream bfrInSt = new BufferedInputStream(new FileInputStream(fileName))){
                            bytesFromFile = new byte[bfrInSt.available()];
                            bfrInSt.read(bytesFromFile);
                        } catch(NullPointerException e1){
                            System.out.println("херня");
                            return;
                        }catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try(BufferedOutputStream bfrOutSt = new BufferedOutputStream(new FileOutputStream(fileName + "Binary"))){
                            for (int i = 0; i < bytesFromFile.length; ++i) {
                                for (int j = 0; j < 8; ++j) {;
                                    bfrOutSt.write((bytesFromFile[i] >> j & 1) == 1 ? '1' : '0');
                                }
                            }
                        }catch (FileNotFoundException e1) {
                            System.out.println("huevo" + e1.getMessage());
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        }

        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new SingleLfsrPanel(), "singleLfsr");
        //mainPanel.add(new AfterAuthPanel(), "Smth another");*/
        frame = new JFrame("Потоковое шифрование");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(290, 330);
        frame.setMinimumSize(new Dimension(290, 330));
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
