//Code By Omer-Faruk Yildiz ICE20390038.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


//This is the statistics frame. Opens up when you want to see statistics of the songs in the list.
public class StatisticsFrame extends JFrame{

    private JLabel totalSongsLbl;
    private JLabel maxGenreSongsLbl;
    private JLabel maxDurSongLbl;
    private JLabel minDurSongLbl;

    private JButton exitBtn;

    private Box box;

    private ArrayList<Song> songList= new ArrayList();

    Song max;
    Song min;

    int genreCounts[]=new int[4];
    int maxCount;

    public StatisticsFrame(){
        
        insertIntoList();

        if(!songList.isEmpty()){
            totalSongsLbl=new JLabel("Total songs: "+ songList.size()+"\n");

            maxGenreSongsLbl=new JLabel("genre: "+genreMaxSongs() +" has the most songs: "+maxCount);

            max=maxDurSong();
            maxDurSongLbl=new JLabel("Maximum duration song: "+max.getTitle()+" with: "+max.getLength());

            min=minDurSong();
            minDurSongLbl=new JLabel("Minimum duration song: "+min.getTitle()+" with: "+min.getLength());

            box=Box.createVerticalBox();      
            box.add(totalSongsLbl);
            box.add(maxGenreSongsLbl);        
            box.add(maxDurSongLbl);
            box.add(minDurSongLbl);       
        
            exitBtn=new JButton("Exit");    

            this.add(box);
            this.add(exitBtn,BorderLayout.PAGE_END);

            this.setSize(400,200);
            this.setTitle("Statistics");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);

            exitBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    hide();
                }   
            });
        }else
            JOptionPane.showMessageDialog(StatisticsFrame.this, "File is empty","File error",JOptionPane.WARNING_MESSAGE);      
    }   

//Inserts every song from the file into an ArrayList of type Song. 
    public void insertIntoList(){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("songs.txt"));
            songList.clear();
            String line="";
            String[] token;
            Song song;

            while(reader.ready()){
                line=reader.readLine();
                token=line.split("\t");

                if(token.length==6){
                    song=new Song(token[0],token[1],Integer.parseInt(token[2]),Integer.parseInt(token[3]),token[4],token[5]);
                    songList.add(song);                        
                }
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(StatisticsFrame.this,"Cant access songs.txt","File not found error",JOptionPane.ERROR_MESSAGE);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(StatisticsFrame.this, "Cant write to songs.txt","File input-output error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Find the max of every genre and insert them into an array. After check which one has the biggest number and return the string genre.
    public String genreMaxSongs(){
        int countRock=0,countRap=0,countPop=0,countHipHop=0;
        for(int i=0;i<songList.size();i++){
            if(songList.get(i).getGenre().equals("Rock"))
                countRock++;
            else if(songList.get(i).getGenre().equals("Rap"))
                countRap++;
            else if(songList.get(i).getGenre().equals("Pop"))
                countPop++;
            else if(songList.get(i).getGenre().equals("Hip-Hop"))
                countHipHop++;        
        }
        genreCounts[0]=countRock;
        genreCounts[1]=countRap;
        genreCounts[2]=countPop;
        genreCounts[3]=countHipHop;

        maxCount=Arrays.stream(genreCounts).max().getAsInt();
        if(maxCount==genreCounts[0])
            return "Rock";
        else if(maxCount==genreCounts[1])
            return "Rap";
        else if(maxCount==genreCounts[2])
            return "Pop";
        else if(maxCount==genreCounts[3])
            return "Hip-Hop";
    return "";
    }

    //find the song with the biggest duration.
    public Song maxDurSong(){
        int maximum=songList.get(0).getLength();
        int pos=0;

        for(int i=1;i<songList.size();i++){
            if(maximum<songList.get(i).getLength()){
                maximum=songList.get(i).getLength();
                pos=i;
            }
        }
        return songList.get(pos);
    }

    //find the song with the smallest duration.
    public Song minDurSong(){
        int minimum=songList.get(0).getLength();
        int pos=0;

        for(int i=1;i<songList.size();i++){
            if(minimum>songList.get(i).getLength()){
            minimum=songList.get(i).getLength();
            pos=i;
            }
        }
        return songList.get(pos);
    }

}


