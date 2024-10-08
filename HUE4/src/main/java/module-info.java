module net.htlgkr.pos3.hue4.dashboard.hue4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.htlgkr.pos3.hue4.dashboard.hue4 to javafx.fxml;
    exports net.htlgkr.pos3.hue4.dashboard.hue4;
}