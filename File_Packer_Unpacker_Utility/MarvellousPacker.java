package MarvellousPackerUnpacker;

import java.util.*;
import java.io.*;

public class MarvellousPacker
{
    private String PackName;
    private String DirName;

    public MarvellousPacker(String A, String B)
    {
        this.PackName = A;
        this.DirName = B;
    }

    public int PackingActivity()
    {
        int Result = 0;

        try
        {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("---------------------------Packer Unpacker-------------------------------------");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("---------------------------Packing Activity------------------------------------");
            System.out.println("-------------------------------------------------------------------------------");

            int i = 0,j = 0,iRet = 0,iCountFile = 0;

            File fobj = new File(DirName);

            //Check the existence of Directory
            if((fobj.exists()) && (fobj.isDirectory()))
            {
                System.out.println(DirName+" is successfully opened");

                File Packobj = new File(PackName);

                //Create a Packed File
                boolean bRet = Packobj.createNewFile();

                if(bRet == false)
                {
                    System.out.println("Unable to create file");
                    return 0;
                }

                System.out.println("Packed file gets successfully created with name : "+PackName);

                //Retrive all the files from directory
                File Arr[] = fobj.listFiles();

                //Packed file object
                FileOutputStream foobj = new FileOutputStream(Packobj);

                //Buffer for read-write activity
                byte Buffer[] = new byte[1024];
            
                String Header = null;

                //Directory traversal
                for(i = 0;i < Arr.length; i++)
                {
                    Header = Arr[i].getName() +" "+ Arr[i].length() ;

                    //Loop to form 100 byte header
                    for(j = Header.length();j < 100;j++)
                    {
                        Header = Header + " ";
                    }

                    //write header into packed file
                    foobj.write(Header.getBytes());

                    //Open file from directory for reading
                    FileInputStream fiobj = new FileInputStream(Arr[i]);

                    //Write contents of file into packed file
                    while((iRet = fiobj.read(Buffer)) != -1)
                    {
                        foobj.write(Buffer,0,iRet);
                        System.out.println("File name Scanned :"+Arr[i].getName() );
                        System.out.println("File size read is : "+iRet);
                    }
    
                    fiobj.close();
                    iCountFile++;

                }

                System.out.println("Packing activity done...");

                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("--------------------------Statistical Report-----------------------------------");
                System.out.println("-------------------------------------------------------------------------------");

                //To be add
                System.out.println("Total files packed : "+iCountFile);

                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("-------------------Thank you for using our Application-------------------------");
                System.out.println("-------------------------------------------------------------------------------");

                Result = 1;
            }
            else
            {
                System.out.println("There is no such directory");
                Result = 0;
            }
        } // End of try
        catch(Exception eobj)
        {
            Result = 0;
        }
        return Result;
    }// End of PackingActivity method
} // End of MarvellousPacker class

//javac -d . MarvellousPacker.java