package application.assets.reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ResvPayController{
    @FXML
    private Button btn_resvBack;
    @FXML
    private AnchorPane resvPayPane;

    //getter that mentioned in the reservation controller
    public Button getBtn_resvBack() {
        return btn_resvBack;
    }
}
