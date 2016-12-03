import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
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
public class ChartView
{
    private ChartController                   chartController;
    private TextField                         tfd;
    private CategoryAxis                      xAxis;
    private NumberAxis                        yAxis;
    private ScatterChart< String, Number >    myChart;
    /**
     * Default constructor for the Chart View class.
     * @param c - CpuController object
     */
    public ChartView(ChartController c)
    {
        chartController = c;
    }

    /**
     * Used to show the stage in the View class of the MVC model
     * @param myStage - Input stage to show
     */
    void Start(Stage myStage)
    {
        myStage.setTitle( "Fox Chart Program" );
        BorderPane rootNode = new BorderPane();
        Scene myScene = new Scene( rootNode, 600, 600 );
        myStage.setScene( myScene );

        ////////////////////////////////////////////
        // Instantiate the controls
        ////////////////////////////////////////////
        MenuBar mb = new MenuBar();
        tfd = new TextField( "Default Text" );
        tfd.setDisable(true);   //Make read only

        ////////////////////////////////////////////
        // File menu
        ////////////////////////////////////////////
        Menu fileMenu = new Menu( "_File" );
        MenuItem itemOpen = new MenuItem( "Open" );
        MenuItem itemExit = new MenuItem( "Exit" );
        itemExit.setAccelerator( KeyCombination.keyCombination( "shortcut+q" ));
        fileMenu.getItems().addAll( itemOpen, new SeparatorMenuItem(), itemExit );
        mb.getMenus().add( fileMenu );

        ////////////////////////////////////////////
        // Charts menu
        ////////////////////////////////////////////
        Menu otherMenu = new Menu( "Charts" );
        RadioMenuItem itemBar = new RadioMenuItem( "Bar" );
        RadioMenuItem itemArea = new RadioMenuItem( "Area" );
        RadioMenuItem itemLine = new RadioMenuItem( "Line" );
        RadioMenuItem itemStacked = new RadioMenuItem( "Stacked" );

        ToggleGroup tg = new ToggleGroup();
        itemBar.setToggleGroup( tg );
        itemArea.setToggleGroup( tg );
        itemLine.setToggleGroup( tg );
        itemStacked.setToggleGroup( tg );
        otherMenu.getItems().addAll( itemBar, itemArea, itemLine, itemStacked );
        mb.getMenus().add( otherMenu );

        ////////////////////////////////////////////
        // Charts Context menu
        ////////////////////////////////////////////
        ContextMenu cMenu = new ContextMenu();
        MenuItem barCMItem = new MenuItem( "Bar" );
        MenuItem areaCMItem = new MenuItem( "Area" );
        MenuItem lineCMItem = new MenuItem( "Line" );
        MenuItem stackedCMItem = new MenuItem( "Stacked" );
        cMenu.getItems().addAll( barCMItem, areaCMItem, lineCMItem, stackedCMItem);

        ////////////////////////////////////////////
        // Menu choice handlers
        ////////////////////////////////////////////

        barCMItem.setOnAction( (ae)->{ ShowBarChart(); } );
        areaCMItem.setOnAction( (ae)->{ ShowAreaChart(); } );
        lineCMItem.setOnAction( (ae)->{ ShowLineChart(); } );
        stackedCMItem.setOnAction( (ae)->{ ShowStackedLineChart(); } );

        itemBar.setOnAction( (ae)->{ ShowBarChart(); } );
        itemArea.setOnAction( (ae)->{ ShowAreaChart(); } );
        itemLine.setOnAction( (ae)->{ ShowLineChart(); } );
        itemStacked.setOnAction( (ae)->{ ShowStackedLineChart(); } );

        itemOpen.setOnAction( (ae) ->
            {
                OpenDataFile(myStage);
            });

        itemExit.setOnAction( (ae) -> {Platform.exit();});

        ////////////////////////////////////////////
        // Set the initial control locations
        ////////////////////////////////////////////

        SetupChartAxis();

        //TestData();

        tfd.setContextMenu( cMenu );
        rootNode.setTop( mb );
        rootNode.setCenter( myChart );
        rootNode.setBottom( tfd );
        myStage.show();

    }

    /**
     * Show the bar chart view of the data
     */
    private void ShowBarChart()
    {
        tfd.setText( "Bar Chart" );
    }

    /**
     * Show the area chart view of the data
     */
    private void ShowAreaChart()
    {
        tfd.setText( "Area Chart" );
    }

