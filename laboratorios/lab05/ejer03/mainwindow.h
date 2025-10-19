#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QStringListModel>
#include <QMap>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_countrySelected(const QModelIndex &index); // slot

private:
    Ui::MainWindow *ui;
    QStringListModel *model; // lista
    QMap<QString, QPair<QString, QString>> datosPaises; // pais -> (idioma, capital)
};

#endif // MAINWINDOW_H
