#define NDEBUG
#include "Application.h"
#include "Shape.h"

static constexpr float VELOCITY = 10.0f;

namespace {
    class MyApp : public leffler::Application {
        leffler::Rect rect{
            {350.0f, 250.0f},
            128.0f,
            128.0f,
            {0xFF, 0xFF, 0xFF}
        };
        leffler::Circle circle{
            {120.0f, 120.0f},
            25.0f,
            {0x0F, 0xF0, 0xFF}
        };

    public:
        using Application::Application;

        void init() override {
        }

        void render() override {
            rect.draw(m_renderer);
            circle.draw(m_renderer);
        }

        void tick() override {
            circle.vel.x = 0;
            circle.vel.y = 0;

            if (m_input.m_up) circle.vel.y = -VELOCITY;
            else if (m_input.m_down) circle.vel.y = VELOCITY;
            if (m_input.m_left) circle.vel.x = -VELOCITY;
            else if (m_input.m_right) circle.vel.x = VELOCITY;

            circle.tick();
        }
    };
}

int main() {
    MyApp app("Circle VS Rect Collision", 800, 600, false, {128, 50, 35});
    app.start();
}
