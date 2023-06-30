package snakenew;

public enum Move {
    U(-1,0),
    D(1,0),
    L(0,-1),
    R(0,1);
    int x;
    int y;
    Move(int i, int j) {
        x=i;
        y=j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
