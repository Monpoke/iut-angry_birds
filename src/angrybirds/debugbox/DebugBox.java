/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.debugbox;

import angrybirds.Game;
import angrybirds.structures.Vector2d;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Pierre
 */
public class DebugBox extends javax.swing.JFrame implements Observer {

    private final DebugBoxModel model;
    private final DebugBoxController debugBoxController;
    private final Game game;

    private final DefaultComboBoxModel moves = new DefaultComboBoxModel<>();

    /**
     * Creates new form DebugBox
     *
     * @param model
     * @param debugBoxController
     * @param game
     */
    public DebugBox(DebugBoxModel model, DebugBoxController debugBoxController, Game game) {
        this.model = model;
        this.debugBoxController = debugBoxController;
        this.game = game;

        moves.addElement("linear");
        moves.addElement("parabolic");

        initComponents();
    }

    public DebugBoxController getDebugBoxController() {
        return debugBoxController;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refreshIte = new javax.swing.JLabel();
        resetScene = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        execBird = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        birdX = new javax.swing.JTextField();
        birdY = new javax.swing.JTextField();
        applyPosition = new javax.swing.JButton();
        length = new javax.swing.JTextField();
        width = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        blockGame = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DebugBox");

        refreshIte.setText("Refresh #");

        resetScene.setText("Reset scene");
        resetScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSceneActionPerformed(evt);
            }
        });

        jComboBox1.setModel(moves);

        execBird.setText("exec on bird");

        birdX.setText("coordX");

        birdY.setText("coordY");

        applyPosition.setText("Apply");
        applyPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyPositionActionPerformed(evt);
            }
        });

        length.setText("length");

        width.setText("width");
        width.setToolTipText("");

        blockGame.setText("Block game");
        blockGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshIte)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetScene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addComponent(execBird))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(applyPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(length, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(birdX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(birdY, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                .addComponent(width)))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(blockGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshIte)
                .addGap(18, 18, 18)
                .addComponent(resetScene)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(execBird))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birdX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birdY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(applyPosition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blockGame)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSceneActionPerformed
        game.resetScene();
    }//GEN-LAST:event_resetSceneActionPerformed

    private void applyPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyPositionActionPerformed
        // have to send to controller that have been updated
        debugBoxController.updatePosition(new Vector2d(
                Double.parseDouble(birdX.getText()),
                Double.parseDouble(birdY.getText()),
                Double.parseDouble(length.getText()),
                Double.parseDouble(width.getText()))
        );
    }//GEN-LAST:event_applyPositionActionPerformed

    private void blockGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockGameActionPerformed
        Game.BLOCK_STATUS = !Game.BLOCK_STATUS;
    }//GEN-LAST:event_blockGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyPosition;
    private javax.swing.JTextField birdX;
    private javax.swing.JTextField birdY;
    private javax.swing.JToggleButton blockGame;
    private javax.swing.JButton execBird;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField length;
    private javax.swing.JLabel refreshIte;
    private javax.swing.JButton resetScene;
    private javax.swing.JTextField width;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        DebugBoxModel m = ((DebugBoxModel) o);
        refreshIte.setText("Refresh #" + m.getRefresh());
        
        // Recup bird
        Vector2d position = game.getBird().getModel().getPosition();
        birdX.setText(position.getX()+"");
        birdY.setText(position.getY()+"");
        length.setText(position.getLength()+"");
        width.setText(position.getWidth()+"");
    }
}
