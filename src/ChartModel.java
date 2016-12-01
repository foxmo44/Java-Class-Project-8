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

    public boolean OpenFile(String strFilename)
    {
        boolean bRetValue = false;

        HardcodeValues();

        return  bRetValue;
    }

    private void HardcodeValues()
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
        qrtSummer.Add(new Language("Python", 65));
        qrtSummer.Add(new Language("C++", 110));
        qrtSummer.Add(new Language("Java", 178));
        qrtSummer.Add(new Language("C#", 120));
        m_Quarters.add(qrtSummer);

        Quarter qrtFall = new Quarter("Fall");
        qrtFall.Add(new Language("Python", 65));
        qrtFall.Add(new Language("C++", 110));
        qrtFall.Add(new Language("Java", 178));
        qrtFall.Add(new Language("C#", 120));
        m_Quarters.add(qrtFall);
    }



}