    /**
     * Show the line chart view of the data
     */
    private void ShowLineChart()
    {
        tfd.setText( "Line Chart" );
    }

    /**
     * Show the stacked line chart view of the data
     */
    private void ShowStackedLineChart()
    {
        tfd.setText( "Stacked Line Chart" );
    }

    /**
     * Open the input data file and read in the data points to be displayed via the model class
     */
    private void OpenDataFile(Stage myStage)
    {
        String strSelectedFile;
        ArrayList< Quarter > Quarters;

        FileChooser f = new FileChooser();
        f.setTitle( "Select a data file" );
        File selectedFile = f.showOpenDialog( myStage );

        if( selectedFile != null )
        {
            strSelectedFile = selectedFile.toString();
            System.out.println("Selected " + strSelectedFile);

            if(chartController.OpenFile(strSelectedFile) == true)
            {
                tfd.setText( String.format("Opened data file: %s", selectedFile.toString()) );

                Quarters = chartController.GetData();

                ChartData(Quarters);
            }
            else
            {
                tfd.setText( String.format("Could not open file: %s", selectedFile.toString()) );
            }
        }
    }

    /**
     * Chart the input data
     * @param seriesData - Data to be charted
     */
    private void ChartData(ArrayList<Quarter> seriesData)
    {
        //Iterate through the object model that represents the quarter data for programming languages
        for(Quarter quarter: seriesData)
        {
            System.out.println("-------------------");
            System.out.println(quarter.GetName());
            System.out.println("-------------------");
            for( Language language : quarter.GetData())
            {
                System.out.printf("Language %s      %d\n", language.GetName(), language.GetQuantity());
            }
        }
    }

    /**
     * Setup the axis for the various charts to use
     */
    private void SetupChartAxis()
    {
        xAxis = new CategoryAxis();
        xAxis.setLabel( "Programmers" );
        yAxis = new NumberAxis();
        yAxis.setLabel( "Lines of code" );
    }

    private void TestData()
    {
        //BarChart< String, Number > myChart = new BarChart<>( xAxis, yAxis );
        //AreaChart< String, Number > myChart = new AreaChart<>( xAxis, yAxis );
        //LineChart< String, Number > myChart = new LineChart<>( xAxis, yAxis );
        //StackedAreaChart< String, Number > myChart = new StackedAreaChart<>( xAxis, yAxis );
        myChart = new ScatterChart<>( xAxis, yAxis );
        myChart.setTitle( "Programming Languages" );

        XYChart.Series< String, Number > winter = new XYChart.Series<>();
        XYChart.Series< String, Number > spring = new XYChart.Series<>();
        XYChart.Series< String, Number > summer = new XYChart.Series<>();
        XYChart.Series< String, Number > fall = new XYChart.Series<>();

        winter.setName( "Winter" );
        winter.getData().add( new XYChart.Data< String, Number>( "Python",  42 ) );
        winter.getData().add( new XYChart.Data< String, Number>( "C++",     150 ) );
        winter.getData().add( new XYChart.Data< String, Number>( "Java",    125 ) );
        winter.getData().add( new XYChart.Data< String, Number>( "C#",      105 ) );

        spring.setName( "Spring" );
        spring.getData().add( new XYChart.Data< String, Number>( "Python",  65 ) );
        spring.getData().add( new XYChart.Data< String, Number>( "C++",     110 ) );
        spring.getData().add( new XYChart.Data< String, Number>( "Java",    178 ) );
        spring.getData().add( new XYChart.Data< String, Number>( "C#",      120 ) );

        summer.setName( "Summer" );
        summer.getData().add( new XYChart.Data< String, Number>( "Python",  56 ) );
        summer.getData().add( new XYChart.Data< String, Number>( "C++",     109 ) );
        summer.getData().add( new XYChart.Data< String, Number>( "Java",    145 ) );
        summer.getData().add( new XYChart.Data< String, Number>( "C#",      204 ) );

        fall.setName( "Fall" );
        fall.getData().add( new XYChart.Data< String, Number>( "Python",  64 ) );
        fall.getData().add( new XYChart.Data< String, Number>( "C++",     95 ) );
        fall.getData().add( new XYChart.Data< String, Number>( "Java",    168 ) );
        fall.getData().add( new XYChart.Data< String, Number>( "C#",      139 ) );

        myChart.getData().add( winter );
        myChart.getData().add( spring );
        myChart.getData().add( summer );
        myChart.getData().add( fall );

        myChart.setAnimated( true );
    }
}
