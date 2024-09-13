package edu.eci.arsw.blueprints.services;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class RedundancyFilter implements Filter{

    private static RedundancyFilter filter;

    private RedundancyFilter(){}

    public static RedundancyFilter getFilter(){
        if(filter == null){
            filter = new RedundancyFilter();
        }
        return filter;
    }

    @Override
    public Blueprint filterPlain(Blueprint bp){
        List<Point> points = bp.getPoints();
        List<Point> newPoints = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++){
            Point currentPoint; 
            Point nextPoint;
            currentPoint = points.get(i);
            nextPoint = points.get(i + 1);
            if(!currentPoint.equals(nextPoint)){
                newPoints.add(currentPoint);
            }
        }
        bp.setPoints(newPoints);
        return bp;
    }

}