/**
*
*Class purpose:
*Help MSU Denver track their students and their grades in various courses
*
*@version 1.0
*
*@author Daniel L'Episcopo
*@author Sinh Mai 
*
*Class/Computer Info:
*Programming Project #2, CS 2050, Section 2
*jGRASP 2.04.4_0, Macbook Pro 2015 OS X
*
*Vocab Word: 
*Hamartia (Noun | hah-mahr-TEE-uh)
*A flaw in character that brings about the downfall of the hero 
*of a tragedy: tragic flaw.
*
*Quote:
*"I have learned over the years that when one's mind if made up, this diminishes
*fear; knowning what must be done does away with fear."
*- Rosa Parks (1913 - 2005)
*
*/

import java.io.IOException; // Access the IOException class
import java.util.Scanner;   // Access the Scanner class
import java.io.*;           // Access IO and related classes

public class DanielLEpiscopo_04 { 
   
   private static Student student;           // Array reference for Student
   private static GradeItem gradeItem;       // Array reference for GradeItem
   
   private static Scanner inputFile;         // Use the Scanner to read inuput file
   private static File inputDataFile;        // Open the input file to read data
   private static PrintWriter outputFile;    // Use the Scanner to read output file
   private static FileWriter outputDataFile; // Open the output file to display data
   
   // List of Student and GradeItem items
   private static LList<Student> linkListOfStudents;    
   private static LList<GradeItem> linkListOfGradeItems;
   
   // Array to store input file values
   private static String[] data = new String[8];
   
   private static final String INPUT_FILE = "Project_04_Input01.txt";
   private static final String OUTPUT_FILE = "Project_04_Output01.txt";
   
   public static void main (String[] args) throws IOException {
      
      linkListOfStudents = new LList<Student>();     // Link list of students 
      linkListOfGradeItems = new LList<GradeItem>(); // Link list of grade items 
      
      // *****   Begin Program Execution   *****
      
      // This will process the input file
      processInput();
      
      // This will generate a report in the output file
      generateReport();
      
      // *****   End Program Execution   *****
      
      // Exit
      inputFile.close();
      outputFile.close();
      System.exit(0);
    
    } // End Main
    
      // ***** Begin Methods Section *****
      
      // ***************************************************************
      
      /**
      * Opens up the input file, reads in the data and sorts it into an
      * array called data. Then checks to see if data.[0] equals STUDENT
      * or if it equals GRADE ITEM. From there it processes the data.
      */
      public static void processInput() throws IOException, IllegalArgumentException {
        
         String temp   = ""; // Temp value to store input data
         
         try {
            
            System.out.println("Reading data from file " + INPUT_FILE);
            
            inputDataFile = new File(INPUT_FILE);
            inputFile     = new Scanner(inputDataFile);
            
            while(inputFile.hasNextLine()) {
         
               temp = inputFile.nextLine();
               data = temp.split(",");
               
               if (data[0].equals("STUDENT")) {
                  
                  processStudentData(data);
                  
               } // End If
                  
               else if (data[0].equals("GRADE ITEM")) { 
           
                  processGradeItemData(data);
                  
               } // End Else if 
                  
               else {
                     throw new IllegalArgumentException(
                        "Invalid entry for either STUDENT or GRADEITEM");
                  
               } // End else 
                  
             } // End While  
         
         } catch (NullPointerException e) {
         
            System.err.println("NullPointerException found.");
            System.out.print(data);
            System.exit(0);
            
         } catch (FileNotFoundException e) {
      
            System.err.println("\nFile " + INPUT_FILE + " not found.");
            System.exit(0);
         
         } // End catch
         
      } // End ProcessInput
      
      // ***************************************************************
      
      /**
      * Processes Student data array for valid entries from input file
      *
      * @param data Array to store input file values
      */
      public static void processStudentData(String[] data) throws IllegalArgumentException {
      
         if (data[1].equals("ADD")) {
         
            student = new Student(data[2], data[3], data[4], data[5]);
            
            if (linkListOfStudents.contains(student)) {
            
               System.out.println("This list already contains this data");
               
               } // End if
              
            else {
            
               if (data[5].contains("@")) {
               
                  linkListOfStudents.add(student);
                  System.out.println("Student ID " + student.getStudentId() + 
                                     " has been added to the list.");
               
                     } // End if 
                                     
                  } // End else                       
            
               } // End if 
            
         else if (data[1].equals("DEL")) {
            
            if (linkListOfStudents.contains(student)) {
                           
               linkListOfStudents.remove(student);
               System.out.println("Student ID " + student.getStudentId() + 
                                  " has been deleted from the list.");
            
               } // End else  
            
            } // End else if
            
         else {
         
            throw new IllegalArgumentException(data[1] + 
               " is invalid input for 'ADD' or 'DEL' in the second field of Student."); 
         
            } // End else 
      
      } // End processStudentData
      
