package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.ArrayList;
import java.util.List;

public class RandomLevel implements MazeLevel {
    
    private final List<Line2D> walls;
    private final Spot start;
    private final Spot finish;
    
    public RandomLevel(final int width, final int height) {
        start = randomSpot(width, height);
        // FIX 15/08/12 It could be the same square !
        finish = randomSpot(width, height);
        final Vecchio vec = Hyena.hyena(width, height, start, finish);

        final List<Spot> vSpots = vec.vWalls();
        final List<Spot> hSpots = vec.hWalls();

        walls = new ArrayList<Line2D>();
        final List<Line2D> vWalls = Joiner.join(vSpots, new VWalls());
        final List<Line2D> hWalls = Joiner.join(hSpots, new HWalls());
        walls.addAll(vWalls);
        walls.addAll(hWalls);
//        final List<HWall> hWalls = Joiner.join(hSpots, new HWalls());



    }
    
    @Override
    public List<Line2D> walls() {
        return walls;        
    }

    @Override
    public Spot start() {
        return start;
    }

    @Override
    public Spot finish() {
        return finish;
    }

    private Spot randomSpot(final int width, final int height) {
        final int w = (int) (Math.random() * width);
        final int h = (int)(Math.random() * height);
        return new Spot(w, h);
    }
    
}