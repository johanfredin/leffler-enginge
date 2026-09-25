//
// Created by johan on 2026-06-13.
//

#ifndef LEFF_CPP_SHAPE_H
#define LEFF_CPP_SHAPE_H

#include <vector>
#include <SDL3/SDL_rect.h>

#include "Application.h"
#include "Geom.h"

namespace leffler {
    class Shape {
    public:
        virtual ~Shape() = default;

        explicit Shape(Vec2 pos, Color color);

        Vec2 pos{};
        Color color{0, 0, 0 ,0};
        virtual void draw(SDL_Renderer *renderer) = 0;
    };

    class Rect : public Shape {
    public:
        Rect();
        explicit Rect(Vec2 pos, float w, float h, Color color);
        float w{}, h{};
        void draw(SDL_Renderer *renderer) override;
    private:
        SDL_FRect rect{};
    };

    class Circle : public Shape {
    public:
        explicit Circle(Vec2 pos, float radius, Color color);
        float radius;
        Vec2 vel{};
        void draw(SDL_Renderer *renderer) override;
        void tick();
    private:
        std::vector<SDL_FPoint> points{};
    };
}


#endif //LEFF_CPP_SHAPE_H
