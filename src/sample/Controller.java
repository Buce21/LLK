package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import llk.Lianlk;

public class Controller {
    @FXML
    private TextField hwnd;
    @FXML
    private TextField timeout;
    @FXML
    private Button start;


    public TextField getTimeout() {
        return timeout;
    }

    public void setTimeout(TextField timeout) {
        this.timeout = timeout;
    }

    public Button getStart() {
        return start;
    }

    public void setStart(Button start) {
        this.start = start;
    }

    public TextField getHwnd() {
        return hwnd;
    }

    public void setHwnd(TextField hwnd) {
        this.hwnd = hwnd;
    }
    @FXML
    public void run(ActionEvent actionEvent) {
        String hwnd = getHwnd().getText();
        String timeout = getTimeout().getText();
        if ("".equals(hwnd) || "".equals(timeout)) {
            return;
        } else {
            Lianlk lianlk = new Lianlk(hwnd,timeout);
            Thread start = new Thread(lianlk);
            start.start();
        }
    }

}
