package pixelcraft.controller;

import pixelcraft.model.PCModel;
import pixelcraft.view.PCView;

public class PCController {
    private PCModel model;
    private PCView view;

    public PCController(PCModel model, PCView view) {
        this.model = model;
        this.view = view;
        this.view.setModel(model);
    }

    public void installControllers() {
        // Add event handlers for the view
    }
}
