package org.ditto.feature.login.controllers;

/**
 * Created by admin on 2017/5/13.
 */

public class ForgetpasswordController {
    public interface Callbacks {
        void onUsernameDone(String username);
        void onPasswordDone(String username);
    }
}
