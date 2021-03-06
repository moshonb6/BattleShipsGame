package Project.modules;

import java.awt.*;

//enum Direction{ROW,COLUMN}

public class BattelShip {
    private char type;
    private Point position;
    private String direction;
    private int length;
    private int shipValue;
    private boolean isAlive;

    public BattelShip(char i_type, Point i_position, String i_direction, int i_length, int i_shipValue){
        type = i_type;
        position = i_position;
        direction = i_direction;
        length = i_length;
        shipValue = i_shipValue;
        isAlive = true;
    }

    public int getShipValue(){
        return shipValue;
    }
    public int getLength(){ return length; }
    public String getDirection(){
        return direction;
    }
    public Point getPosition(){
        return position;
    }
}
