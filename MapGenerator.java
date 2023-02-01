import java.awt.Color;
import java.awt.Graphics2D;
public class MapGenerator{
    public int map[][];//Map which wil contain all the bricks
    public int brickWidth;
    public int brickHeight;

    public int[][] letter_pos={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                               {0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,0,0,2,2,2,0,0,2,0},
                               {0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,2,0,0,2,0,2,0,0,2,0},
                               {0,2,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2,0,2,0,0,2,0},
                               {0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,2,0,0,2,0},
                               {0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,0,0,2,2,2,0,0,2,0},
                               {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};

    public MapGenerator (int row, int col)//Passing rows and columns 
    //to know how many row and column should be dedicated for a brick
    {
        map = new int[row][col];//2D array
        for(int i = 0; i<map.length; i++)//For rows
            for(int j =0; j<map[0].length; j++){//For columns{
                if(letter_pos[i][j]==2)
                    map[i][j]=2;
                else
                    map[i][j] = 1;
            }
        

        brickWidth = 540/col;//Setting width of brick
        brickHeight = 150/row;//Setting height of brick
    }

    public void draw(Graphics2D g)//Function to draw bricks
    {
        for(int i = 0; i<map.length; i++)//For rows
        {
            for(int j =0; j<map[0].length; j++)//For column
            {
                if(map[i][j] == 1)
                {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    // this is just to show separate brick, game can still run without it
                    //g.setStroke(new BasicStroke(3));
                    //g.setColor(Color.black);//Border colour of brick
                    //g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);//Draw Brick
                }
                if(map[i][j]==2){
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col)//To detect the 
    //intersection of ball with brick and then change that value
    {
        if(map[row][col]>0){
            map[row][col]--;
        }
        //map[row][col] = value;
    }
}