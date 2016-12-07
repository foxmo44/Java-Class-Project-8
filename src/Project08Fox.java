/**
 * <h1>Advanced Java - Project08Fox</h1>
 * <h1>Project08Fox Class</h1>
 * This is the class that kicks it all off and instantiates the controller
 * <p>
 * <b>Create Date: 11/30/2016</b>
 * <b>Due Date: 121316</b>
 *
 * @author Michael Fox
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class Project08Fox extends Application
{
    private static ChartController chartController;

    /**
     * The main running function of the application
     * @param args - command line arguments for the application
     */
    public static void main(String [] args)
    {
        //Use the model view controller solution (MVC) model for the CPU interface to the db and viewing
        chartController = new ChartController();

        launch( args );

    }

    /**
     * The start function used by JavaFx.  This will pass the mainstage to the view class
     * @param mainStage - The main stage of the Java Fx GUI
     */
    public void start( Stage mainStage )
    {
        chartController.Start(mainStage);
    }

}
