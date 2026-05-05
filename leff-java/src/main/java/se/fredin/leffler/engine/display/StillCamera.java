package se.fredin.leffler.engine.display;

import se.fredin.leffler.engine.geometry.Camera;

public class StillCamera extends Camera {

    public StillCamera() {
        super(0, 0, 0, 0, null);
    }

    @Override
    public void tick(float deltaTime) {
        // Do nothing
    }


}
