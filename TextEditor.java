import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
//creating frame
    JFrame frame;
//creating text area
    JTextArea textArea;
//    creating menu bar
    JMenuBar JMenuBar;
//creating constructor
    TextEditor()
    {
//initializing the frame
        frame=new JFrame("Text Editor");
//initialinzing the text area
        textArea = new JTextArea();
//        initializing menu bar
        JMenuBar = new JMenuBar();
//creating menu
        JMenu file = new JMenu("File");
        JMenu edit= new JMenu("Edit");
//creating menu items for file menu
        JMenuItem openFile=new JMenuItem("Open File");
        JMenuItem saveFile=new JMenuItem("Save File");
        JMenuItem printFile=new JMenuItem("Print File");
        JMenuItem newFile=new JMenuItem("New File");
//adding function to menu item
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);
//adding menu items to file menu
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);
//creating menu items for edit menu
        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");
        JMenuItem close=new JMenuItem("Close");
//adding function to menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);
//adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);
//adding menu to menu bar
        JMenuBar.add(file);
        JMenuBar.add(edit);
//adding menu bar in frame
        frame.setJMenuBar(JMenuBar);
//adding text area to frame
        frame.add(textArea);
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args)
    {
        TextEditor obj=new TextEditor();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String call=e.getActionCommand();
//implementing action
        if(call=="New File")
        {
            textArea.setText("");
        }
        else if(call=="Cut")
        {
            textArea.cut();
        }
        else if(call=="Copy")
        {
            textArea.copy();
        }
        else if(call=="Paste")
        {
            textArea.paste();
        }
        else if(call=="Close")
        {
            frame.setVisible(false);
        }
        else if(call=="Save File")
        {
            JFileChooser jFileChooser=new JFileChooser("c:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }
                try{
                    writer.flush();
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }
                try{
                    writer.close();
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Open File")
        {
            JFileChooser jFileChooser1=new JFileChooser("c:");
            int ans=jFileChooser1.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser1.getSelectedFile().getAbsolutePath());
                try {
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader((new FileReader(file)));
                    s2= bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textArea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if(call=="Print File")
        {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
