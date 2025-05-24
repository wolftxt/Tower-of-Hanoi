
public class HanoiWindow extends javax.swing.JFrame {

    private int disks = 3;
    private boolean usedSolver = false;

    private void checkWinner() {
        if (hanoiWidget1.getGame().isWinner()) {
            hanoiWidget1.getGame().setPlaying(false);
            if (usedSolver) {
                jLabel1.setText("You used the solver didn't you?");
            } else {
                jLabel1.setText("You won! Click here for a new game!");
            }
        }
    }

    public HanoiWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hanoiWidget1 = new HanoiWidget();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        hanoiSolver1 = new HanoiSolver();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hanoiWidget1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                hanoiWidget1MouseDragged(evt);
            }
        });
        hanoiWidget1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hanoiWidget1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hanoiWidget1MouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Playing");
        jLabel1.setToolTipText("");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(3, 3, null, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        hanoiSolver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hanoiSolver1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(hanoiSolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE))
            .addComponent(hanoiWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hanoiWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hanoiSolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hanoiWidget1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hanoiWidget1MousePressed
        if (evt.getButton() == 1) {
            hanoiWidget1.setPressed(true);
            hanoiWidget1.setMouseCoords(evt.getX(), evt.getY());
            hanoiWidget1.setOldPressedThird(evt.getX());
            this.repaint();
        }
    }//GEN-LAST:event_hanoiWidget1MousePressed

    private void hanoiWidget1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hanoiWidget1MouseReleased
        if (evt.getButton() == 1) {
            hanoiWidget1.setPressed(false);
            this.repaint();
            hanoiWidget1.moveDiskTo();
            checkWinner();
            this.repaint();
        }
    }//GEN-LAST:event_hanoiWidget1MouseReleased

    private void hanoiWidget1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hanoiWidget1MouseDragged
        hanoiWidget1.setMouseCoords(evt.getX(), evt.getY());
        hanoiWidget1.setPressedThird(evt.getX());
        this.repaint();
    }//GEN-LAST:event_hanoiWidget1MouseDragged

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        jLabel1.setText("Playing");
        hanoiWidget1.newGame(disks);
        usedSolver = false;
        this.repaint();
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        this.disks = (int) jSpinner1.getValue();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void hanoiSolver1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hanoiSolver1MouseReleased
        usedSolver = true;
        int[] solution = hanoiSolver1.solve(hanoiWidget1.getGame());
        hanoiWidget1.getGame().moveDiskTo(solution[0], solution[1], solution[2]);
        checkWinner();
        this.repaint();
    }//GEN-LAST:event_hanoiSolver1MouseReleased

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HanoiWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HanoiWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HanoiWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HanoiWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HanoiWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private HanoiSolver hanoiSolver1;
    private HanoiWidget hanoiWidget1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
