
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public  class Notepad extends JFrame implements ActionListener{
   
    JTextArea area;
    String text;
    Notepad(){
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("./icons/notepad.png"));
        Image icon =notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");


        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
 
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);
        
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        file.setFont(new Font("AERIAL",Font.PLAIN,14));
        menubar.add(file);



        JMenu edit = new JMenu("Edit");
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);
        
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        menubar.add(edit);


        
        JMenu helpmenu = new JMenu("About");
        
        JMenuItem help = new JMenuItem("Help");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
       help.addActionListener(this);
       
       helpmenu.add(help);

       menubar.add(helpmenu);
       
        
      



        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
      

        JScrollPane pane= new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);





        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae){
       if(ae.getActionCommand().equals("New")){
        area.setText("");}
        else if
            (ae.getActionCommand().equals("Open")){
                JFileChooser chooser = new JFileChooser();
                chooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only.txt files","txt"); 
                chooser.addChoosableFileFilter(restrict);
                chooser.showOpenDialog(this);
                int action = chooser.showOpenDialog(this); 
                if(action  != JFileChooser.APPROVE_OPTION){
                    return;
                }
                File file=  chooser.getSelectedFile();
                try{
                   
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    area.read(reader, null);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else if (ae.getActionCommand().equals("Save")) {
                JFileChooser saveas = new JFileChooser();
                saveas.setApproveButtonText("Save");
                int action = saveas.showOpenDialog(this);
                if(action != JFileChooser.APPROVE_OPTION){
                    return;
                }   
                File filename = new File(saveas.getSelectedFile()+".txt");
                BufferedWriter outFile = null;
                try {
                    outFile = new BufferedWriter(new FileWriter(filename));
                    area.write(outFile);
                } catch (Exception e) { 
                    e.printStackTrace();
                }           
            }
            else if (ae.getActionCommand().equals("Print")) {
                try {
                    area.print();
                    
                } catch (Exception e) {
                e.printStackTrace();     
                }
                
            }
            else if(ae.getActionCommand().equals("Exit")){
                  System.exit(0);
            }
            else if (ae.getActionCommand().equals("Copy")) {
                text = area.getSelectedText();
            }else if (ae.getActionCommand().equals("Paste")) {
                area.insert(text, area.getCaretPosition());
            }else if (ae.getActionCommand().equals("Cut")) {
                text = area.getSelectedText();
                area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
            }else if (ae.getActionCommand().equals("Select All")) {
                area.selectAll();
            } 
            else if (ae.getActionCommand().equals("About")) {
                new About().setVisible(true);
                
            }
        }
    
    public static void main(String[] args){
     new Notepad();
        
    }
    
    
}
