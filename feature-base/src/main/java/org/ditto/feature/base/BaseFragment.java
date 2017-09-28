package org.ditto.feature.base;

import android.arch.lifecycle.LifecycleFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class BaseFragment extends LifecycleFragment {


    String title = "";
    String route = "";

    public String getTitle() {
        return title;
    }

    public BaseFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getRoute() {
        return route;
    }

    public BaseFragment setRoute(String route) {
        this.route = route;
        return this;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BaseFragment() {
    }

}
