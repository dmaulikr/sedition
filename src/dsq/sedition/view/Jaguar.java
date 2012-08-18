package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.core.Game;

import javax.microedition.khronos.opengles.GL10;

public interface Jaguar {
    void onCommand(final Game game, float left, float top);
    void offCommand(final Game game);
    
    // FIX 18/08/12 Maybe use config later.
    void onCreate(final GL10 g, final Context context, final Game game);
    void onResize(final GL10 g, final int width, final int height, final Game game);
    void onDraw(final GL10 g, final int width, final int height, final Game game);
}