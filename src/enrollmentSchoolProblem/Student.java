/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;

public class Student {
    private float mark;
	private String[] priorities;
	private String studentId;
	private String location;
	private float feeExpectation;
	
    /*
     * Constructor Student Object
     *      mark: float
     *      orderOfPriorityOfSchool: String[] - Example:[Hanu,Tmu,Ptits]
     */
	public Student(String studentId, float mark, String[] orderOfPriorityOfSchool, float feeExpectation, String location) {
		   this.studentId = studentId;
    	   this.mark = mark;
    	   this.priorities = orderOfPriorityOfSchool;
    	   this.location = location;
    	   this.feeExpectation = feeExpectation;
       }
    /*
     * return feeExpetation;
     */
	public float getFeeExpectation() {
		return this.feeExpectation;
	}
	/*
	 * return mark of student
	 */
	public float getMark() {
		return this.mark;
	}
	
	/*
	 * return id of student
	 */
	public String getStudentId() {
		return this.studentId;
	}
	
	/*
	 * return priorities of student
	 */
	public String[] getPriorities() {
		return this.priorities;
	}
	/*
	 * return id of student
	 */
	public String getLocaction() {
		return this.location;
		
	}
}
