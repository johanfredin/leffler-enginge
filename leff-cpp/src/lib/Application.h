//
// Created by johan on 2026-05-07.
//

#ifndef LEFF_CPP_LEFFLERAPP_H
#define LEFF_CPP_LEFFLERAPP_H
#include <string>
#include <SDL3/SDL.h>

#include "Input.h"

namespace leffler {
    static constexpr unsigned DEFAULT_WINDOW_WIDTH = 800;
    static constexpr unsigned DEFAULT_WINDOW_HEIGHT = 600;
    static constexpr unsigned FPS = 1000 / 60;

    struct Color {
        uint8_t r, g, b, a;

        Color(uint8_t r, uint8_t g, uint8_t b, uint8_t a = 0);
        explicit Color(uint32_t rgba);
    };

    class Application {
    public:
        virtual ~Application();

        explicit Application(
            const std::string &title,
            int w_width = DEFAULT_WINDOW_WIDTH,
            int w_height = DEFAULT_WINDOW_HEIGHT,
            bool fullscreen = false,
            Color clear_color = {0, 0, 0, 0}
        );

        virtual void start();

        virtual void tick() = 0;

        virtual void render() = 0;

    protected:
        SDL_Window *m_window;
        SDL_Renderer *m_renderer;
        Color m_clear_color;
    private:
        virtual void destroy();
        Input m_input;
        const int m_window_width;
        const int m_window_height;
        bool m_fullscreen;
        bool m_running;
    };
}
#endif //LEFF_CPP_LEFFLERAPP_H
