module delorme.john {
    requires javafx.controls;
    requires javafx.fxml;


    opens delorme.john to javafx.fxml;
    exports delorme.john;
    exports delorme.john.Controllers;
    opens delorme.john.Controllers to javafx.fxml;

    opens delorme.john.Models to javafx.fxml;
    exports delorme.john.Models;
}