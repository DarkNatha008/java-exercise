module fr.unicaen.iut.tpNoteMMDV {
  requires javafx.controls;
  requires javafx.fxml;
requires javafx.graphics;
requires javafx.base;


  opens fr.unicaen.iut.tpnote2025 to javafx.fxml;
  exports fr.unicaen.iut.tpnote2025;
  exports fr.unicaen.iut.tpnote2025.model;
  opens fr.unicaen.iut.tpnote2025.model to javafx.fxml;
  exports fr.unicaen.iut.tpnote2025.model.entities;
  opens fr.unicaen.iut.tpnote2025.model.entities to javafx.fxml;
}