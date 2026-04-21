#ifndef DISPLAY_H
#define DISPLAY_H

#include <stdint.h>
#include <stdbool.h>
#include <SDL2/SDL.h>

#define FPS 30
#define FRAME_TARGET_TIME (1000 / FPS)


typedef struct {
    uint8_t show_vertices: 1;
    uint8_t show_wireframe: 1;
    uint8_t show_flat_shaded: 1;
    uint8_t backface_culling: 1;
    uint8_t show_textured: 1;
    uint8_t padding: 3;
} render_mode_t;

bool initialize_window();
int get_window_width(void);
int get_window_height(void);

void draw_grid(void);
void draw_pixel(int x, int y, uint32_t color);
void draw_line(int x0, int y0, int x1, int y1, uint32_t color);
void draw_rect(int x, int y, int width, int height, uint32_t color);
void render_color_buffer(void);
void render_text_area(int fov_factor);
void clear_color_buffer(uint32_t color);
void clear_z_buffer(void);
void destroy_window(void);

void toggle_show_vertices(void);
void toggle_show_wireframe(void);
void toggle_show_flat_shaded(void);
void toggle_backface_culling(void);
void toggle_show_textures(void);

bool is_show_vertices(void);
bool is_show_wireframe(void);
bool is_show_flat_shaded(void);
bool is_backface_culling(void);
bool is_show_textures(void);

float get_zbuffer_at(int x, int y);
void set_zbuffer_at(int x, int y, float value);

#endif
