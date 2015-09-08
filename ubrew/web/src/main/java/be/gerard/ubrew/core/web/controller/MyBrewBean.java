package be.gerard.ubrew.core.web.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.context.annotation.Scope;

//@ViewScoped
@Scope("session")
@Named
public class MyBrewBean implements Serializable {
    
    private LineChartModel lineModel;

    public LineChartModel getLineModel() {
        return lineModel;
    }
    
    @PostConstruct
    public void init() {
        createLineModels();
    }
    
    private void createLineModels() {
        lineModel = initCategoryModel();
        lineModel.setTitle("Category Chart");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Months"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("TODO");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries alcohol = new ChartSeries();
        alcohol.setLabel("Alcohol");
        alcohol.set("2004", 120);
        alcohol.set("2005", 100);
        alcohol.set("2006", 44);
        alcohol.set("2007", 150);
        alcohol.set("2008", 25);
 
        ChartSeries heat = new ChartSeries();
        heat.setLabel("Heat");
        heat.set("2004", 52);
        heat.set("2005", 60);
        heat.set("2006", 110);
        heat.set("2007", 90);
        heat.set("2008", 120);
 
        model.addSeries(alcohol);
        model.addSeries(heat);
         
        return model;
    }

}
