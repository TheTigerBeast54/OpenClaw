cmake_minimum_required(VERSION 3.22)
project(CaptainClawAndroid LANGUAGES C CXX)

set(CMAKE_C_STANDARD 99)
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(OPENCLAW_ROOT "${CMAKE_CURRENT_LIST_DIR}/../../../../..")
set(SDL_ROOT "${OPENCLAW_ROOT}/android-app/third_party/SDL")
set(SDL_IMAGE_ROOT "${OPENCLAW_ROOT}/android-app/third_party/SDL_image")
set(SDL_MIXER_ROOT "${OPENCLAW_ROOT}/android-app/third_party/SDL_mixer")
set(SDL_TTF_ROOT "${OPENCLAW_ROOT}/android-app/third_party/SDL_ttf")
set(SDL_GFX_ROOT "${OPENCLAW_ROOT}/android-app/third_party/SDL2_gfx")

if(NOT EXISTS "${SDL_ROOT}/CMakeLists.txt")
    message(FATAL_ERROR "SDL2 sources missing. Run the GitHub Actions workflow or fetch SDL dependencies.")
endif()

# Build SDL2 and its extension libraries for the Android NDK.
set(SDL_SHARED ON CACHE BOOL "" FORCE)
set(SDL_STATIC OFF CACHE BOOL "" FORCE)
set(SDL_TEST OFF CACHE BOOL "" FORCE)
add_subdirectory("${SDL_ROOT}" "${CMAKE_BINARY_DIR}/SDL")

set(SDL2IMAGE_VENDORED ON CACHE BOOL "" FORCE)
set(SDL2IMAGE_SAMPLES OFF CACHE BOOL "" FORCE)
set(SDL2IMAGE_TESTS OFF CACHE BOOL "" FORCE)
set(SDL2IMAGE_INSTALL OFF CACHE BOOL "" FORCE)
add_subdirectory("${SDL_IMAGE_ROOT}" "${CMAKE_BINARY_DIR}/SDL_image")

set(SDL2MIXER_VENDORED ON CACHE BOOL "" FORCE)
set(SDL2MIXER_SAMPLES OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_TESTS OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_INSTALL OFF CACHE BOOL "" FORCE)
add_subdirectory("${SDL_MIXER_ROOT}" "${CMAKE_BINARY_DIR}/SDL_mixer")

set(SDL2TTF_VENDORED ON CACHE BOOL "" FORCE)
set(SDL2TTF_SAMPLES OFF CACHE BOOL "" FORCE)
set(SDL2TTF_TESTS OFF CACHE BOOL "" FORCE)
set(SDL2TTF_INSTALL OFF CACHE BOOL "" FORCE)
add_subdirectory("${SDL_TTF_ROOT}" "${CMAKE_BINARY_DIR}/SDL_ttf")

# SDL2_gfx has no consistently usable CMake target across releases; define it explicitly.
add_library(SDL2_gfx STATIC
    "${SDL_GFX_ROOT}/SDL2_gfxPrimitives.c"
    "${SDL_GFX_ROOT}/SDL2_rotozoom.c"
    "${SDL_GFX_ROOT}/SDL2_framerate.c"
    "${SDL_GFX_ROOT}/SDL2_imageFilter.c"
)
target_include_directories(SDL2_gfx PUBLIC "${SDL_GFX_ROOT}")
target_link_libraries(SDL2_gfx PUBLIC SDL2)

# The upstream CMake project defines the openclaw shared library and links the game engine.
# It has legacy assumptions about SDL targets and Android output paths; the workflow will
# surface any incompatibility in its build log for the next patch iteration.
set(Android ON CACHE BOOL "" FORCE)
add_subdirectory("${OPENCLAW_ROOT}" "${CMAKE_BINARY_DIR}/OpenClaw")
