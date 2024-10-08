module at.htlgkr.pos.dashboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlgkr.pos.dashboard to javafx.fxml;
    exports at.htlgkr.pos.dashboard;
}