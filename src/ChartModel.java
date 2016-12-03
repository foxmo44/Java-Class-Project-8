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
public class ChartModel
{
    private ArrayList< Quarter > m_Quarters;

    /**
     * Constructor for the Chart Model
     */
    public ChartModel()
    {
        m_Quarters = new ArrayList<Quarter>();
    }

    /**
     * Open the file and read it into the object model for the programming language data
     * @param strFilename
     * @return true if the input file was successfully processed
     */
    public boolean OpenFile(String strFilename)
    {
        boolean bRetValue = false;

        bRetValue = HardcodeValues();

        //bRetValue = ReadValues(strFilename);

        return  bRetValue;
    }

    /**
     * The primary function to get the data for each quarter for programming languages
     * @return The arraylist containing the data for each quarter
     */
    public ArrayList <Quarter> GetData()
    {
        return (m_Quarters);
    }

    /**
     * Read the input data and populate the object model for the series representing the quarters
     * @param strFilename
     * @return true if the data is read
     */
    private boolean ReadValues(String strFilename)
    {
        //TODO - READ THE DATA FROM THE INPUT FILES
        return(false);
    }

    private boolean HardcodeValues()
    {
        Quarter qrtWinter = new Quarter("Winter");
        qrtWinter.Add(new Language("Python", 42));
        qrtWinter.Add(new Language("C++", 150));
        qrtWinter.Add(new Language("Java", 125));
        qrtWinter.Add(new Language("C#", 105));
        m_Quarters.add(qrtWinter);

        Quarter qrtSpring = new Quarter("Spring");
        qrtSpring.Add(new Language("Python", 65));
        qrtSpring.Add(new Language("C++", 110));
        qrtSpring.Add(new Language("Java", 178));
        qrtSpring.Add(new Language("C#", 120));
        m_Quarters.add(qrtSpring);

        Quarter qrtSummer = new Quarter("Summer");
        qrtSummer.Add(new Language("Python", 56));
        qrtSummer.Add(new Language("C++", 109));
        qrtSummer.Add(new Language("Java", 145));
        qrtSummer.Add(new Language("C#", 204));
        m_Quarters.add(qrtSummer);

        Quarter qrtFall = new Quarter("Fall");
        qrtFall.Add(new Language("Python", 64));
        qrtFall.Add(new Language("C++", 95));
        qrtFall.Add(new Language("Java", 168));
        qrtFall.Add(new Language("C#", 139));
        m_Quarters.add(qrtFall);

        return (true);
    }



}
