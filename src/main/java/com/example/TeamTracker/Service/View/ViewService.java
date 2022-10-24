package com.example.TeamTracker.Service.View;

import com.example.TeamTracker.Model.View;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ViewService {

    //Abstract User related methods
    Collection<View> getAllViews();
    View createNewView(View view);
    void deleteViewById(Long id);
    Collection<View> getViewsForOneUser(String owner);
    Collection<View> findViewByIdAndOwner(String owner, Long id);
}