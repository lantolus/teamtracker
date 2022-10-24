package com.example.TeamTracker.Service.View;

import com.example.TeamTracker.Model.View;
import com.example.TeamTracker.Repository.ViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;


//Setting this class to be a Service
@Slf4j
@Service
@AllArgsConstructor
//Implementation of ViewService Abstract methods
public class ViewServiceImpl implements ViewService {

    //Injecting Repo field
    private final ViewRepository viewRepository;


    @Override // Method to retrieve all views
    public Collection<View> getAllViews() {
        return viewRepository.findAll();
    }

    @Override   // Method to create new view
    public View createNewView(View view) {
        return viewRepository.save(view);
    }

    @Override // Method to delete View by ID
    public void deleteViewById(Long id) {
        viewRepository.deleteById(id);
    }

    @Override // Method to retrieve all views
    public Collection<View> getViewsForOneUser(String owner) {
        return viewRepository.findViewByOwner(owner);
    }

    @Override // Method to retrieve all views
    public Collection<View> findViewByIdAndOwner(String owner,Long id) {
        return viewRepository.findViewByIdAndOwner(id,owner);
    }

}


