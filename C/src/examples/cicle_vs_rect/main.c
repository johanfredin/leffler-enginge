#include <stdbool.h>
#include <stdio.h>
#include "display.h"
#include <SDL2/SDL.h>

static bool is_running = false;
static uint32_t previous_frame_time = 0;
static float delta_time = 0;

static SDL_

void setup(void) {

}

void update(void) {
    // Wait some time until the reach the target frame time in milliseconds
    int time_to_wait = FRAME_TARGET_TIME - (SDL_GetTicks() - previous_frame_time);

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
    render_color_buffer();
}

int main(void) {
    is_running = initialize_window();
    setup();
    while (is_running) {
        update();
        render();
    }
    destroy_window();


    printf("Hello, World!\n");
    return 0;
}
