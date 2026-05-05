package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.geometry.Rectangle2f;
import se.fredin.leffler.engine.object.Player;

import java.util.List;

public class CollisionHandler {

    public void handleCollision(Player player, List<Rectangle2f> levelBounds) {
        for (var rect : levelBounds) {
            var bounds = player.bounds;
            if (bounds.overlaps(rect)) {
                var oldX = player.oldPos.x;
                var oldY = player.oldPos.y;
                var right = player.position.x + bounds.w;
                var bottom = player.position.y + player.h;
                var oldBottom = player.oldPos.y + player.h;
                var oldRight = player.oldPos.x + bounds.w;
                var rightBottom = rect.y + rect.h;

                if (right >= rect.x & oldRight <= rect.x) {                                          // From right
                    player.position.x = rect.x - player.w;
                } else if (player.position.x <= (rect.x + rect.w) & oldX >= (rect.x + rect.w)) {     // From left
                    player.position.x = rect.x + rect.w;
                } else if (bottom >= rect.y & oldBottom <= rect.y) {                                 // From above
                    player.position.y = rect.y - player.h;
                } else if (player.position.y <= rightBottom & oldY >= rightBottom) {                 // From below
                    player.position.y = rightBottom;
                }
            }
        }
    }

}
