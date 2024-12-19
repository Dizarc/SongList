//Code By Omer-Faruk Yildiz ICE20390038.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

//Pretty straightforward. This is the About frame opens up when you click about on the MainFrame.
public class AboutFrame extends JFrame{
    private JLabel aboutLbl;
    private JButton exitBtn;

    public AboutFrame(){

        aboutLbl=new JLabel();
        exitBtn=new JButton("Exit");

        aboutLbl.setBorder(new EmptyBorder(0,10,0,10));
        aboutLbl.setText("<html><h4>MySongList app made by Omer-Faruk Yildiz ICE20390038<h4><br>"
                +"This application saves song names into a txt file. "
                +"Developed for the final project of the Java course in PADA <strong>2021-2022</strong></html>");

        this.add(aboutLbl,BorderLayout.PAGE_START);
        this.add(exitBtn,BorderLayout.PAGE_END);

        this.setSize(400,200);
        this.setTitle("About");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                hide();
            }   
        });
    }


}
