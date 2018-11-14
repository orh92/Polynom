package myMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
    	Polynom p=new Polynom ("0.2x^4-1.5x^3+3.0x^2-x-5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);       
        DataTable data = new DataTable(Double.class, Double.class);
        DataTable datac = new DataTable(Double.class, Double.class);
        double x;
        double y;
        for (x = -2.0; x <= 6.0; x+=0.009) {
        	 y  = p.f(x);
             data.add(x, y);
             //check if min point
            if ((p.f(x-0.01)>p.f(x)&&p.f(x)<p.f(x+0.01))) {
            	y = p.f(x);
        	datac.add(x, y);
        	System.out.println("minPoint: "+"("+x+","+y+")"); 
        	}
            //check if max point
            		if((p.f(x-0.01)<p.f(x)&&p.f(x)>p.f(x+0.01))) {
            	y = p.f(x);
            	datac.add(x, y);
            	System.out.println("maxPoint: "+"("+x+","+y+")");           	
            }
        }
              
        XYPlot plot = new XYPlot(data,datac);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.5f, 0.3f, 1.0f);
        Color colorc = new Color(1.0f, 0.0f, 0.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getPointRenderers(datac).get(0).setColor(colorc);
        plot.getLineRenderers(data).get(0).setColor(color);
    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
        Polynom p=new Polynom ("0.2x^4-1.5x^3+3.0x^2-x-5");
        System.out.println("area up than the x "+p.area(-2, 6, 0.01));
        System.out.println("area under the x "+p.areaUnderX(-2, 6, 0.01));
    }
}