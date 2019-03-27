import java.awt.BasicStroke;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Wykresy {

   public void draw_chart(String file, ArrayList<Long> best, ArrayList<Long> worst, ArrayList<Long> average) throws IOException {
      DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
      
      int generations = best.size();
      
      for(int i=0; i<worst.size(); i++) {
    	  line_chart_dataset.addValue( worst.get(i) , "worst" , Integer.toString(i+1));
      }
      
      for(int i=0; i<average.size(); i++) {
    	  line_chart_dataset.addValue( average.get(i) , "average" , Integer.toString(i+1));
      }
      
      for(int i=0; i<best.size(); i++) {
    	  line_chart_dataset.addValue( best.get(i) , "best" , Integer.toString(i+1));
      }

      JFreeChart lineChartObject = ChartFactory.createLineChart(
         file,"generation",
         "G function value",
         line_chart_dataset,PlotOrientation.VERTICAL,
         true,true,false);

      int width = 960;    /* Width of the image */
      int height = 720;   /* Height of the image */ 
      File lineChart = new File( "wykresy/"+file+".jpeg" );
      BasicStroke stroke = new BasicStroke(2.5f);
      final CategoryPlot plot = lineChartObject.getCategoryPlot();
      int transparency = 95;
      CategoryItemRenderer renderer = plot.getRenderer();;
      renderer.setSeriesOutlinePaint(0, Color.black);
      renderer.setSeriesStroke(0, stroke);
      renderer.setSeriesStroke(1, stroke);
      renderer.setSeriesStroke(2, stroke);
      
    //  CategoryAxis xAxis = plot.getDomainAxis();
      //xAxis.setTickUnit(new NumberTickUnit(10));
      //plot.setDomainAxis(xAxis);
   //   rangeAxis.setTickUnit(new NumberTickUnit(10));
      ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
   }
}