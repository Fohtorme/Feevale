/*
 * Desenvolvido por Whale(1)
 */
package shedulingpid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.table.TableModel;

/**
 *
 * @author jonas
 */
public class GraphicScheduler extends javax.swing.JPanel implements ActionListener {

    private final Timer timer;
    private int time;
    private final List<Process> processes;
    private int runningProcess;
    private int runningProcessTime;

    /**
     * Creates new form GraphicScheduler
     */
    public GraphicScheduler() {
        initComponents();
        this.timer = new Timer(1000, this);
        this.timer.start();
        this.time = 0;
        this.processes = new ArrayList<>();
        this.runningProcess = -1;
        this.runningProcessTime = 0;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == this.timer) {
            executeProcess();
        }

    }

    private void executeProcess() {
        this.time++;
        currentTimeField.setText(this.time + "");

        switch (shedulerField.getSelectedItem().toString()) {
            case "Not Preemptive":
                notPreemptive();
                break;
            case "Preemptive for Priority":
                preemptiveForPriority();
                break;
            case "Preemptive for Priority + Time":
                preemptiveForPriorityAndTime();
                break;
        }

        updateTable();
    }

    private void notPreemptive() {
        // Run the first process awaiting
        for (int i = 0; i < processes.size(); i++) {
            if (!processes.get(i).isFinished()) {
                runProcess(i, 0);
                break;
            }
        }
    }

    private void preemptiveForPriority() {
        // Find the high priority element
        int priority = -1;
        int elementIndex = -1;
        for (int i = 0; i < processes.size(); i++) {
            // Ignore finished processes
            if (processes.get(i).isFinished()) {
                continue;
            }
            // If find a greater prioriry element
            if (processes.get(i).getPriority() > priority) {
                priority = processes.get(i).getPriority();
                elementIndex = i;
            }
        }
        // If element was not finded
        if (elementIndex == -1) {
            return;
        }
        // Run the high priority element
        runProcess(elementIndex, 0);
    }

    private void preemptiveForPriorityAndTime() {
        // Find the high priority element
        int priority = -1;
        int elementIndex = -1;
        for (int i = 0; i < processes.size(); i++) {
            // Ignore finished processes
            if (processes.get(i).isFinished()) {
                continue;
            }
            // If find a greater prioriry element
            if (processes.get(i).getPriority() > priority) {
                priority = processes.get(i).getPriority();
                elementIndex = i;
            }
            // If it's not the same priority
            if (processes.get(i).getPriority() != priority) {
                continue;
            }
            // If the process time is not over
            if (runningProcessTime != 0) {
                // If it's the process running
                if (processes.get(i).getId() == this.runningProcess) {
                    elementIndex = i;
                }
                continue;
            }
            // If it is not the next process
            if (!(processes.get(i).getId() > this.runningProcess)) {
                continue;
            }
            // If already had find a next element
            if (processes.get(elementIndex).getId() > this.runningProcess) {
                // If the element finded is lesser than the current element
                if (processes.get(i).getId() < processes.get(elementIndex).getId()) {
                    elementIndex = i;
                }
            } else {
                elementIndex = i;
            }
        }
        // If element was not finded
        if (elementIndex == -1) {
            return;
        }
        // If the element has been swapped
        if (processes.get(elementIndex).getId() != this.runningProcess) {
            this.runningProcessTime = Integer.parseInt(quantumField.getSelectedItem().toString()) - 1;
        } else {
            this.runningProcessTime--;
        }
        // Run the high priority element
        runProcess(elementIndex, this.runningProcessTime);
    }

    private void runProcess(int i, int q) {
        processes.get(i).runProcess(time);
        this.runningProcess = processes.get(i).getId();
        quantumTimeField.setText((q + 1) + "");
    }

    private void updateTable() {
        // Sheduler status
        String status = "IDLE";
        TableModel tm = processesTable.getModel();
        for (int i = 0; i < processes.size(); i++) {
            tm.setValueAt(processes.get(i).getId(), i, 0);
            tm.setValueAt(processes.get(i).getPriority(), i, 1);
            tm.setValueAt(processes.get(i).getTotalTime(), i, 2);
            tm.setValueAt(processes.get(i).getRemainingTime(), i, 3);
            tm.setValueAt(processes.get(i).getStartTime(), i, 4);
            tm.setValueAt(processes.get(i).getEndTime(), i, 5);
            tm.setValueAt(processes.get(i).getLeadTime(time), i, 6);
            tm.setValueAt(processes.get(i).getStatus(time), i, 7);
            if (processes.get(i).getStatus(time).equals("Running")) {
                status = "RUNNING";
            }
        }
        shedulerStatusField.setText(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        processesTable = new javax.swing.JTable();
        addProcessButton = new javax.swing.JButton();
        currentTimeField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        processPriorityField = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        processTimeField = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        quantumField = new javax.swing.JComboBox<>();
        shedulerField = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        quantumTimeField = new javax.swing.JTextField();
        shedulerStatusField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        processesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Priority", "Total time", "Remaining time", "Start", "End", "Lead time", "Status"
            }
        ));
        jScrollPane1.setViewportView(processesTable);

        addProcessButton.setText("Add process");
        addProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProcessButtonActionPerformed(evt);
            }
        });

        currentTimeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        currentTimeField.setEnabled(false);

        jLabel1.setText("Current time");

        processPriorityField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabel2.setText("Process priority");

        jLabel3.setText("Process time");

        processTimeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        jLabel4.setText("Quantum");

        quantumField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        shedulerField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Preemptive", "Preemptive for Priority", "Preemptive for Priority + Time" }));

        jLabel5.setText("Sheduler");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Time quantum");

        quantumTimeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantumTimeField.setEnabled(false);

        shedulerStatusField.setEnabled(false);

        jLabel7.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentTimeField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantumTimeField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantumField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(processTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(processPriorityField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shedulerField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shedulerStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shedulerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(shedulerStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(processTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(processPriorityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantumTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProcessButtonActionPerformed
        processes.add(new Process(
                Integer.parseInt(processPriorityField.getSelectedItem().toString()),
                Integer.parseInt(processTimeField.getSelectedItem().toString()),
                this.time
        ));
        updateTable();
    }//GEN-LAST:event_addProcessButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        int processesSize = processes.size();
        this.processes.clear();
        TableModel tm = processesTable.getModel();
        for (int i = 0; i < processesSize; i++) {
            for (int j = 0; j < tm.getColumnCount(); j++) {
                tm.setValueAt(null, i, j);
            }
        }
    }//GEN-LAST:event_clearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProcessButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField currentTimeField;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> processPriorityField;
    private javax.swing.JComboBox<String> processTimeField;
    private javax.swing.JTable processesTable;
    private javax.swing.JComboBox<String> quantumField;
    private javax.swing.JTextField quantumTimeField;
    private javax.swing.JComboBox<String> shedulerField;
    private javax.swing.JTextField shedulerStatusField;
    // End of variables declaration//GEN-END:variables
}
