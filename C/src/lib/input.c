//
// Created by johan on 2026-04-22.
//

#include "input.h"

#include <stdint.h>
#include <SDL2/SDL_events.h>

typedef struct Input {
    uint8_t up: 1;
    uint8_t down: 1;
    uint8_t left: 1;
    uint8_t right: 1;
    uint8_t quit: 1;
    uint8_t pad: 3;
} Input;

static Input input;

bool input_up(void) {
    return input.up;
}

bool input_down(void) {
    return input.down;
}

bool input_left(void) {
    return input.left;
}

bool input_right(void) {
    return input.right;
}

bool input_quit(void) {
    return input.quit;
}

void input_process(void) {
    SDL_Event event;
    while(SDL_PollEvent(&event)) {
        switch (event.type) {
            case SDL_QUIT:
                input.quit = !input.quit;
                break;
            case SDL_KEYDOWN:
                input.up = event.key.keysym.sym == SDLK_UP;
                input.down = event.key.keysym.sym == SDLK_DOWN;
                input.left = event.key.keysym.sym == SDLK_LEFT;
                input.right = event.key.keysym.sym == SDLK_RIGHT;
                break;
            case SDL_KEYUP:
                input.quit = event.key.keysym.sym == SDLK_ESCAPE;
                break;
            default:
                break;
        }
    }
}
