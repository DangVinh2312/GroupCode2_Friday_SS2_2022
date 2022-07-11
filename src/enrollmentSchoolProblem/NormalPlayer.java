/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;

import java.util.List;
/**
 * @object A typical Normal Player is
 *
 * @attributes
 *      strategies List<String>
 */
public class NormalPlayer {
    private List<String> strategies;
    public NormalPlayer(List<String> strategies){
        this.strategies = strategies;
    }

    public List<String> getStrategiesList() {
        return strategies;
    }

    public int getStrategiesSize(){
        return strategies.size();
    }
    
    public String getStrategyAt(int index){
        return strategies.get(index);
    }

    public void display(){
        for(String s: strategies){
            System.out.print("Strategy " + (strategies.indexOf(s) + 1) + ":\t"  );
                System.out.print(s + "\t");
            System.out.println();
        }
    }
}
