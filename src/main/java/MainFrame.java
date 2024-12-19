//Code By Omer-Faruk Yildiz ICE20390038.

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//this is the main frame.

public class MainFrame extends JFrame {

    //titles to help the user.
    private JLabel titleLbl;
    private JLabel yearLbl;
    private JLabel lengthLbl;
    private JLabel artistLbl;

    //text fields for the user input.
    private JTextField titleTf;
    private JTextField yearTf;
    private JTextField lengthTf;
    private JTextField artistTf;

    private JComboBox genreCb;

    //create the 4 buttons.
    private JButton saveBtn;
    private JButton statsBtn;
    private JButton aboutBtn;
    private JButton exitBtn;

    //create the menu bar.
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem saveItem;
    private JMenuItem statsItem;
    private JMenuItem aboutItem;
    private JMenuItem exitItem;

    

    public MainFrame(){

        titleLbl=new JLabel("title:\t");
        yearLbl=new JLabel("year:\t");
        lengthLbl=new JLabel("length:\t");
        artistLbl=new JLabel("artist:\t");

        titleTf=new JTextField(15);
        yearTf=new JTextField(4);
        lengthTf=new JTextField(2);
        artistTf=new JTextField(15);


        genreCb=new JComboBox();
        genreCb.addItem("Rock");
        genreCb.addItem("Rap");
        genreCb.addItem("Pop");
        genreCb.addItem("Hip-Hop");

        saveBtn=new JButton("Save to file");
        statsBtn=new JButton("Song statistics");
        aboutBtn=new JButton("About");
        exitBtn=new JButton("Exit");

        menuBar=new JMenuBar();

        fileMenu=new JMenu("File");
        helpMenu=new JMenu("Help");

        saveItem=new JMenuItem("Save to file");
        statsItem=new JMenuItem("Song statistics");
        aboutItem=new JMenuItem("About");
        exitItem=new JMenuItem("Exit");

        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(statsItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);


        //Create two panels one for the top things 
        //and one for the bottom things to make it more user friendly
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel bottomPanel=new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));      

        topPanel.add(titleLbl);
        topPanel.add(titleTf);
        topPanel.add(yearLbl);
        topPanel.add(yearTf);
        topPanel.add(lengthLbl);
        topPanel.add(lengthTf);
        topPanel.add(artistLbl);
        topPanel.add(artistTf);
        topPanel.add(genreCb);

        bottomPanel.add(saveBtn);
        bottomPanel.add(statsBtn);
        bottomPanel.add(aboutBtn);
        bottomPanel.add(exitBtn);

        this.add(topPanel,BorderLayout.PAGE_START);
        this.add(bottomPanel,BorderLayout.PAGE_END);

        //actually create the window for the user to see.
        this.setSize(800,200);
        this.setLocationRelativeTo(null);
        this.setTitle("MySongList");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        //Here are the listeners listening for user clicks.
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
   
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                saveToFile();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                saveToFile();
            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AboutFrame();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AboutFrame();
            }
        });

        statsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StatisticsFrame();
            }
        });

        statsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StatisticsFrame();
            }
        });

    }

    //the exit app function. Used to exit the application whenever the user clicks on anything with the word "exit" on it
    public void exitApp(){
        int choice=JOptionPane.showConfirmDialog(MainFrame.this,"Do you want to exit the app? \n Any unsaved changes will be lost");
        if (choice==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    //Save the song information to the hardcoded file.
    //First checks if any of the fields are empty.
    //If its not empty it reads the whole file to determine if the song has already been placed.
    //If the song isnt already in the file it writes it in.
    public void saveToFile(){
        String title=titleTf.getText().trim();
        String year=yearTf.getText().trim();
        String length=lengthTf.getText().trim();
        String artist=artistTf.getText().trim();
        String genre=genreCb.getSelectedItem().toString().trim();

        if(title.isEmpty() || year.isEmpty() || length.isEmpty() || artist.isEmpty()){
            JOptionPane.showMessageDialog(MainFrame.this, "A field is missing","Save error",JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                BufferedReader reader=new BufferedReader(new FileReader("songs.txt"));
                String line="";
                String[] token;
                boolean found=false;

                while(found==false && reader.ready()){
                    line=reader.readLine();
                    token=line.split("\t");

                    if(token.length==6){
                        if(title.equals(token[1]) && year.equals(token[2]) && artist.equals(token[4])){
                            JOptionPane.showMessageDialog(MainFrame.this, "This song has already been saved","Save error",JOptionPane.WARNING_MESSAGE);
                            found=true;
                        }
                   }
                }
                try{
                    if(found==false){
                        BufferedWriter writer=new BufferedWriter(new FileWriter("songs.txt",true));
                        String code=title.substring(0, Math.min(5, title.length()))+year;
                        writer.write(code+"\t"+title+"\t"+year+"\t"+length+"\t"+artist+"\t"+genre);
                        writer.newLine();
                        writer.close();
                        clearFields();
                    }
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(saveBtn, "Cant write to songs.txt","File input-output error",JOptionPane.ERROR_MESSAGE);
                }
            }catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(saveBtn,"Cant access songs.txt","File not found error",JOptionPane.ERROR_MESSAGE);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(saveBtn,"Cant write to songs.txt","File input-output error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //used for clearing the field when the user adds a song.
    public void clearFields(){
        titleTf.setText("");
        yearTf.setText("");
        lengthTf.setText("");
        artistTf.setText("");
    }
}
