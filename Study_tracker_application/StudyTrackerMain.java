import java.util.*;
import java.time.LocalDate;
import java.io.*;


class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Description;
    }

    //Getter methods
    public LocalDate getDate()
    {
        return Date;
    }

    public String getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescription()
    {
        return Description;
    }
}

class StudyTracker
{
    //Data Strutcure to hold the data about study
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();

    public void InsertLog()
    {
        Scanner Scannerobj = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--------------Please Enter the valid details of your study----------------------");
        System.out.println("--------------------------------------------------------------------------------");

        LocalDate Dateobj = LocalDate.now();

        System.out.println("Please Provide the name of subject like C/C++/Java/OS/DS");
        String sub = Scannerobj.nextLine();

        System.out.println("Enter the time period of your study in hours");
        double dur = Scannerobj.nextDouble();
        Scannerobj.nextLine();

        System.out.println("Please provide the description about the study for future reference");
        String desc = Scannerobj.nextLine();

        StudyLog Studyobj = new StudyLog(Dateobj,sub,dur,desc);

        Database.add(Studyobj);

        System.out.println("Study Log gets stored successfully");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void DisplayLog()
    {
        System.out.println("--------------------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--------------------Log Report from Marvellous Study Tracker--------------------");

        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }

        System.out.println("--------------------------------------------------------------------------------");

    }

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Nothing to export as database is empty");
            System.out.println("--------------------------------------------------------------------------------");
            return;
        }

        String FileName = "MyStudy.csv";

        //create new file
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            //it will dealloacte the resources automatically
            //Write CSV header
            fwobj.write("Date, Subject, Duration, Description\n");

            //write each record in csv
            for(StudyLog sobj : Database)
            {
                fwobj.write(sobj.getDate() + "," +
                            sobj.getSubject().replace(",","") + "," + 
                            sobj.getDuration()+ "," + 
                            sobj.getDescription().replace(","," ") + "\n"
                            );
            }

            System.out.println("Log created successfully");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the csv.");
            System.out.println("Report this issue to the creator");
        }
    }

    public void SummarybyDate()
    {
        System.out.println("--------------------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--------------------Summary By date from My Study Tracker-----------------------");

        TreeMap <LocalDate, Double> tobj = new TreeMap <LocalDate, Double> ();

        LocalDate lobj = null;

        double d, old;

        for(StudyLog sobj : Database)
        {
          lobj = sobj.getDate();

          d = sobj.getDuration();

          if(tobj.containsKey(lobj))
          {
            old = tobj.get(lobj);
            tobj.put(lobj, d + old);
          } 
          else
          {
            tobj.put(lobj, d);
          } 
        }

        //Display the details as per date
        for(LocalDate l : tobj.keySet())
        {
            System.out.println("Date : "+l+" Total Study "+tobj.get(l));
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public void SummarybySubject()
    {
        System.out.println("--------------------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("-----------------Summary By Subject from My Study Tracker-----------------------");

        TreeMap <String, Double> tobj = new TreeMap <String, Double> ();

        String s = null;

        double d, old;

        for(StudyLog sobj : Database)
        {
          
          s = sobj.getSubject();
          d = sobj.getDuration();

          if(tobj.containsKey(s))
          {
            old = tobj.get(s);
            tobj.put(s, d + old);
          } 
          else
          {
            tobj.put(s, d);
          } 
        }

        //Display the details as per subject
        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" Total Study "+tobj.get(str));
        }

        System.out.println("--------------------------------------------------------------------------------");
    }
}

class StudyTrackerMain       //StudyTrackerStarter
{
    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();

        Scanner Scannerobj = new Scanner(System.in);

        int iChoice = 0;

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("-----------Welcome to my Study tracker application------------------------------");
        System.out.println("--------------------------------------------------------------------------------");

        do
        {
            System.out.println(" 1 : Insert new Study Log into database");
            System.out.println(" 2 : View All Study Logs");
            System.out.println(" 3 : Summary of Study Log by Date");
            System.out.println(" 4 : Summary of Study Log by Subject");
            System.out.println(" 5 : Export Study Log  to CSV file");
            System.out.println(" 6 : Exit the Application");
            System.out.println("Please select the appropriate option");

            iChoice = Scannerobj.nextInt();

            switch(iChoice)
            {
                case 1:         //Insert new Study Log into database
                    stobj.InsertLog();
                    break;
                case 2:         //View All Study Logs
                    stobj.DisplayLog();
                    break;
                case 3:         //Summary of Study Log by Date
                    stobj.SummarybyDate();
                    break;
                case 4:         //Summary of Study Log by Subject
                    stobj.SummarybySubject();
                    break;
                case 5:         //Export Study Log  to CSV file
                    stobj.ExportCSV();
                    break;
                case 6:         //Exit the Application
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Thank you for using Study Tracker Application");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter the valid option");
                    break;
            }

        }while(true);



        

    }
}