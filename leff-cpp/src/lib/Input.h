//
// Created by johan on 2026-05-19.
//

#ifndef LEFF_CPP_INPUT_H
#define LEFF_CPP_INPUT_H
#include <cstdint>

namespace leffler {
    class Input {
    public:
        Input();
        uint8_t m_up: 1;
        uint8_t m_down: 1;
        uint8_t m_left: 1;
        uint8_t m_right: 1;
        uint8_t m_quit: 4;

        void handle();
    };
}
#endif //LEFF_CPP_INPUT_H
