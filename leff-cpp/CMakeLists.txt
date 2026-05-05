cmake_minimum_required(VERSION 3.28)

project(leff_cpp)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(SDL3 REQUIRED)

add_executable(leff_cpp main.cpp)

target_link_libraries(leff_cpp PRIVATE SDL3::SDL3)