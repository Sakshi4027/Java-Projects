package MarvellousPackerUnpacker;

import java.util.*;
import java.io.*;

public class MarvellousUnpacker
{
    private String PackName;

    public MarvellousUnpacker(String A)
    {
        this.PackName = A;
    }

    public int UnpackingActivity()
    {
        int Result = 0;
        try
        {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("---------------------------Packer Unpacker-------------------------------------");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("--------------------------Unpacking Activity-----------------------------------");
            System.out.println("-------------------------------------------------------------------------------");

            int FileSize = 0, iRet = 0,iCountFile = 0;
            String Header = null;
            File fobjnew = null;


            File fobj = new File(PackName);

            if(!fobj.exists())
            {
                System.out.println("Unable to access packed File");
                return 0;
            }

            System.out.println("Packed file gets successfully opened");

            FileInputStream fiobj = new FileInputStream(fobj);

            //Buffer to read the header
            byte HeaderBuffer[] = new byte[100];

            while((iRet = fiobj.read(HeaderBuffer,0,100)) != -1)
            {
                //Convert byte array to String
                Header = new String(HeaderBuffer);

                Header = Header.trim();

                String Tokens[] = Header.split(" ");

                fobjnew = new File(Tokens[0]);
                
                fobjnew.createNewFile();

                FileSize = Integer.parseInt(Tokens[1]);
                byte Buffer[] = new byte[FileSize];

                FileOutputStream foobj = new FileOutputStream(fobjnew);

                fiobj.read(Buffer,0,FileSize);

                foobj.write(Buffer,0,FileSize);

                System.out.println("File Unpacked with name : "+Tokens[0] +" having size "+Tokens[1]+" bytes.");

                foobj.close();
                iCountFile++;
            } //End of while

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("--------------------------Statistical Report-----------------------------------");
            System.out.println("-------------------------------------------------------------------------------");

            //To be add
            System.out.println("Total files packed : "+iCountFile);

                
            fiobj.close();
            
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("-------------------Thank you for using our Application-------------------------");
            System.out.println("-------------------------------------------------------------------------------");

            Result = 1;
        }
        catch(Exception eobj)
        {
            Result = 0;
        }

        return Result;
    }
}// End of MarvellousUnpacker class

//javac -d . MarvellousUnpacker.java