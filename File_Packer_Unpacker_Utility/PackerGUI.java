import javax.swing.*;
import java.awt.event.*;
import MarvellousPackerUnpacker.MarvellousPacker;

class PackerGUI 
{
    public static void main(String A[])
    {
        JFrame fobj = new JFrame("Packer");
        JLabel lobj1 = new JLabel("Name of Directory");
        JTextField tobj1 = new JTextField();

        JLabel lobj2 = new JLabel("Name of Packed File");
        JTextField tobj2 = new JTextField();

        JButton bobj = new JButton("Pack");
        
        fobj.setLayout(null);
        fobj.add(lobj1);
        fobj.add(tobj1);
        fobj.add(lobj2);
        fobj.add(tobj2);
        fobj.add(bobj);

        lobj1.setBounds(10,20,120,20);
        tobj1.setBounds(150,20,150,20);

        lobj2.setBounds(10,50,120,20);
        tobj2.setBounds(150,50,150,20);

        bobj.setBounds(150,80,100,20);

        bobj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aobj)
            {
                String a = null, b = null;
                int Result = 0;

                a = tobj1.getText();
                b = tobj2.getText();

                MarvellousPacker mobj = new MarvellousPacker(b,a);

                Result = mobj.PackingActivity();
                if(Result == 1)
                {
                    JOptionPane.showMessageDialog(fobj, "Packing Activity Successful!!!!");
                }
                else
                {
                    JOptionPane.showMessageDialog(fobj, "Packing Activity Unsuccessful!!!!");
                }
            }
        });

        fobj.setSize(500,500);
        fobj.setVisible(true); 
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}