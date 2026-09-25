add_library(leffler_engine STATIC
        Application.cpp
        Application.h
        log.h
        Input.cpp
        Input.h
        Geom.h
        Shape.cpp
        Shape.h
        Geom.cpp
)

target_include_directories(leffler_engine PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_link_libraries(leffler_engine PUBLIC SDL3::SDL3)
