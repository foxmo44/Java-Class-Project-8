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
import java.util.Set;

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
    private XYChart< String, Number >         myChart;
    ChartType                                 m_eChartType;

    public enum ChartType
    {
        eBarChart,
        eAreaChart,
        eStackedAreaChart,
        eLineChart,
    };


    /**
     * Default constructor for the Chart View class.
     * @param c - CpuController object
     */
    public ChartView(ChartController c)
    {
        chartController = c;
        m_eChartType = ChartType.eBarChart;    //type of chart to startup with.
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

        barCMItem.setOnAction( (ae)->
        {
            ShowBarChart();
            rootNode.setCenter( myChart );
        } );

        areaCMItem.setOnAction( (ae)->
        {
            ShowAreaChart();
            rootNode.setCenter( myChart );
        } );

        lineCMItem.setOnAction( (ae)->
        {
            ShowLineChart();
            rootNode.setCenter( myChart );
        } );

        stackedCMItem.setOnAction( (ae)->
        {
            ShowStackedLineChart();
            rootNode.setCenter( myChart );
        } );

        itemBar.setOnAction( (ae)->
        {
            ShowBarChart();
            rootNode.setCenter( myChart );
        } );

        itemArea.setOnAction( (ae)->
        {
            ShowAreaChart();
            rootNode.setCenter( myChart );
        } );

        itemLine.setOnAction( (ae)->
        {
            ShowLineChart();
            rootNode.setCenter( myChart );
        } );

        itemStacked.setOnAction( (ae)->
        {
            ShowStackedLineChart();
            rootNode.setCenter( myChart );
        } );

        itemOpen.setOnAction( (ae) ->
            {
                OpenDataFile(myStage);
            });

        itemExit.setOnAction( (ae) -> {Platform.exit();});

        ////////////////////////////////////////////
        // Set the initial control locations
        ////////////////////////////////////////////

        SetupChartAxis();

        CreateChart();

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
        m_eChartType = ChartType.eBarChart;
        RefreshChart();
    }

    /**
     * Show the area chart view of the data
     */
    private void ShowAreaChart()
    {
        m_eChartType = ChartType.eAreaChart;
        RefreshChart();
    }

    /**
     * Show the line chart view of the data
     */
    private void ShowLineChart()
    {
        m_eChartType = ChartType.eLineChart;
        RefreshChart();
    }

    /**
     * Show the stacked line chart view of the data
     */
    private void ShowStackedLineChart()
    {
        m_eChartType = ChartType.eStackedAreaChart;
        RefreshChart();
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
        XYChart.Series< String, Number > objSeries = null;
        int iSeriesIndex = 0;

        if(seriesData.size() > 0)
        {
            //Iterate through the object model that represents the quarter data for programming languages
            for (Quarter quarter : seriesData)
            {
                //System.out.println("-------------------");
                //System.out.println(quarter.GetName());
                //System.out.println("-------------------");

                objSeries = new XYChart.Series<>();     //instantiate the series

                objSeries.setName(quarter.GetName());   //Set the name of the series

                for (Language language : quarter.GetData())
                {
                    //System.out.printf("Language %s      %d\n", language.GetName(), language.GetQuantity());

                    objSeries.getData().add( new XYChart.Data< String, Number>( language.GetName(),  language.GetQuantity() ) );
                }

                //add the series to the chart
                myChart.getData().add(objSeries);

                //objSeries = null;

                iSeriesIndex++;
            }

            myChart.setAnimated( true );
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

    /**
     * delete the current chart and recreate the chart based on the latest settings
     */
    private void RefreshChart()
    {
        ArrayList< Quarter > Quarters;

        SetupChartAxis();

        CreateChart();

        Quarters = chartController.GetData();

        ChartData(Quarters);
    }

    /**
     * Create the chart based on the input argument
     */
    private void CreateChart()
    {
        switch (m_eChartType)
        {
            case eLineChart:
                System.out.println("Creating Line Chart");
                myChart = new LineChart<>(xAxis, yAxis);
                break;

            case eBarChart:
                System.out.println("Creating Bar Chart");
                myChart = new BarChart<>(xAxis, yAxis);
                break;

            case eAreaChart:
                System.out.println("Creating Area Chart");
                myChart = new AreaChart<>(xAxis, yAxis);
                break;

            default:
            case eStackedAreaChart:
                System.out.println("Creating Stacked Area Chart");
                myChart = new StackedAreaChart<>(xAxis, yAxis);
                break;

        }

        myChart.setTitle( "Programming Languages Line Chart" );

    }


}


