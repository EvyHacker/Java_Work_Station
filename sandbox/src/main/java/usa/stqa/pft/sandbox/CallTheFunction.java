package usa.stqa.pft.sandbox;

public class CallTheFunction {
    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);
        p1.distance(p2);
        System.out.println(("p1" + "(" + p1.x)+ ";" +(p1.y) + ")");
        //System.out.println("p1  = " + p1.y);
        System.out.println(("p2" + "(" + p2.x)+ ";" +(p2.y) + ")");
        System.out.println("Distance between two points = " + p1.distance(p2));

    }

}
