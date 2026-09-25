cmake_minimum_required(VERSION 3.28)

project(leff_cpp)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_COMPILER clang++)

find_package(SDL3 REQUIRED)

add_subdirectory(src/lib)
add_subdirectory(src/examples/helloworld)
add_subdirectory(src/examples/circle_vs_rect_collision)
add_subdirectory(src/examples/tile_based_collision)