      // ***************************************************************
      
      /**
      * Processes GradeItem data array for valid entries from input file
      *
      * @param data Array to store input file values
      */
      public static void processGradeItemData(String[] data) throws IllegalArgumentException {
         
         
         if (data[1].equals("ADD")) {
         
            gradeItem = new GradeItem(Integer.parseInt(data[2]), data[3], data[4], 
                                      data[5], data[6],Integer.parseInt(data[7]), 
                                      Integer.parseInt(data[8]));
                                      
            if (linkListOfGradeItems.contains(gradeItem)) {
            
               System.out.println("This list already contains this data");
            
               } // End if
               
            else {
            
               linkListOfGradeItems.add(gradeItem);
               System.out.println("Grade Item " +  gradeItem.getGradeItem() + 
                                  " and Student ID " + gradeItem.getStudentId() + 
                                  " have been added to the list.");
               
               } // End else 
               
            } // End if   
                                      
         else if (data[1].equals("DEL")) {
                                      
            if (linkListOfGradeItems.contains(gradeItem)) {
            
               linkListOfGradeItems.remove(gradeItem);
               System.out.println("Grade Item " +  gradeItem.getGradeItem() + 
                                  " and Student ID " + gradeItem.getStudentId() + 
                                  " have been deleted from the list.");
            
               } // End if
            
            } // End else if
         
         else {
         
            throw new IllegalArgumentException(data[1] + 
               " is invalid input for 'ADD' or 'DEL' in the second field of Grade Item."); 
         
               } // End else                        
      
      } // End processGradeItemData
      
      // ***************************************************************
      
      /**
      * After processing, we crate array objects for Student and GradeItem
      * values. It iterates through both objects and prints to an output 
      * file, which will have ordered and calculated percentages. 
      */
      public static void generateReport() throws IOException {
        
        // Access the output file to write to it 
        outputDataFile = new FileWriter(OUTPUT_FILE);
        outputFile     = new PrintWriter(outputDataFile);
        
        // Student and Grade item objects calling toArray for objects list
        Object[] student   = linkListOfStudents.toArray();
        Object[] gradeItem = linkListOfGradeItems.toArray();
        
        System.out.println("Generating Report ... Done");
        
        for (int i = 1; i < linkListOfStudents.getCurrentSize(); i++) {
            
            int sumMaxScore  = 0; // Sum amount of max score points
            int sumActScore  = 0; // Sum amount of actual score points
            int gradePercent = 0; // Grade percentage for each student
            
            Student s = (Student) student[i];
            
            outputFile.println(s.getStudentId() + "  " +
                               s.getFirstName() + "  " +
                               s.getLastName()  + "  " +
                               s.getEmailAddress());
           	            
           outputFile.println("Grade Items");
            
         for (int j = 0; j < linkListOfGradeItems.getCurrentSize(); j++) {
            
            GradeItem g = (GradeItem) gradeItem[j];
            
            if (s.getStudentId().equals(g.getStudentId())) {
               
              outputFile.printf("%-8s %-8s %-12s %-12s %-4s %4s %n",
	            		   			g.getGradeItem(), 
	                              g.getCourseId(),
	                              g.getItemType(), 
	                              g.getDate(),     
	                              g.getMaxScore(), 
	                              g.getActScore());
	                           
	               sumMaxScore  += g.getMaxScore();
	               sumActScore  += g.getActScore();
	            
	               } // End if 
	        
	            } // End for 
	            
	            outputFile.println("=====================================" + 
	            				       "=========================");
	            
               // Calculates final grade score for student
	            gradePercent = (int) (100 * ((double) sumActScore/sumMaxScore));
	        
	            outputFile.printf("%-32s %14s %5s %6s", 
	            						"Total" ,
	            						sumMaxScore, 
	            						sumActScore, 
	            						gradePercent + "%");
                                 
	            outputFile.println("\n");
              
         } // End for
     
      } // End generateReport
      
      // ***************************************************************
      
      // ***** End Methods Section *****

} // End Class 