package classes;

public class bodyPart{
    int x;
    int y;

    public bodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void increaseX(){
        x++;
        if (x>31){
            x = 0;
        }
    }

    public void increaseY(){
        y++;
        if (y>31){
            y = 0;
        }
    }

    public void decreaseX(){
        x--;
        if (x<0){
            x = 32;
        }
    }

    public void decreaseY(){
        y--;
        if (y<0){
            y = 32;
        }
    }
}