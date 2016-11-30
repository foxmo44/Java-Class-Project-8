import javafx.scene.chart.Chart;
import javafx.stage.Stage;

/**
 * <h1>Advanced Java - Project08Fox</h1>
 * <h1>${FILE_NAME}  Class</h1>
 * This is the class description XXXXXXX
 * <p>
 * <b>Create Date: 11/30/2016</b>
 * <b>Due Date: XXXXXXXX</b>
 *
 * @author Michael Fox
 */
public class ChartController
{
    private ChartView chartView;

        /**
         * The default constructor for the Chart Controller class.  This will instantiate the Chart View class
         */
    public ChartController()
    {
        chartView = new ChartView(this);
    }

        /**
         * Simply pass on the main stage to the view class
         * @param mainStage - main stage for the Java Fx GUI
         */
    void Start(Stage mainStage)
    {
        chartView.Start(mainStage);
    }
}
