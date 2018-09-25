package display;

import config.WarningConfig;
import config.ColorConfig;
import data.IPrjManagable;
import data.Prj;
import data.PrjPoTrans;
import data.PrjVoTrans;
import display.alert.ErrorHandler;
import display.alert.IWarnable;
import display.alert.WarningHandler;
import display.canvas.CustomCanvas;
import exception.ProjectDataException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Window;


/**
 * distribute MainWin events
 */
public class MainWinController implements IPrjManagable, IWarnable {
    @FXML
    private AnchorPane root;
    @FXML
    private CustomCanvas canvas;

    @Override
    public CustomCanvas getCanvas() {
        return canvas;
    }

    @FXML
    public void initialize() {
        root.setBackground(new Background(new BackgroundFill(ColorConfig.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        canvas.setDraw();
    }

    @FXML
    public void drawOnAction() {
        canvas.setDraw();
    }

    @FXML
    public void eraseOnAction() {
        canvas.setErase();
    }

    @FXML
    public void recognizeOnAction() {
        canvas.setRecognize();
    }

    @FXML
    public void newOnAction() {
        warnOnNew();

        final PrjVoTrans prjVoTrans = new PrjVoTrans(this);
        prjVoTrans.clearVo();
    }

    private void warnOnNew() {
        new WarningHandler(this).showConfirmationDialog(WarningConfig.SAVE_FIRST);
    }

    @FXML
    public void openOnAction() {
        try {
            final Prj prj = poToPrj();
            prjToVo(prj);
        } catch (ProjectDataException e) {
            new ErrorHandler().showErrorDialog(e);
        }
    }

    private void prjToVo(Prj prj) {
        final PrjVoTrans prjVoTrans = new PrjVoTrans(this);
        prjVoTrans.prjToVo(prj);
    }

    private Prj poToPrj() throws ProjectDataException {
        final PrjPoTrans prjPoTrans = new PrjPoTrans(getWindow());
        return prjPoTrans.poToPrj();
    }

    @FXML
    public void saveOnAction() {
        save();
    }

    @Override
    public void onTakenWarningSuggestion(){
        save();
    }

    private void save() {
        try {
            final Prj prj = voToPrj();
            prjToPo(prj);
        } catch (ProjectDataException e) {
            new ErrorHandler().showErrorDialog(e);
        }
    }

    private void prjToPo(Prj prj) throws ProjectDataException {
        final PrjPoTrans prjPoTrans = new PrjPoTrans(getWindow());
        prjPoTrans.prjToPo(prj);
    }

    private Prj voToPrj() {
        final PrjVoTrans prjVoTrans = new PrjVoTrans(this);
        return prjVoTrans.voToPrj();
    }

    private Window getWindow() {
        return root.getScene().getWindow();
    }

}