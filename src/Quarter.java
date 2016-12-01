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
public class Quarter
{
    private String m_strName;
    private ArrayList< Language > m_languages;

    public Quarter(String strName)
    {
        m_strName = strName;
        m_languages = new ArrayList< Language >();
    }

    public void Add(Language objLanguage)
    {
        m_languages.add(objLanguage);
    }
}
