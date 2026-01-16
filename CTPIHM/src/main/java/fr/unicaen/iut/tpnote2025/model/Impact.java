package fr.unicaen.iut.tpnote2025.model;

import fr.unicaen.iut.tpnote2025.model.entities.Entity;
import javafx.geometry.Point2D;

public record Impact(double dh, double dL, Point2D norm, Entity entity) {

}
