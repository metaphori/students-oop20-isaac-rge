/*
 *   V2d.java
 *
 * Copyright 2000-2001-2002  aliCE team at deis.unibo.it
 *
 * This software is the proprietary information of deis.unibo.it
 * Use is subject to license terms.
 *
 */
package ryleh.common;

/**
 *
 * 2-dimensional vector
 * objects are completely state-less
 *
 */
public class V2d implements java.io.Serializable {

    public double x,y;
    
    public V2d(double x,double y){
        this.x=x;
        this.y=y;
    }

    public V2d(P2d to, P2d from){
        this.x=to.x-from.x;
        this.y=to.y-from.y;
    }

    public V2d sum(V2d v){
        return new V2d(x+v.x,y+v.y);
    }
    
    public V2d sub(V2d v){
    	return new V2d(x-v.x,y-v.y);
    }

    public double module(){
        return (double)Math.sqrt(x*x+y*y);
    }

    public V2d getNormalized(){
        double module=(double)Math.sqrt(x*x+y*y);
        return new V2d(x/module,y/module);
    }

    public V2d mul(double fact){
        return new V2d(x*fact,y*fact);
    }
    public static V2d fromAngle(double degrees) {
        return new V2d(GameMath.cosDeg((float)degrees), GameMath.sinDeg((float)degrees));
    }
    public String toString(){
        return "V2d("+x+","+y+")";
    }
    public V2d mulLocal(double a) {
        this.x *= a;
        this.y *= a;
        return new V2d(this.x, this.y);
    }
    public V2d addLocal(V2d v) {
        this.x += v.x;
        this.y += v.y;
        return new V2d(this.x, this.y);
    }
}
