package com.Simudyne;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Created by selwynstephen on 13/04/16.
 */
public class TestFrame extends JFrame implements ActionListener{

    Integer YEARS_TO_RUN = 15;
    String[] columnNames = {"Year", "Breed C count", "Breed NC count", "Breed C Lost", "Breed NC Gained", "Breed Gained"};
    String[][] dataValues = new String [YEARS_TO_RUN][6];
    JTable resultsTable = new JTable(dataValues, columnNames);

    public TestFrame() {
        super("Agent Breeds");
        setLookAndFeel();
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JLabel pageLabel = new JLabel("Brand Factor", JLabel.RIGHT);
        final JTextField brandFactorInput = new JTextField(20);
        brandFactorInput.setText("0.5");
        add(pageLabel);
        add(brandFactorInput);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Ok")) {
                    panel.remove(resultsTable);
                    repaint();
                    try {
                        List<Agent> agentsFromFile = AgentUtils.getAgentsFromFile("AgentTest.csv");
                        List<AgentResult> agentResults = AgentUtils.processAgents(agentsFromFile, YEARS_TO_RUN, Float.valueOf(brandFactorInput.getText()));
                        Integer index = 0;
                        String [][] newData = new String[YEARS_TO_RUN][agentResults.size()];
                        for (AgentResult agentResult : agentResults) {
                            String [] data = {String.valueOf(agentResult.getYear())
                                            , String.valueOf(agentResult.getBreedCCount())
                                            , String.valueOf(agentResult.getBreedNCCount())
                                            , String.valueOf(agentResult.getBreedCLostCount())
                                            , String.valueOf(agentResult.getBreedCGainedCount())
                                            , String.valueOf(agentResult.getBreedRegained())
                                            };
                            newData[index] = data;;
                            index++;
                        }
                        TableModel tm = new DefaultTableModel(newData, columnNames);
                        resultsTable.setModel(tm);
                        panel.add(resultsTable);
                        panel.repaint();
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }

            }
        });
        add(button);

        add(panel, BorderLayout.PAGE_END);
        panel.add(resultsTable);
        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // ignore error
        }
    }

    public static void main(String[] arguments) {
        TestFrame frame = new TestFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
