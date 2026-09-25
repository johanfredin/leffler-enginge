//
// Created by johan on 2026-06-13.
//

#include "Shape.h"

namespace leffler {

    static void set_color(SDL_Renderer *renderer, const Color &color) {
        SDL_SetRenderDrawColor(renderer, color.r, color.g, color.b, color.a);
    }

    Shape::Shape(const Vec2 pos, const Color color) : pos(pos), color(color) {
    }

    Rect::Rect(const Vec2 pos, const float w, const float h, const Color color) : Shape(pos, color), w(w), h(h) {
        this->rect = {.x = pos.x, .y = pos.y, .w = w, .h = h};
    }

    Circle::Circle(const Vec2 pos, const float radius, const Color color) : Shape(pos, color), radius(radius) {
        const float radius_squared = radius * radius;
        for (float x = -radius; x <= radius; x += 1.0f) {
            for (float y = -radius; y <= radius; y++) {
                if (((x * x) + (y * y)) <= radius_squared) {
                    points.push_back({x + pos.x, y + pos.y});
                }
            }
        }
    }

    void Rect::draw(SDL_Renderer *renderer) {
        set_color(renderer, color);
        rect = {.x = pos.x, .y = pos.y, .w = w, .h = h};
        SDL_RenderFillRect(renderer, &rect);
    }

    void Circle::draw(SDL_Renderer *renderer) {
        set_color(renderer, color);
        SDL_RenderPoints(renderer, points.data(), static_cast<int>(points.size()));
    }

    void Circle::tick() {
        for (auto &[x, y] : points) {
            x += vel.x;
            y += vel.y;
        }
    }
}
