//
// Created by johan on 2026-05-07.
//

#include "Application.h"

#include "log.h"

namespace leffler {

    static Uint64 delta_time = 0;
    static Uint64 previous_frame_time = 0;

    static void init_subsystem() {
        if (SDL_InitSubSystem(SDL_INIT_VIDEO | SDL_INIT_AUDIO | SDL_INIT_EVENTS)) {
            log_info("SDL Initialized");
            return;
        }
        log_err("Could not init SDL. Error: %s", SDL_GetError());
        exit(1);
    }

    static SDL_Window *create_window(const std::string &title, const int width, const int height, const bool fullscreen) {
        Uint64 flags = SDL_WINDOW_RESIZABLE;
        if (fullscreen) {
            flags |= SDL_WINDOW_FULLSCREEN;
        }

        SDL_Window *window = SDL_CreateWindow(title.c_str(), width, height, flags);
        if (!window) {
            log_err("Could not initialize window. Error: %s", SDL_GetError());
            exit(1);
        }
        SDL_SetWindowPosition(window, SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED);
        SDL_SyncWindow(window);
        return window;
    };

    static SDL_Renderer *create_renderer(SDL_Window *window) {
        SDL_Renderer *renderer = SDL_CreateRenderer(window, nullptr);
        if (!renderer) {
            log_err("Could not initialize renderer. Error: %s", SDL_GetError());
            exit(1);
        }
        return renderer;
    }

    static inline void handle_delta_time() {
        // Wait some time until the reach the target frame time in milliseconds

        // Only delay execution if we are running too fast
        if (const Uint64 time_to_wait = FPS - (SDL_GetTicks() - previous_frame_time); time_to_wait > 0 && time_to_wait <= FPS) {
            SDL_Delay(time_to_wait);
        }

        // Get a delta time factor converted to seconds to be used to update our game objects
        delta_time = (SDL_GetTicks() - previous_frame_time) / 1000;

        previous_frame_time = SDL_GetTicks();

    }

    Application::Application(
        const std::string &title,
        const int w_width,
        const int w_height,
        const bool fullscreen,
        const Color clear_color) : m_clear_color(clear_color),
                                    m_window_width(w_width),
                                    m_window_height(w_height),
                                    m_fullscreen(fullscreen) {

        // Init SDL
        init_subsystem();

        // Init window and renderer
        m_window = create_window(title, m_window_width, m_window_height, fullscreen);
        m_renderer = create_renderer(m_window);
        m_running = true;
    }

    void Application::start()  {
        init();
        while (m_running && !m_input.m_quit) {
            m_input.handle();
            tick();
            handle_delta_time();
            SDL_SetRenderDrawColor(m_renderer, m_clear_color.r, m_clear_color.g, m_clear_color.b, m_clear_color.a);
            SDL_RenderClear(m_renderer);
            render();
            SDL_RenderPresent(m_renderer);
        }
        destroy();
    }

    Application::~Application() {
        Application::destroy();
    }

    void Application::destroy() {
        SDL_DestroyRenderer(m_renderer);
        SDL_DestroyWindow(m_window);
        SDL_Quit();
    }

    Color::Color(const uint8_t r, const uint8_t g, const uint8_t b, const uint8_t a) : r(r), g(g), b(b), a(a) {}

    Color::Color(const uint32_t rgba) {
        r = rgba >> 24;
        g = rgba >> 16;
        b = rgba >> 8;
        a = rgba;
    }
}
