import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
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
public class ChartView
{
    private ChartController chartController;

    private Label lbl;
    private TextField tfd;

    /**
     * Default constructor for the Chart View class.
     * @param c - CpuController object
     */
    public ChartView(ChartController c)
    {
        chartController = c;
    }

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
        lbl = new Label( "Replace with Chart" );
        tfd = new TextField( "Default Text" );

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

        barCMItem.setOnAction( (ae)->{ tfd.setText( "Bar Chart" ); } );
        areaCMItem.setOnAction( (ae)->{ tfd.setText( "Area Chart" ); } );
        lineCMItem.setOnAction( (ae)->{ tfd.setText( "Line Chart" ); } );
        stackedCMItem.setOnAction( (ae)->{ tfd.setText( "Stacked Line Chart" ); } );

        itemBar.setOnAction( (ae)->{ tfd.setText( "Bar Chart" ); } );
        itemArea.setOnAction( (ae)->{ tfd.setText( "Area Chart" ); } );
        itemLine.setOnAction( (ae)->{ tfd.setText( "Line Chart" ); } );
        itemStacked.setOnAction( (ae)->{ tfd.setText( "Stacked Line Chart" ); } );

        itemOpen.setOnAction( (ae) -> {tfd.setText( "Open Input File" );});
        itemExit.setOnAction( (ae) -> {Platform.exit();});

        ////////////////////////////////////////////
        // Set the initial control locations
        ////////////////////////////////////////////
        tfd.setContextMenu( cMenu );
        rootNode.setTop( mb );
        rootNode.setCenter( lbl );
        rootNode.setBottom( tfd );
        myStage.show();

    }
}
