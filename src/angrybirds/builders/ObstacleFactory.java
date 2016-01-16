package angrybirds.builders;

import angrybirds.controllers.ObstacleController;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.CircleObstacle;
import angrybirds.views.RectangleObstacle;
import angrybirds.views.ShapeObstacle;

import java.util.Random;

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
            Random rnd = new Random();
            int a = rnd.nextInt(3);
            if(a==0){
                obsModel.setColor("yellow");
            } else if(a==1){
                obsModel.setColor("blue");
            } else if(a==2){
                obsModel.setColor("pink");
            } else {
                obsModel.setColor("green");
            }

            obsView = new CircleObstacle(obsModel, obsController);

        }

        obsModel.addView(obsView);

        return obsView;
    }

}
