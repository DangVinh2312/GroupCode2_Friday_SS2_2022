/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;

/**
 *    Java Program to Implement Gale Shapley Algorithm 
 *    This algorithm is inspired by the marriage maching problem with the idea of 1:1 matching, so that no student fails to apply to the school.
 **/
 
/** Class GaleShapley **/
public class GaleShapleyAlgorithm
{
    private int N, engagedCount;
    private String[][] menPref;
    private String[][] womenPref;
    private String[] men;
    private String[] women;
    private String[] womenPartner;
    private boolean[] menEngaged;
 
    /** Constructor **/
    public GaleShapleyAlgorithm(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        N = mp.length;
        engagedCount = 0;
        men = m;
        women = w;
        menPref = mp;
        womenPref = wp;
        menEngaged = new boolean[N];
        womenPartner = new String[N];
        calcMatches();
    }
    /** function to calculate all matches **/
    private void calcMatches()
    {
        while (engagedCount < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!menEngaged[free])
                    break;
 
            for (int i = 0; i < N && !menEngaged[free]; i++)
            {
                int index = womenIndexOf(menPref[free][i]);
                if (womenPartner[index] == null)
                {
                    womenPartner[index] = men[free];
                    menEngaged[free] = true;
                    engagedCount++;
                }
                else
                {
                    String currentPartner = womenPartner[index];
                    if (morePreference(currentPartner, men[free], index))
                    {
                        womenPartner[index] = men[free];
                        menEngaged[free] = true;
                        menEngaged[menIndexOf(currentPartner)] = false;
                    }
                }
            }            
        }
        
    }
    /** function to check if women prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (womenPref[index][i].equals(newPartner))
                return true;
            if (womenPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    /** get men index **/
    private int menIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (men[i].equals(str))
                return i;
        return -1;
    }
    /** get women index **/
    private int womenIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (women[i].equals(str))
                return i;
        return -1;
    }
    /** get womenPartner index **/
    private int womenPartnerIndexOf(String str) {
    	for (int i = 0; i < N; i++)
            if (womenPartner[i].equals(str))
                return i;
        return -1;
    }
    /** print couples **/
    public void printCouples()
    {
        System.out.println("Couples are : ");
        for (int i = 0; i < N; i++)
        {
            System.out.println(womenPartner[i] +" "+ women[i]);
        }
    }
    public String getSchoolMatchingGroup(String groupName) {
    	
    	return women[womenPartnerIndexOf(groupName)];
    }
    /** main function **/
    public static void main(String[] args) 
    {
        System.out.println("Gale Shapley Marriage Algorithm\n");
        /** list of men **/
        String[] m = {"M1", "M2", "M3"};
        /** list of women **/
        String[] w = {"W1", "W2", "W3"};
 
        /** men preference **/
        String[][] mp = {
                         {"W1", "W3", "W2"}, 
                         {"W1", "W2", "W3"},
                         {"W1", "W2", "W3"}};
        /** women preference **/                      
        String[][] wp = {{"M1", "M2", "M3"}, 
                         {"M1", "M2", "M3"}, 
                         {"M1", "M2", "M3"},
                        };
 
        GaleShapleyAlgorithm gs = new GaleShapleyAlgorithm(m, w, mp, wp);                        
    }
}