package org.ditto.feature.login.controllers;


/**
 * Created by admin on 2017/5/13.
 */

public class RegisterController  {
    public interface Callbacks {
        void onUsernameDone(String username);
        void onPasswordDone(String username);
    }
}
