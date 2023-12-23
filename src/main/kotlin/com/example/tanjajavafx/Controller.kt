package com.example.tanjajavafx

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.CheckBoxTableCell
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell
import javafx.stage.Stage

class Controller : Application() {

    @FXML
    private lateinit var columnDozent: TableColumn<Kurs, String>

    @FXML
    private lateinit var columnLektion: TableColumn<Kurs, String>

    @FXML
    private lateinit var columnName: TableColumn<Kurs, String>

    @FXML
    private lateinit var columnObligatorisch: TableColumn<Kurs, Boolean>

    @FXML
    private lateinit var table: TableView<Kurs>


    private fun initTable() {
        //ad new Tablerow with empty values
        table.items.add(Kurs("Schoppe", "Haus", "Spezialkurs", false))
    }


    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Controller::class.java.getResource("tabelle.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }

    @FXML
    fun initialize() {
        println("initialize")
        assert(columnDozent != null) { "fx:id=\"columnDozent\" was not injected: check your FXML file 'tabelle.fxml'." }
        assert(columnLektion != null) { "fx:id=\"columnLektion\" was not injected: check your FXML file 'tabelle.fxml'." }
        assert(columnName != null) { "fx:id=\"columnName\" was not injected: check your FXML file 'tabelle.fxml'." }
        assert(columnObligatorisch != null) { "fx:id=\"columnObligatorisch\" was not injected: check your FXML file 'tabelle.fxml'." }
        assert(table != null) { "fx:id=\"table\" was not injected: check your FXML file 'tabelle.fxml'." }
        table.isEditable = true

        columnDozent.cellFactory = TextFieldTableCell.forTableColumn()
        columnDozent.setOnEditCommit {
            it.tableView.items[it.tablePosition.row].dozent = it.newValue
        }

        columnLektion.cellFactory = TextFieldTableCell.forTableColumn()
        columnLektion.setOnEditCommit {
            it.tableView.items[it.tablePosition.row].lektion = it.newValue
        }

        columnName.cellFactory = TextFieldTableCell.forTableColumn()
        columnName.setOnEditCommit {
            it.tableView.items[it.tablePosition.row].name = it.newValue
        }

        columnObligatorisch.cellFactory = CheckBoxTableCell.forTableColumn(columnObligatorisch)
        columnObligatorisch.setOnEditCommit {
            it.tableView.items[it.tablePosition.row].obligatorisch = it.newValue
        }

        //set the values of the columns

        //


        columnDozent.cellValueFactory = PropertyValueFactory<Kurs, String>("dozent")
        columnLektion.cellValueFactory = PropertyValueFactory<Kurs, String>("lektion")
        columnName.cellValueFactory = PropertyValueFactory<Kurs, String>("name")
        columnObligatorisch.cellValueFactory = PropertyValueFactory<Kurs, Boolean>("obligatorisch")

        initTable()


    }

}
