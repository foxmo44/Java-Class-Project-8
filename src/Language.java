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
public class Language
{
    private String m_strName;
    private int m_iQuantity;

    public Language(String strName, int iQuantity)
    {
        m_strName = strName;
        m_iQuantity = iQuantity;
    }

    /**
     * Getter for the name of the language
     * @return Name of the language
     */
    public String GetName() { return(m_strName);}

    /**
     * Getter for the quantity of the language
     * @return Quantity for the language
     */
    public int GetQuantity() { return (m_iQuantity);}
}
