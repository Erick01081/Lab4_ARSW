package edu.eci.arsw.blueprints.services;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class SubsamplingFilter implements Filter{

    private static SubsamplingFilter filter;

    private SubsamplingFilter(){}

    public static SubsamplingFilter getFilter(){
        if(filter == null){
            filter = new SubsamplingFilter();
        }
        return filter;
    }

    @Override
    public Blueprint filterPlain(Blueprint bp){
        List<Point> points = bp.getPoints();
        List<Point> newPoints = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i += 2){
            newPoints.add(points.get(i));
        }
        bp.setPoints(newPoints);
        return bp;
    }

}