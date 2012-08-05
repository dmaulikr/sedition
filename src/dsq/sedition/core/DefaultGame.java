package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.event.EventListener;
import dsq.sedition.scene.GameCamera;
import dsq.sedition.scene.Camera;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.scene.GlimpseCamera;

import java.util.List;

public class DefaultGame implements Game {

    public static final float ZOOM_OUT_RATE = 0.001f;
    private final MutablePlayer player;
    private final GlimpseCamera camera;
    
    private ViewState viewState = ViewState.TOP;
    private final Level level;

    // FIX 9/06/12 Clean this up.
    public DefaultGame(final Level level, final EventListener events) {
        this.level = level;
        player = new DefaultPlayer(level.start(), 0f, events);
        camera = new GameCamera(player);
    }

    @Override
    public void turnLeft() {
        player.setTurnRate(-2f);
    }

    @Override
    public void turnRight() {
        player.setTurnRate(2f);
    }

    @Override
    public void speedUp() {
        player.setAcceleration(0.003f);
    }

    @Override
    public void slowDown() {
        player.setAcceleration(-0.002f);
    }

    @Override
    public void holdPace() {
        player.setAcceleration(-0.0001f);
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public Camera camera() {
        return camera;
    }

    @Override
    public List<Sprite> sprites() {
        return level.sprites(viewState);
    }

    @Override
    public List<Sprite> allSprites() {
        return level.allSprites();
    }

    @Override
    public void update() {
        // FIX 11/06/12 A better approach for this dual state would be better. Dare I say fold?
        if (viewState == ViewState.TOP) {
            final float newScale = camera.getScale() - ZOOM_OUT_RATE;
            camera.setScale(newScale);
            if (newScale < 0.4) {
                camera.transition();
                viewState = ViewState.PLAYER;
            }
        } else {
            final List<Collidable> obstacles = level.obstacles(viewState);
            player.update(obstacles);
        }
    }

    @Override
    public void stopTurning() {
        player.setTurnRate(0);
    }

}