/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;

public class School {
	
	private int numberStudentTarget;
	private float standardScore;
	private String schoolName;
	private float fees;
	private String location;
    
	/*
	 * Constructor of school object
	 *     numberStudentTarget: integer
	 *     standardScore: float
	 *     
	 */
	public School(String schoolName, int numberStudentTarget, float standardScore, float fees, String location) {
		this.schoolName = schoolName;
		this.numberStudentTarget = numberStudentTarget;
		this.standardScore = standardScore;
		this.fees=fees;
		
		this.location=location;
	}
    
	/*
	 * Return numberStudentTarget
	 */
	public int getNumberStudentTarget() {
		return this.numberStudentTarget;
	}
	
	/*
	 * Return standardScore
	 */
	public float getStandardScore() {
		return this.standardScore;
	}
	
	/*
	 * Return schoolName
	 */
	public String getSchoolName() {
		return this.schoolName;
	}
	/*
	 * Return fees
	 */
	public float getFees() {
		return this.fees;
	}
	/*
	 * Return position
	 */
	public String getPosition() {
		return this.location;
	}
	
	
}
