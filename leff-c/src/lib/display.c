#include "display.h"
#include <SDL2/SDL_render.h>
#include <time.h>

#define FULL_SCREEN false

#define WINDOWED_MODE_W 1080
#define WINDOWED_MODE_H 720

static SDL_Window *window = NULL;
static SDL_Renderer *renderer = NULL;

static uint32_t *color_buffer = NULL;
static float *z_buffer = NULL;

static SDL_Texture *color_buffer_texture = NULL;
static int window_width = 320;
static int window_height = 240;

static render_mode_t render_mode = {
    0,
    0,
    0,
    0,
    1,
    0
};

int get_window_width(void) {
    return window_width;
}

int get_window_height(void) {
    return window_height;
}

bool display_init(const int w, const int h) {
    if (SDL_Init(SDL_INIT_EVERYTHING) != 0) {
        fprintf(stderr, "Error initializing SDL.\n");
        return false;
    }

    window_width = w;
    window_height = h;

    int fullscreen_w = window_width;
    int fullscreen_h = window_height;

    // Set width and height of the SDL window with the max screen resolution (if fullscreen true)
    if (FULL_SCREEN) {
        SDL_DisplayMode display_mode;
        SDL_GetCurrentDisplayMode(0, &display_mode);
        fullscreen_w = display_mode.w;
        fullscreen_h = display_mode.h;
    } else {
        fullscreen_w = WINDOWED_MODE_W;
        fullscreen_h = WINDOWED_MODE_H;
    }

    // Create a SDL Window
    window = SDL_CreateWindow(
        NULL,
        SDL_WINDOWPOS_CENTERED,
        SDL_WINDOWPOS_CENTERED,
        fullscreen_w,
        fullscreen_h,
        SDL_WINDOW_BORDERLESS
    );
    if (!window) {
        fprintf(stderr, "Error creating SDL window.\n");
        return false;
    }

    // Create a SDL renderer
    renderer = SDL_CreateRenderer(window, -1, 0);
    if (!renderer) {
        fprintf(stderr, "Error creating SDL renderer.\n");
        return false;
    }

    // Allocate the required memory in bytes to hold the color buffer and the z-buffer
    color_buffer = (uint32_t *) malloc(sizeof(uint32_t) * (window_width * window_height));
    z_buffer = (float *) malloc(sizeof(float) * window_width * window_height);

    // Creating a SDL texture that is used to display the color buffer
    color_buffer_texture = SDL_CreateTexture(
        renderer,
        SDL_PIXELFORMAT_RGBA32,
        SDL_TEXTUREACCESS_STREAMING,
        window_width,
        window_height
    );

    return true;
}

void display_draw_grid(void) {
    for (int y = 0; y < window_height; y += 10) {
        for (int x = 0; x < window_width; x += 10) {
            color_buffer[(window_width * y) + x] = 0xFF444444;
        }
    }
}

void draw_pixel(int x, int y, uint32_t color) {
    if (x < 0 || x >= window_width || y < 0 || y >= window_height) {
        return;
    }

    color_buffer[(window_width * y) + x] = color;
}

void draw_line(int x0, int y0, int x1, int y1, uint32_t color) {
    int delta_x = (x1 - x0);
    int delta_y = (y1 - y0);

    /*
     * Sometimes delta_y is greater than delta_x...meaning
     * we need to run the total delta_y as side_lenght instead of delta_x
    */
    int longest_side_length = (abs(delta_x) >= abs(delta_y)) ? abs(delta_x) : abs(delta_y);

    // Find out how much we should increment in both x and y each step
    float x_inc = delta_x / (float) longest_side_length;
    // Must cast to float otherwise it will be integer division in C
    float y_inc = delta_y / (float) longest_side_length;

    float current_x = x0;
    float current_y = y0;

    for (int i = 0; i <= longest_side_length; i++) {
        draw_pixel(round(current_x), round(current_y), color);
        current_x += x_inc;
        current_y += y_inc;
    }
}

void draw_rect(int x, int y, int width, int height, uint32_t color) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int current_x = x + i;
            int current_y = y + j;
            draw_pixel(current_x, current_y, color);
        }
    }
}

void display_render_color_buffer(void) {
    SDL_UpdateTexture(
        color_buffer_texture,
        NULL,
        color_buffer,
        window_width * sizeof(uint32_t)
    );
    SDL_RenderCopy(renderer, color_buffer_texture, NULL, NULL);
    SDL_RenderPresent(renderer);
}

void display_draw_circle(int cx, int cy, int radius, uint32_t *color) {
    for (int x = -radius; x <= radius; x++) {
        for (int y = -radius; y <= radius; y++) {
            if (((x * x) + (y * y)) <= (radius * radius)) {
                draw_pixel(cx + x, cy + y, *color);
            }
        }
    }

}

void clear_color_buffer(uint32_t color) {
    SDL_RenderClear(renderer);
    for (int i = 0; i < (window_width * window_height); i++) {
        color_buffer[i] = color;
    }
}

void clear_z_buffer(void) {
    for (int i = 0; i < (window_width * window_height); i++) {
        z_buffer[i] = 1.0;
    }
}

void toggle_show_vertices(void) {
    render_mode.show_vertices = !render_mode.show_vertices;
    printf("show_vertices=%d\n", render_mode.show_vertices);
}

void toggle_show_wireframe(void) {
    render_mode.show_wireframe = !render_mode.show_wireframe;
    printf("show_wireframe=%d\n", render_mode.show_wireframe);
}

void toggle_show_flat_shaded(void) {
    render_mode.show_flat_shaded = !render_mode.show_flat_shaded;
    printf("show_flat_shaded=%d\n", render_mode.show_flat_shaded);
}

void toggle_backface_culling(void) {
    render_mode.backface_culling = !render_mode.backface_culling;
    printf("backface_culling=%d\n", render_mode.backface_culling);
}

void toggle_show_textures(void) {
    render_mode.show_textured = !render_mode.show_textured;
    printf("show_textured=%d\n", render_mode.show_textured);
}

bool is_show_vertices(void) {
    return render_mode.show_vertices;
}

bool is_show_wireframe(void) {
    return render_mode.show_wireframe;
}

bool is_show_flat_shaded(void) {
    return render_mode.show_flat_shaded;
}

bool is_backface_culling(void) {
    return render_mode.backface_culling;
}

bool is_show_textures(void) {
    return render_mode.show_textured;
}

float get_zbuffer_at(int x, int y) {
    if (x < 0 || x >= window_width || y < 0 || y >= window_height) {
        return 1.0f;
    }
    return z_buffer[(window_width * y) + x];
}

void set_zbuffer_at(int x, int y, float value) {
    if (x < 0 || x >= window_width || y < 0 || y >= window_height) {
        return;
    }
    z_buffer[(window_width * y) + x] = value;
}

void display_destroy(void) {
    free(color_buffer);
    free(z_buffer);
    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    SDL_Quit();
}
