cmake_minimum_required(VERSION 3.16)

# Nombre del proyecto
project(ColorListApp VERSION 1.0 LANGUAGES CXX)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets)

# Configuración general del compilador
set(CMAKE_AUTOUIC ON)   
set(CMAKE_AUTOMOC ON)   
set(CMAKE_AUTORCC ON)   

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Fuentes del proyecto
set(PROJECT_SOURCES
    main.cpp
    mainwindow.cpp
    mainwindow.h
    mainwindow.ui
)

# Crear el ejecutable
if(${QT_VERSION_MAJOR} GREATER_EQUAL 6)
    qt_add_executable(ColorListApp
        ${PROJECT_SOURCES}
    )
else()
    add_executable(ColorListApp
        ${PROJECT_SOURCES}
    )
endif()

# Vincular las bibliotecas necesarias
target_link_libraries(ColorListApp PRIVATE Qt${QT_VERSION_MAJOR}::Widgets)

