package view;

import java.awt.*;
import java.awt.geom.Line2D;

public class TopViewGUI extends ViewGUI {

    private Rectangle runwayRec;
    private Line2D centreLine;
    private Polygon clearArea;
    private Line2D[] sideLines;
    private Rectangle obstacleRec;
    
    public void init() {
        setBackground(new Color(51, 204, 51));
        clearArea = new Polygon();
        runwayRec = new Rectangle();
        toraString = "TORA";
        todaString = "TODA";
        asdaString = "ASDA";
        ldaString = "LDA";
    }


    private int rescaleVertical(int original){
        return (int) ((float) (original)/runwayWidth*runwayRec.getHeight());
    }

    protected int rescaleHorizontal(int original){
        return  (int) ((float) (original)/runwayLength*(getWidth()*(0.83 - 0.17)));
    }

    public Rectangle getObstacleRec(){
        return obstacleRec;
    }

    public double[] rotateandzoom(double orientation,double x,double y,double zoom)
    {
    	
    	orientation = (Math.PI * 2) - orientation;
    	//makes the middle 0
    	x = x - getWidth()/2;
    	y = y - getHeight()/2;
    	double newx =  ((x * Math.cos(orientation)) - (y * Math.sin(orientation))) + getWidth()/2;
    	double newy = ((x * Math.sin(orientation)) + (y * Math.cos(orientation))) + getHeight()/2;
    	double[] rotated = {newx,newy};
    	return rotated;
    }
    
