#define NDEBUG
#include "Application.h"


class MyApp: public leffler::Application {
public:
    using Application::Application;

    void init() override {
        SDL_SetRenderScale(m_renderer, 4.0f, 4.0f);
    }

    void render() override {
        SDL_SetRenderDrawColor(m_renderer, 0xFF, 0xFF, 0xFF, 0xFF);
        SDL_RenderDebugText(m_renderer, 60, 60, "Hello World");
    }
    void tick() override {
        // Nothing needed
    }
};

int main() {
    MyApp app("Hello World", 800, 600, false, {128, 50, 35});
    app.start();
}