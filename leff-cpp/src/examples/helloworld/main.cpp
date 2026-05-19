#define NDEBUG
#include "Application.h"
#include "log.h"
//
// Created by johan on 2026-05-07.
//

class MyApp: public leffler::Application {
public:
    using Application::Application;

    void render() override {
        log_debug("");
    }

    void tick() override {
    }
};

int main() {
    MyApp app("Hello World", 800, 600, false, {128, 50, 35});
    app.start();
}