    @Override
    public void redrawDistances(int toda, int tora, int lda, int asda) {
  
    	double zoom = 1;
        super.redrawDistances(toda, tora, lda, asda);
        double orientation = Math.PI /4;
        int height = getHeight();
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        //TODO this part is confusing what does it do
        if (obstacleOnRunway){
            if(obstacleRec.getCenterX() > width/2) {
                toraLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.75), (float) x[1], (float) (height / 1.75));
                todaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 2.4), (float) x[1] , (float) (height / 2.4));
                asdaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.65), (float) x[1], (float) (height / 1.65));
                ldaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 2.7), (float) x[1] + thresholdDistance, (float) (height / 2.7));
            }
            else {
                toraLine = new Line2D.Float((float) x[4], (float) (height / 1.75), (float) obstacleRec.getMaxX(), (float) (height / 1.75));
                todaLine = new Line2D.Float((float) width, (float) (height / 2.4), (float) obstacleRec.getMaxX(), (float) (height / 2.4));
                asdaLine = new Line2D.Float((float) (width*0.95), (float) (height / 1.65), (float) obstacleRec.getMaxX(), (float) (height / 1.65));
                if (obstacleRec.getMaxX() > x[1] +thresholdDistance) {
                    ldaLine = new Line2D.Float((float) x[4], (float) (height / 2.7), (float) obstacleRec.getMaxX(), (float) (height / 2.7));
                }
                else {
                    ldaLine = new Line2D.Float((float) x[4], (float) (height / 2.7), (float) x[1]+thresholdDistance, (float) (height / 2.7));
                }
            }
        }
        else {
            toraLine = new Line2D.Float((float) rotateandzoom(orientation,x[4],(height / 1.75),zoom)[0], (float) rotateandzoom(orientation,x[4],(height / 1.75),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 1.75),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 1.75),zoom)[1]);
            todaLine = new Line2D.Float((float) rotateandzoom(orientation,width,(height / 2.4),zoom)[0], (float) rotateandzoom(orientation,width,(height / 2.4),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 2.4),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 2.4),zoom)[1]);
            asdaLine = new Line2D.Float((float) rotateandzoom(orientation,(width*0.95),(height / 1.65),zoom)[0], (float) rotateandzoom(orientation,(width*0.95),(height / 1.65),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 1.65),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 1.65),zoom)[1]);
            ldaLine = new Line2D.Float((float) rotateandzoom(orientation,x[4],(height / 2.7),zoom)[0], (float) rotateandzoom(orientation,x[4],(height / 2.7),zoom)[1], (float) rotateandzoom(orientation,x[1] + thresholdDistance,(height / 2.7),zoom)[0], (float) rotateandzoom(orientation,x[1] + thresholdDistance,(height / 2.7),zoom)[1]);
        }
        
    }

    public void redrawView() {
        double orientation = Math.PI /4;
        double zoom = 1;
        clearArea.reset();
        int height = getHeight();
        int[] y = {(int) (height * 0.3), (int) (height * 0.2), (int) (height * 0.7), (int) (height * 0.8)};
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        clearArea.addPoint((int)rotateandzoom(orientation,x[0],y[0],zoom)[0],(int)rotateandzoom(orientation,x[0],y[0],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[1],y[0],zoom)[0],(int)rotateandzoom(orientation,x[1],y[0],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[2],y[1],zoom)[0],(int)rotateandzoom(orientation,x[2],y[1],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[3],y[1],zoom)[0],(int)rotateandzoom(orientation,x[3],y[1],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[4],y[0],zoom)[0],(int)rotateandzoom(orientation,x[4],y[0],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[5],y[0],zoom)[0],(int)rotateandzoom(orientation,x[5],y[0],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[5],y[2],zoom)[0],(int)rotateandzoom(orientation,x[5],y[2],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[4],y[2],zoom)[0],(int)rotateandzoom(orientation,x[4],y[2],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[3],y[3],zoom)[0],(int)rotateandzoom(orientation,x[3],y[3],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[2],y[3],zoom)[0],(int)rotateandzoom(orientation,x[2],y[3],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[1],y[2],zoom)[0],(int)rotateandzoom(orientation,x[1],y[2],zoom)[1] );
        clearArea.addPoint((int)rotateandzoom(orientation,x[0],y[2],zoom)[0],(int)rotateandzoom(orientation,x[0],y[2],zoom)[1] );
        //TODO rectangles don work
        runwayRec = new Rectangle((int) (0.05 * width), (int) (0.45 * height), (int) (0.9 * width), (int) (0.10 * height));
        centreLine = new Line2D.Float((float) rotateandzoom(orientation,x[2],(height / 2),zoom)[0], (float) rotateandzoom(orientation,x[2],(height / 2),zoom)[1], (float) rotateandzoom(orientation,x[3],(height / 2),zoom)[0], (float) rotateandzoom(orientation,x[3],(height / 2),zoom)[1]);
        toraLine = new Line2D.Float((float) rotateandzoom(orientation,x[4],(height / 1.75),zoom)[0], (float) rotateandzoom(orientation,x[4],(height / 1.75),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 1.75),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 1.75),zoom)[1]);
        todaLine = new Line2D.Float((float) rotateandzoom(orientation,width,(height / 2.4),zoom)[0], (float) rotateandzoom(orientation,width,(height / 2.4),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 2.4),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 2.4),zoom)[1]);
        asdaLine = new Line2D.Float((float) rotateandzoom(orientation,(width*0.95),(height / 1.65),zoom)[0], (float) rotateandzoom(orientation,(width*0.95),(height / 1.65),zoom)[1], (float) rotateandzoom(orientation,x[1],(height / 1.65),zoom)[0], (float) rotateandzoom(orientation,x[1],(height / 1.65),zoom)[1]);
        ldaLine = new Line2D.Float((float) rotateandzoom(orientation,x[4],(height / 2.7),zoom)[0], (float) rotateandzoom(orientation,x[4],(height / 2.7),zoom)[1], (float) rotateandzoom(orientation,x[1] + thresholdDistance,(height / 2.7),zoom)[0], (float) rotateandzoom(orientation,x[1] + thresholdDistance,(height / 2.7),zoom)[1]);
        sideLines = new Line2D[20];
        double halfway = height / 2;
        for (int i = 0; i < 10; i++) {
            double ypoint = halfway - 18 + i * 4;
            double xpoint = (width * 0.12);
            sideLines[i] = new Line2D.Double(rotateandzoom(orientation,xpoint,ypoint,zoom)[0], rotateandzoom(orientation,xpoint,ypoint,zoom)[1], rotateandzoom(orientation,x[1],ypoint,zoom)[0], rotateandzoom(orientation,x[1],ypoint,zoom)[1]);
            sideLines[i + 10] = new Line2D.Double(rotateandzoom(orientation,x[4],ypoint,zoom)[0], rotateandzoom(orientation,x[4],ypoint,zoom)[1], rotateandzoom(orientation,width - xpoint,ypoint,zoom)[0], rotateandzoom(orientation,width - xpoint,ypoint,zoom)[1]);
        }
    }


    @Override
    public void removeObstacle() {
        obstacleOnRunway = false;
    }

    //TODO Rectangles dont work
    @Override
    public void addObstacle(int width, int length, int height, int dFromT, int dFromCL) {
        obstacleOnRunway = true;
        dFromT = (int) (getWidth()*0.17) + thresholdDistance + rescaleHorizontal(dFromT);
        width = Math.max(rescaleVertical(width), 10);
        length = Math.max(rescaleHorizontal(length), 10);
        dFromCL = getHeight()/2 + rescaleVertical(dFromCL);
        obstacleRec = new Rectangle(dFromT,dFromCL,length, width);
        repaint();
    }

    private static void drawRunwayName(String name, double rotation, int x, int y, Graphics g){
        Graphics2D g2d =  (Graphics2D) g.create();
        g2d.rotate(rotation);
        g2d.setColor(Color.white);
        g2d.setFont(MainPageGUI.displayFont);
        int newX = (int) (x*Math.cos(rotation) + y*Math.sin(rotation));
        int  newY = (int) (-x*Math.sin(rotation) + y*Math.cos(rotation));
        g2d.drawString(name,newX,newY);
        g2d.dispose();
    }

    public void paint(Graphics g) {
        super.paint(g);
        double orientation = Math.PI /4;
        double zoom = 1;
        fillShape(new Color(141, 44, 159), g, clearArea);
        outlineShape(Color.black, g, clearArea, outline);
        fillShape(Color.gray, g, runwayRec);
        outlineShape(Color.black, g, runwayRec, outline);
        outlineShape(Color.white, g, centreLine, dashed);
        if (action == TAKEOFF) {
            outlineShape(Color.blue, g, toraLine, outline);
            outlineShape(Color.blue, g, todaLine, outline);
            outlineShape(Color.orange, g, asdaLine, outline);
            g.drawString(toraString, (int) toraLine.getX2(), (int) toraLine.getY2() + 15);
            g.drawString(todaString, (int) todaLine.getX2(), (int) todaLine.getY2() + 15);
            g.drawString(asdaString, (int) asdaLine.getX2(), (int) asdaLine.getY2() + 15);

        }
        if (action == LANDING) {
            outlineShape(Color.orange, g, ldaLine, outline);
            g.drawString(ldaString, (int) ldaLine.getX2(), (int) ldaLine.getY2() + 15);
        }
        for (Line2D line: sideLines){
            outlineShape(Color.white, g, line, outline);
        }
        if (obstacleOnRunway){
            fillShape(Color.black,g,obstacleRec);
            
        }

        drawRunwayName(name,orientation,(int)rotateandzoom(orientation,getWidth()/5,getHeight()/2 - 18,zoom)[0],(int)rotateandzoom(orientation,getWidth()/5,getHeight()/2 - 18,zoom)[1], g);
        drawRunwayName(inverseName, orientation,(int)rotateandzoom(orientation,4 * getWidth()/5,getHeight()/2 + 18,zoom)[0], (int)rotateandzoom(orientation,4 * getWidth()/5,getHeight()/2 + 18,zoom)[1], g);
    }
}
