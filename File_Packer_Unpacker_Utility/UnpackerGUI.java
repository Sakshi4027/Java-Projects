import javax.swing.*;
import java.awt.event.*;
import MarvellousPackerUnpacker.MarvellousUnpacker;

class UnpackerGUI 
{
    public static void main(String A[])
    {
        JFrame fobj = new JFrame("Packer");
        JLabel lobj1 = new JLabel("Name of Packed file :");
        JTextField tobj1 = new JTextField();

        JButton bobj = new JButton("Unpack");
        
        fobj.setLayout(null);
        fobj.add(lobj1);
        fobj.add(tobj1);
    
        fobj.add(bobj);

        lobj1.setBounds(10,20,120,20);
        tobj1.setBounds(150,20,150,20);

        bobj.setBounds(150,50,100,20);

        bobj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aobj)
            {
                String a = null;
                int Result = 0;

                a = tobj1.getText();
                MarvellousUnpacker mobj = new MarvellousUnpacker(a);

                Result = mobj.UnpackingActivity();
                if(Result == 1)
                {
                    JOptionPane.showMessageDialog(fobj, "Unpacking Activity Successful!!!!");
                }
                else
                {
                    JOptionPane.showMessageDialog(fobj, "Unpacking Activity Unsuccessful!!!!");
                }
            }
        });

        fobj.setSize(500,500);
        fobj.setVisible(true); 
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}