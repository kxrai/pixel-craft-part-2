module pixelcraft.pixelcraftgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens pc.gui.pixelcraftgui to javafx.fxml;
    exports pixelcraft;
    exports view;
    opens view to javafx.fxml;
}