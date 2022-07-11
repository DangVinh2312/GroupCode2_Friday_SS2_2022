/**
 * @author Vu Dinh Duy _ 1901040047
 */
package enrollmentSchoolProblem;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputDataDriver {
    //row to start reading
    private static final int START_ROW = 39;
    //path to access file xlsx
    private String path;
    //Constructor
    public InputDataDriver(String path) {
	   this.path = path;
    }

    /**
     * 
     * @effects
     *    Get normalPlayer list from input data
     */
    public List<NormalPlayer> getNormalPlayer() {
    	//list normalPlayer
        List<NormalPlayer> normalPlayerList ;
        //define line to start
        int row = START_ROW;
        // get number of normal player 
        int numberOfNormalPlayer = Integer.parseInt(getStringOfCoordinate(37,0));
        // loop normal player one by one and add it to list
        normalPlayerList = new ArrayList<>(numberOfNormalPlayer);
        for (int i = 0; i <numberOfNormalPlayer; i++) {
            normalPlayerList.add(setNormalPlayer(row));
            row++;
        }
        return normalPlayerList;
    }
    /**
     
     * @effects
     *    get all data of normalPlayer at row i
     */
    private  NormalPlayer setNormalPlayer(int row){
    	//get number of data of player
        int strategiesOfPlayer = Integer.valueOf(getStringOfCoordinate(row, 0));
        //define list to add data of player
        List<String> strategies = new ArrayList<>(strategiesOfPlayer);
        //column to start reading
        int column = 1;
        /*
         *   if numberData !=50
         *      player is student
         */ 
        if(strategiesOfPlayer!=50) {
        	for (int i = 0; i < strategiesOfPlayer; i++) {
                strategies.add(getStringOfCoordinate(row, column));
                column++; 
        }
        	 /*
             *   if numberData ==50
             *      player is school
             *      => particular number of data is 5
             */
        }else {
        	for (int i = 0; i < 5; i++) {
                strategies.add(getStringOfCoordinate(row, column));
                column++;
        }
        }
        return new NormalPlayer(strategies);
    }
    
    /**
     * @effects
     *    return value of a cell at row rowIndex and column columnIndex in String type
     * 
     */
    private  String getStringOfCoordinate(int rowIndex, int columnIndex) {
        try {
        	//Access path
            FileInputStream file = new FileInputStream(this.path);
            // create workbook for file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // get the first sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Lấy row có index là rowIndex
            Row row = sheet.getRow(rowIndex);
            //if row null, return null
            if (row.getCell(columnIndex) == null) {
                return null;
            }
            //if row != null, get cell index columnIndex on this row
            Cell cell = row.getCell(columnIndex);
            //convert data format of excel file to String type
            DataFormatter df = new DataFormatter();
        	String value = df.formatCellValue(cell);
            //close file 
            file.close();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
   /**
    * @effects
    *  Show data of normal player to the console screen
    */
    private static void displayNormalPlayerList(List<NormalPlayer> normalPlayerList){
        for(NormalPlayer normalPlayer : normalPlayerList){
            System.out.println("Normal player : " + (normalPlayerList.indexOf(normalPlayer)+1));
            normalPlayer.display();
            System.out.println("---------------");
        }
    }
    /**
     *  @effects
     *     Get student list from normalPlayer list
     * 
     */
    public  List<Student> getStudentList(){
    	//define student list
    	List<Student> studentList = new ArrayList<>();
    	//get normal player list
    	List<NormalPlayer> normalPlayerList = getNormalPlayer();
    	//Loop from 0 to numberOfStudent in normalPlayerList
    	for(int i=0;i<getNumberOfStudent();i++) {
    		//get normal player
    		NormalPlayer player = normalPlayerList.get(i);
    		//define priority of each student
    		String[] priority = new String[getNumberOfSchool()];
    		for(int j=0;j<getNumberOfSchool();j++) {
    			priority[j] = player.getStrategyAt(j);
    		}
    		//define avgScore of each Student
    		float avgScore  = Float.parseFloat(player.getStrategyAt(getNumberOfSchool()));
    		//define location of each student
    		String location = player.getStrategyAt(getNumberOfSchool()+1);
    		//define id student 
    		String id = player.getStrategyAt(getNumberOfSchool()+3);
    		//define feeExpectation
    		float feeExpectation = Float.parseFloat(player.getStrategyAt(getNumberOfSchool()+2));
    		studentList.add(new Student(id,avgScore,priority,feeExpectation,location));
    	}
    	return studentList;
    }
    
    /**
     *  @effects
     *     Get school list form normalPlayer list
     * 
     */
    public List<School> getSchoolList(){
    	List<School> schoolList = new ArrayList<>();
    	//get normal player list
    	List<NormalPlayer> normalPlayerList = getNormalPlayer();
    	for(int i=0;i<getNumberOfSchool();i++) {
    		NormalPlayer player = normalPlayerList.get(getNumberOfStudent()+i);
    		//define name of school
    		String nameSchool = player.getStrategyAt(0);
    		//define numberOfStudentTarget
    		int numStudentTarget = Integer.parseInt(player.getStrategyAt(1));
    		//define standard score
    		float standardScore = Float.parseFloat(player.getStrategyAt(2));
    		//define fee
    		float fee = Float.parseFloat(player.getStrategyAt(3));
    		//define location
    		String location = player.getStrategyAt(4);
    		schoolList.add(new School(nameSchool,numStudentTarget,standardScore,fee,location));
    	}
    	return schoolList;
    }
    /**
    * @effects
    *    return List<Conflict>
    */
   public List<Conflict> getConflictSet(){
	   //define list to contain conflict data
       List<Conflict> conflictSet = new ArrayList<>();
       //define row to start reading
       int row = START_ROW + getNormalPlayer().size()+1;
       //conflict data for student
       float[] conflict = new float[getNumberOfSchool()+4];
       for(int i=0;i<getNumberOfSchool()+4;i++) {
    	    conflict[i] =Float.parseFloat(getStringOfCoordinate(row, i)) ;
       }
       conflictSet.add(new Conflict(conflict));
       row++;
       //conflict data for school
       float[] conflict2 = new float[2];
       for(int i=0;i<2;i++) {
    	   conflict2[i] = Float.parseFloat(getStringOfCoordinate(row, i));
       }
       conflictSet.add(new Conflict(conflict2));
      
       return conflictSet;
   }
   /**
    * @effects
    *    Get number of school
    **/
    public int getNumberOfSchool() {
    	//define start line to read
    	int row = START_ROW;
    	//result to return function
    	int result=0;
    	// get number of normal player 
        int numberOfNormalPlayer = Integer.parseInt(getStringOfCoordinate(37,0));
        /*
         * loop normalPlayerList
         *     if normalPlayer is school
         *         result++;
         */
        for(int i=0;i<numberOfNormalPlayer;i++) {
        	
        	int strategiesOfPlayer = Integer.valueOf(getStringOfCoordinate(row, 0));
        	if( strategiesOfPlayer==50) {
        		result++;
        	}
        	row++;
        }
    	return result;
    }
    /**
     * @effects
     *    Get number of student
     */
     public int getNumberOfStudent() {
    	//define start line to read
     	int row = START_ROW;
     	int result=0;
     	// get number of normal player 
         int numberOfNormalPlayer = Integer.parseInt(getStringOfCoordinate(37,0));
         /*
          * loop normalPlayerList
          *     if normalPlayer is student
          *         result++;
          */
         for(int i=0;i<numberOfNormalPlayer;i++) {
         	int strategiesOfPlayer = Integer.valueOf(getStringOfCoordinate(row, 0));
         	if( strategiesOfPlayer!=50) {
         		result++;
         	}
         	row++;
         }
     	return result;
     }
}
