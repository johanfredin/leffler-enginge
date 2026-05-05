cmake_minimum_required(VERSION 3.10)
project(renderer C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall")

file(GLOB LIB_SOURCES src/lib/*.c)
add_library(core ${LIB_SOURCES})

target_include_directories(core PUBLIC src/lib)
target_link_libraries(core SDL2 SDL2_ttf m)

file(GLOB EXAMPLE_SOURCES src/examples/*/*.c)
foreach(example_src ${EXAMPLE_SOURCES})
    get_filename_component(example_name ${example_src} NAME_WE)
    add_executable(${example_name} ${example_src}
            src/lib/input.c)
    target_link_libraries(${example_name} core)
endforeach()
