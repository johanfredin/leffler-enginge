//
// Created by johan on 2026-06-13.
//

#ifndef LEFF_CPP_VECTOR_H
#define LEFF_CPP_VECTOR_H

namespace leffler {

    class Vec2 {
    public:
        Vec2();
        Vec2(float x, float y);

        Vec2 operator+=(Vec2 const& other) {
            x += other.x;
            y += other.y;
            return *this;
        }
        Vec2 operator-=(Vec2 const& other) const {
            Vec2 result{};
            result.x -= other.x;
            result.y -= other.y;
            return result;
        }

        float x;
        float y;
    };

}

#endif //LEFF_CPP_VECTOR_H
