module app.shadowlands {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens app to javafx.fxml;
    exports app;
    exports app.controller.intro;
    opens app.controller.intro to javafx.fxml;
    exports app.controller.prologue;
    opens app.controller.prologue to javafx.fxml;
    exports app.controller.general;
    opens app.controller.general to javafx.fxml;
    exports app.controller.act1;
    opens app.controller.act1 to javafx.fxml;
    exports app.controller.act1.darkAlley;
    opens app.controller.act1.darkAlley to javafx.fxml;
    exports app.controller.act1.forest;
    opens app.controller.act1.forest to javafx.fxml;
    exports app.controller.act1.forest.right;
    opens app.controller.act1.forest.right to javafx.fxml;
}