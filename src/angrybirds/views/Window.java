/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.debugbox.DebugBox;
import angrybirds.Constants;
import angrybirds.Game;
import angrybirds.Tools;
import angrybirds.debugbox.DebugBoxController;
import angrybirds.debugbox.DebugBoxModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author Pierre
 */
public class Window extends JFrame {


    private Game game;
    private DebugBoxModel debugBoxModel;

    private static int refreshTimes = 0;
    private Timer t = null;
    private DebugBox debugBox;

    public Window(Game game) {
        this.game = game;

        setTitle("Angry Birds");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tools.debug("LANCEMENT");

    }

    public void setRefreshTimes(int refreshTimes_) {
        this.refreshTimes = refreshTimes_;
    }

    /**
     * Refresh game state.
     */
    public void refresh() {
        setVisible(true);

        launchDebugBox();

        t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                if (Game.BLOCK_STATUS == false) {

                    if (Constants.DEBUG_MODE == true && debugBoxModel != null) {
                        debugBoxModel.setRefreshTimes((refreshTimes++));
                    }
                    
                    game.updateElements();
                    repaint();
                }
            }
        }, 0, game.getFPS());

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        game.loop(g);
    }

    private void launchDebugBox() {
        if (Constants.DEBUG_MODE == true && debugBoxModel == null) {

            debugBoxModel = new DebugBoxModel();
            DebugBoxController debugBoxController = new DebugBoxController(debugBoxModel);
            debugBox = new DebugBox(debugBoxModel, debugBoxController, game);
            debugBoxModel.addView(debugBox);
            debugBoxController.setBird(game.getBird());
            game.getBird().getModel().addObserver(debugBox);
            debugBox.setVisible(true);
        }
    }

    /**
     * Refresh scene
     *
     * @param a
     */
    public void refreshScene(Game a) {
        setRefreshTimes(0);
        if (debugBox != null) {
            debugBox.getDebugBoxController().setBird(a.getBird());
        }
    }
    
    
    public static int getRefreshTimes() {
        return refreshTimes;
    }

}
