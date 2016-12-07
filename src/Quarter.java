import java.util.ArrayList;

/**
 * <h1>Advanced Java - Project08Fox</h1>
 * <h1>Quarter  Class</h1>
 * This is the class that stores the name of the quarter and all of the associated data points
 * <p>
 * <b>Create Date: 11/30/2016</b>
 * <b>Due Date: 121316</b>
 *
 * @author Michael Fox
 */
public class Quarter
{
    /**
     * Name of the Quarter such as Fall, Winter, Spring, or Summer
     */
    private String m_strName;
    /**
     * List of languages for the quarter
     */
    private ArrayList< Language > m_languages;

    /**
     * Constructor for the quarter object
     * @param strName - Name of the quarter. i.e. Fall, Winter, Spring, Summer
     */
    public Quarter(String strName)
    {
        m_strName = strName;
        m_languages = new ArrayList< Language >();
    }

    /**
     * Add a Language object to the quarter
     * @param objLanguage - language object to add to the quarter
     */
    public void Add(Language objLanguage)
    {
        m_languages.add(objLanguage);
    }

    /**
     * Getter for the list of languages for the quarter
     * @return - list of the languages for the quarter
     */
    public ArrayList<Language> GetData() {return(m_languages);};

    /**
     * Getter for the quarter name
     * @return name of the quarter
     */
    public String GetName() {return(m_strName);}
}
