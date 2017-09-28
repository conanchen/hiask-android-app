package org.ditto.feature.base.content.actions;

public class ActionMoresController   {
    public interface Callbacks {

        void onPositionClicked();
    }

    private final Callbacks callbacks;

    public ActionMoresController(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

}
