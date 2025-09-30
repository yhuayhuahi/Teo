cmake_minimum_required(VERSION 3.12)
project(TrapecioIntegral)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_CXX_FLAGS_DEBUG "-g -O0 -Wall -Wextra")
set(CMAKE_CXX_FLAGS_RELEASE "-O3 -DNDEBUG")

set(EXTERNAL_DIR ${CMAKE_SOURCE_DIR}/external)

file(MAKE_DIRECTORY ${EXTERNAL_DIR})

# Descargar ExprTk
set(EXPRTK_HEADER ${EXTERNAL_DIR}/exprtk.hpp)
if(NOT EXISTS ${EXPRTK_HEADER})
    message(STATUS "Descargando ExprTk...")
    file(DOWNLOAD 
        "https://raw.githubusercontent.com/ArashPartow/exprtk/master/exprtk.hpp"
        ${EXPRTK_HEADER}
        SHOW_PROGRESS
        STATUS download_status
    )
    list(GET download_status 0 status_code)
    if(NOT status_code EQUAL 0)
        message(FATAL_ERROR "Error descargando ExprTk")
    endif()
    message(STATUS "ExprTk descargado exitosamente")
endif()

find_package(Threads REQUIRED)

# Crear ejecutables
add_executable(trapecio src/trapecio.cpp)
add_executable(trapecioPool src/trapecioPool.cpp)

# Incluir directorios
target_include_directories(trapecio PRIVATE ${EXTERNAL_DIR})
target_include_directories(trapecioPool PRIVATE ${EXTERNAL_DIR})

# Enlazar threads
target_link_libraries(trapecio Threads::Threads)
target_link_libraries(trapecioPool Threads::Threads)

if(UNIX AND NOT APPLE)
    target_link_libraries(trapecio m)
    target_link_libraries(trapecioPool m)
endif()

target_compile_definitions(trapecio PRIVATE exprtk_disable_string_capabilities)
target_compile_definitions(trapecioPool PRIVATE exprtk_disable_string_capabilities)
