import javafx.scene.chart.Chart;
import javafx.stage.Stage;
import java.util.ArrayList;

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
    private ChartModel chartModel;

    /**
     * The default constructor for the Chart Controller class.  This will instantiate the Chart View class
     */
    public ChartController()
    {
        chartView = new ChartView(this);
        chartModel = new ChartModel();
    }

    /**
     * Simply pass on the main stage to the view class
     * @param mainStage - main stage for the Java Fx GUI
     */
    void Start(Stage mainStage)
    {
        chartView.Start(mainStage);
    }

    /**
     * Used to provide the view class with data from the model class
     * @return The data representing the quarter data for the programming languages
     */
    public ArrayList<Quarter> GetData() { return(chartModel.GetData());}

    /**
     * Use the model to open and load the data to be charted
     * @param strFilename - Name of the input file
     * @return true if data is properly read from the file
     */
    public boolean OpenFile(String strFilename)
    {
        return (chartModel.OpenFile(strFilename));
    }
}
