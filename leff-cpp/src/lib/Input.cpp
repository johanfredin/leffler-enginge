//
// Created by johan on 2026-05-19.
//

#include "Input.h"

#include <SDL3/SDL_events.h>
#include "log.h"

namespace leffler {
    Input::Input(): m_up(0), m_down(0), m_left(0), m_right(0), m_quit(0) {}

    void Input::handle() {
        SDL_Event event;
        while (SDL_PollEvent(&event)) {
            switch (event.type) {
                case SDL_EVENT_QUIT:
                    m_quit = !m_quit;
                    break;
                case SDL_EVENT_KEY_DOWN:
                    if (event.key.key == SDLK_UP) {
                        m_up = 1;
                        log_debug("UP key pressed");
                    }
                    if (event.key.key == SDLK_DOWN) {
                        m_down = 1;
                        log_debug("DOWN key pressed");
                    }
                    if (event.key.key == SDLK_LEFT) {
                        m_left = 1;
                        log_debug("LEFT key pressed");
                    }
                    if (event.key.key == SDLK_RIGHT) {
                        m_right = 1;
                        log_debug("RIGHT key pressed");
                    }
                    break;
                case SDL_EVENT_KEY_UP:
                    if (event.key.key == SDLK_ESCAPE) {
                        m_quit = 1;
                    }
                    if (event.key.key == SDLK_UP) {
                        m_up = 0;
                    }
                    if (event.key.key == SDLK_DOWN) {
                        m_down = 0;
                    }
                    if (event.key.key == SDLK_LEFT) {
                        m_left = 0;
                    }
                    if (event.key.key == SDLK_RIGHT) {
                        m_right = 0;
                    }
                    break;
                case SDL_EVENT_MOUSE_BUTTON_DOWN:
                    if (event.button.button == SDL_BUTTON_LEFT) {
                        log_debug("Left Mouse button");
                    }
                    if (event.button.button == SDL_BUTTON_RIGHT) {
                        log_debug("Right Mouse button");
                    }
                default:
                    break;
            }
        }
    }
}
