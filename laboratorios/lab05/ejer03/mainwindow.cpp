#include "mainwindow.h"
#include "./ui_mainwindow.h"
#include <QStringList>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    // lista de paises
    QStringList paises = {
        "Peru",
        "Mexico",
        "Colombia",
        "Argentina",
        "Chile",
        "Estados Unidos",
        "Canada",
        "Francia",
        "Italia",
        "Japon"
    };

    // idioma y capital
    datosPaises = {
        {"Peru", {"Español", "Lima"}},
        {"Mexico", {"Español", "Ciudad de Mexico"}},
        {"Colombia", {"Español", "Bogota"}},
        {"Argentina", {"Español", "Buenos Aires"}},
        {"Chile", {"Español", "Santiago"}},
        {"Estados Unidos", {"Ingles", "Washington D.C."}},
        {"Canada", {"Ingles y Frances", "Ottawa"}},
        {"Francia", {"Frances", "Paris"}},
        {"Italia", {"Italiano", "Roma"}},
        {"Japon", {"Japones", "Tokio"}}
    };

    // se crea el modelo y se asocia al QListView
    model = new QStringListModel(this);
    model->setStringList(paises);
    ui->listView->setModel(model);

    // conectamos la señal y slot
    connect(ui->listView->selectionModel(), &QItemSelectionModel::currentChanged,
            this, &MainWindow::on_countrySelected);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_countrySelected(const QModelIndex &index)
{
    QString pais = model->data(index, Qt::DisplayRole).toString();

    if (datosPaises.contains(pais)) {
        QString idioma = datosPaises[pais].first;
        QString capital = datosPaises[pais].second;
        ui->labelInfo->setText("Idioma: " + idioma + "\nCapital: " + capital);
    } else {
        ui->labelInfo->setText("Informacion no disponible");
    }
}
