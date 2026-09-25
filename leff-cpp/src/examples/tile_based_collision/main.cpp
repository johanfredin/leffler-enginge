#define NDEBUG
#include "Application.h"
#include "Shape.h"


namespace {
    class MyApp : public leffler::Application {
        static constexpr float VELOCITY = 2.66f;
        static constexpr Uint8 TILE_SIZE = 16;

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

        const std::string m_str_map = "00000000\n"
                "10000001\n"
                "10000001\n"
                "00011000\n"
                "00011000\n"
                "00000000\n"
                "11110011\n"
                "00000011\n";

        std::vector<leffler::Rect> m_map;

    public:
        using Application::Application;

        void init() override {
            size_t cols = 0;
            size_t rows = 0;
            for (const char c: m_str_map) {
                if (c == '1') {
                    leffler::Rect collision_tile{
                        {
                            static_cast<float>(cols * TILE_SIZE),
                            static_cast<float>(rows * TILE_SIZE),
                        },
                        TILE_SIZE, TILE_SIZE, {0xFF, 0, 0}
                    };
                    m_map.push_back(collision_tile);
                } else if (c == '\n') {
                    rows += 1;
                    cols = 0;
                } else {
                    cols += 1;
                }
            }
        }

        void render() override {
            m_player.bounds.draw(m_renderer);
            for (leffler::Rect collision_tile: m_map) {
                collision_tile.draw(m_renderer);
            }
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
    MyApp app("Circle VS Rect Collision", 320, 240, false, {0, 0, 0x55});
    app.start();
}
