/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.util;

/**
 * A pair that stores two paired values.
 * @author Luan
 * 
 */
public class Pair<X, Y> 
{
    private X x;
    private Y y;
    
    /**
     * 
     * @param x any object.
     * @param y any object.
     */
    public Pair(X x, Y y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public X getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Y getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Y y) {
        this.y = y;
    }
    
    /**
    *@param x first parameter.
    *@param y second parameter.
    */
    public void setXY(X x, Y y)
    {
        this.x = x;
        this.y = y;
    }    
}
