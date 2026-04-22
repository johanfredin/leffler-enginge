#include <stdbool.h>
#include "display.h"
#include "input.h"
#include <SDL2/SDL.h>

#include "vector.h"

#define NDEBUG
#define RADIUS 32
#define VEL 5

static bool is_running = false;
static uint32_t previous_frame_time = 0;
static float delta_time = 0;
static vec2_t pos = {};
static uint32_t color = 0x42A08000;

void setup(void) {
    is_running = initialize_window();
    pos = (vec2_t){120, 140};
}

void update(void) {
    if (input_quit()) {
        is_running = false;
    }

    if (input_up()) {
        pos.y -= VEL;
    } else if (input_down()) {
        pos.y += VEL;
    }
    if (input_left()) {
        pos.x -= VEL;
    } else if (input_right()) {
        pos.x += VEL;
    }



    // Wait some time until the reach the target frame time in milliseconds
    const int time_to_wait = FRAME_TARGET_TIME - (SDL_GetTicks() - previous_frame_time);

    // Only delay execution if we are running too fast
    if (time_to_wait > 0 && time_to_wait <= FRAME_TARGET_TIME) {
        SDL_Delay(time_to_wait);
    }

    // Get a delta time factor converted to seconds to be used to update our game objects
    delta_time = (SDL_GetTicks() - previous_frame_time) / 1000.0;

    previous_frame_time = SDL_GetTicks();
}

void render(void) {
    clear_color_buffer(0xFF000000);
    draw_grid();
    draw_circle(pos.x, pos.y, RADIUS, &color);
    render_color_buffer();
}

int main(void) {
    setup();
    while (is_running) {
        input_process();
        update();
        render();
    }
    destroy_window();

    return 0;
}
