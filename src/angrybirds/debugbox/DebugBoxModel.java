package angrybirds.debugbox;

import java.util.Observable;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
/**
 *
 * @author Pierre
 */
public class DebugBoxModel extends Observable {

    private DebugBox view;
    private int refresh = 0;

    public void addView(DebugBox debugBox) {
        this.view = debugBox;
        addObserver(view);
    }

    /**
     *
     * @param t;
     */
    public void setRefreshTimes(int t) {
        this.refresh = t;
        setChanged();
        notifyObservers();
    }

    public int getRefresh() {
        return refresh;
    }
    
    
}
