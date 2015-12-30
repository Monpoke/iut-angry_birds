package angrybirds.builders;

import angrybirds.Constants;
import angrybirds.controllers.ObstacleController;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.CircleObstacle;
import angrybirds.views.GameObjectView;
import angrybirds.views.RectangleObstacle;
import angrybirds.views.ShapeObstacle;

import java.awt.*;

/**
 * Created by Pierre on 27/12/2015.
 */
public class ObstacleFactory {

    public static ShapeObstacle createObstacle(String shape, int x, int y){
        if(shape==null){
            return null;
        }


        ObstacleModel obsModel = new ObstacleModel(new Vector2d(x,y));
        obsModel.setEnableTrajectory(false);

        ObstacleController obsController = new ObstacleController(obsModel);

        ShapeObstacle obsView;

        if(shape.equalsIgnoreCase("SQUARE")){
            obsModel.setWidth(70);
            obsModel.setHeight(70);
            obsView = new RectangleObstacle(obsModel, obsController);
            obsView.setHidden(false);
        }
        // CIRCLE
        else {
            obsView = new CircleObstacle(obsModel, obsController);


        }

        obsModel.addView(obsView);

        return obsView;
    }

}
