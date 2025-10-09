#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    // Agregamos algunos colores a la lista
    ui->colorListWidget->addItem("Rojo");
    ui->colorListWidget->addItem("Verde");
    ui->colorListWidget->addItem("Azul");
    ui->colorListWidget->addItem("Amarillo");
    ui->colorListWidget->addItem("Negro");

    // Conectamos la señal al slot
    connect(ui->colorListWidget, &QListWidget::itemClicked,
            this, &MainWindow::onColorItemClicked);
}

MainWindow::~MainWindow() {
    delete ui;
}

void MainWindow::onColorItemClicked(QListWidgetItem *item) {
    ui->colorLabel->setText("Color seleccionado: " + item->text());
}

