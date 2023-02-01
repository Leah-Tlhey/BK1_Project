import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame obj=new JFrame();//obj is object of JFrame
        GamePlay gamePlay = new GamePlay();//gameplay is object of Gameplay

        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Breakout Ball");//Ttile of JFrame
        obj.setResizable(false);//Cannot resize the Frame
        obj.setVisible(true);//Set to be visible
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//To close the Frame once EXIT button is pressed
        obj.add(gamePlay);//Adding gameplay JPanel in JFrame
        obj.setVisible(true);//Set to be visible


        MusicPlayer player1 = new MusicPlayer
        ("C:\\First\\src\\background_music.wav");
            player1.setVolumn(6f).play();

    }

}
