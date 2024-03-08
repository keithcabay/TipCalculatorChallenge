module edu.farmingdale.part1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.part1 to javafx.fxml;
    exports edu.farmingdale.part1;
}