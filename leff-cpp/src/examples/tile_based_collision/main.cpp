#define NDEBUG
#include "Application.h"
#include "Shape.h"


namespace {
    class MyApp : public leffler::Application {
        static constexpr float VELOCITY = .66f;
        static constexpr Uint8 TILE_SIZE = 1;

        struct Player {
            leffler::Vec2 vel;
            leffler::Rect bounds;
        };

        Player m_player{
            .vel = {0.0f, 0.0f},
            .bounds{
                {10.0f, 10.0f},
                TILE_SIZE,
                TILE_SIZE,
                {0xFF, 0xFF, 0xFF}
            }
        };

    public:
        using Application::Application;

        void init() override {
            SDL_SetRenderScale(m_renderer, 8.0, 8.0);
        }

        void render() override {
            m_player.bounds.draw(m_renderer);
        }

        void tick() override {
            m_player.vel = {0.0f, 0.0f};

            if (m_input.m_up) {
                m_player.vel.y = -VELOCITY;
            } else if (m_input.m_down) {
                m_player.vel.y = VELOCITY;
            }

            if (m_input.m_left) {
                m_player.vel.x = -VELOCITY;
            } else if (m_input.m_right) {
                m_player.vel.x = VELOCITY;
            }

            m_player.bounds.pos += m_player.vel;
        }
    };
}

int main() {
    MyApp app("Circle VS Rect Collision", 320, 240, false, {128, 50, 35});
    app.start();
}
