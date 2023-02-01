import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;//Package for awt
import java.awt.event.ActionEvent;//Package for ActionListener
import java.awt.event.ActionListener;//Package for ActionListener
import java.awt.event.KeyEvent;//Package for KeyListener
import java.awt.event.KeyListener;//Package for Keylistener
public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;//False so that the game doesnt starts by itself
    private int score = 0;//Starting score

    private int totalBricks = 175+54;//Total number of bricks

    private Timer timer;
    private int delay=8;//Speed given to ball in ms

    private int playerX = 310;//Starting position of our slide
    private int playerY = 550;

    private int ballposX = 120;//Starting position of ball
    private int ballposY = 350;//Starting positon of ball
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public GamePlay()
    {
        map = new MapGenerator(7, 25);
        addKeyListener(this);
        setFocusable(true);//Sets the focusable state of this Component to true
        setFocusTraversalKeysEnabled(false);//Sets the focus traversal keys to not enabled.
        timer=new Timer(delay,this);//Timer object,this actionlistener is notified when the timer goes off
        timer.start();//Start timer
    }

    public void paint(Graphics g)//Graphics object g is used to draw different shapes
    {
        // background
        
        
        
        g.setColor(Color.black);//Sets Background color
        g.fillRect(1, 1, 692, 592);//To fill the following rectangle with black color

        // drawing map
        map.draw((Graphics2D) g);//It wil draw the mapie bricks and stuff

        // borders
        g.setColor(Color.yellow);//Sets border color to yellow
        g.fillRect(0, 0, 3, 592);//To fill the following rectangle with yellow color
        g.fillRect(0, 0, 692, 3);//To fill the following rectangle with yellow color
        g.fillRect(691, 0, 3, 592);//To fill the following rectangle with yellow color

        // the scores
        g.setColor(Color.white);//Sets the colour of scores to white
        g.setFont(new Font("serif",Font.BOLD, 25));//Sets  font to the serif and sets the other properties.
        g.drawString(""+score, 590,30);//Draws the score given by string, using serif font and white color.

        // the paddle
        g.setColor(Color.green);//Sets the padde colour to green
        g.fillRect(playerX, playerY, 100, 8);//To fill the paddle with green color

        // the ball
        g.setColor(Color.yellow);//Set the ball color to yellow
        g.fillOval(ballposX, ballposY, 20, 20);//To fill the ball with yellow color

        // when you won the game
        if(totalBricks <= 0)//When all the bricks are broken
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));//Sets this graphics context's font to the specified font.
            g.drawString("You Won", 260,300);

            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230,350);
        }

        // when you lose the game
        if(ballposY > 570)//If ball goes out of border from down
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Game Over, Scores: "+score, 190,300);

            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230,350);
        }

        g.dispose();//Disposes g and releases any system resources that it is using
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)//If RIGHT key is detected
        {
            if(playerX >= 600)//If it value goes above 600 it goes outside border so setting it to be 600 so it can go upto border
                playerX = 600;
            else
                moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)//IF LEFT key is detected
        {
            if(playerX < 10)
                playerX = 10;
            else
                moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)//IF LEFT key is detected
        {
            if(playerX < 450)
                playerY = 450;
            else
                moveUP();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)//IF LEFT key is detected
        {
            if(playerX >550)
                playerX = 550;
            else
                moveDOWN();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER)//If ENTER key is pressed
        {
            if(!play)//If game over then after that run this code
            {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);

                repaint();//Repaint or to keep updating the screen by calling the paint method
            }
        }
    }

    public void keyReleased(KeyEvent e) {}//Not needed
    public void keyTyped(KeyEvent e) {}//Not needed

    public void moveRight()
    {
        play = true;
        playerX+=40;//To move 20px to the right
    }
    public void moveLeft()
    {
        play = true;
        playerX-=40;//TO move 20px to the left
    }
    public void moveDOWN()
    {
        play = true;
        playerY+=20;//To move 20px to the donw
    }
    public void moveUP()
    {
        play = true;
        playerY-=20;//TO move 20px to the up
    }

    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)//If we pressed right/left key it would be true
        {
            

            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, playerY, 30, 8))){
                ballYdir = -ballYdir;ballXdir = -2;}
            else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, playerY, 30, 8))){
                ballYdir = -ballYdir;ballXdir = ballXdir + 1;}
            else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, playerY, 40, 8)))
                ballYdir = -ballYdir;
        
            // check map collision with the ball
           A:  for(int i = 0; i<map.map.length; i++){//map.map //first map is obj created in gameplay and 2nd map is to access the 2D array from mapgenerator
                for(int j =0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        //scores++;
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);//To detect the intersection between ball and brick
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect))
                        {
                            
                            MusicPlayer player = new MusicPlayer("C:\\First\\src\\breakbrick.wav");
                            player.setVolumn(6f).play();


                            map.setBrickValue(0, i, j);//If ball intersects the change value from 1 to 0 or 2 to 1
                            score+=5;
                            totalBricks--;
                            // when ball hit right or left of brick
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)
                                ballXdir = -ballXdir;
                            // when ball hits top or bottom of brick
                            else
                                ballYdir = -ballYdir;
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;//To check if ball is touching the border
            ballposY += ballYdir;//To check if ball is touching the border

            if(ballposX < 0)//For left border
                ballXdir = -ballXdir;
            if(ballposY < 0)//For top border
                ballYdir = -ballYdir;
            if(ballposX > 670)//For right border
                ballXdir = -ballXdir;

            repaint();//Repaint or to keep updating the screen by calling the paint method
        }
    }
}
