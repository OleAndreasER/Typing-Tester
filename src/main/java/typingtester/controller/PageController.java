package typingtester.controller;

import javafx.scene.input.KeyEvent;
import typingtester.pages.PageSwitcher;

public abstract class PageController {
    protected PageSwitcher sceneController;

    public void setPageSwitcher(PageSwitcher sceneController) {
        this.sceneController = sceneController;
    }

    public abstract void handleKeyPress(KeyEvent event);
}
