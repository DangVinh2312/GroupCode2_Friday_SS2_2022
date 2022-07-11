/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;

public class Conflict {
    private float[] conflictDataSet ;
    public Conflict(float[] conflict)  {
        this.conflictDataSet = conflict;
    }
   /*
    * return conflict data set
    * 
    */
    public float[] getConflictDataSet() {
    	return this.conflictDataSet;
    }
    /*
     * show conflict set
     */
    public String toString() {
    	String result = "";
    	for(int i=0;i<this.conflictDataSet.length;i++) {
    		result = result +String.valueOf(conflictDataSet[i]) +" ";
    	}
    	return result;
    }
}
