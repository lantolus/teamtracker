//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.example.TeamTracker.Controller;

import com.example.TeamTracker.Exceptions.EmployeeNotFoundException;
import com.example.TeamTracker.Exceptions.MandatoryFieldsNullException;
import com.example.TeamTracker.Model.FEViewTable;
import com.example.TeamTracker.Model.View;
import com.example.TeamTracker.Repository.ViewRepository;
import com.example.TeamTracker.Service.FeView.FEViewColumnsImplementation;
import com.example.TeamTracker.Service.View.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
//Setting this class as a REST Controller , so we can call APIs
@RestController
//Allowing FE to interact /w BE trough port 3000
@CrossOrigin(origins = "http://localhost:3000/")
//Setting base path for endpoints related to View objects
@RequestMapping("api/v1/")
public class ViewController {

    //Injecting View Service field Param
    private ViewService viewService;
    private FEViewColumnsImplementation feViewColumnsImplementation;


    @Autowired
    public ViewController(ViewService viewService,FEViewColumnsImplementation feViewColumnsImplementation) {
        this.viewService = viewService;
        this.feViewColumnsImplementation = feViewColumnsImplementation;
    }



    //API for retrieving all View objects from DB
    @RequestMapping(path = "views/getAll",produces = {"application/json"},method = RequestMethod.GET)
    public Collection<View> getAllViews() {
        return viewService.getAllViews();
    }

    //API for Creating new View objects ,and to save them to the DB
    @RequestMapping(path = "views/createNew",method = RequestMethod.POST)
    public ResponseEntity<View> createView(@RequestBody View view) {
        try {
            viewService.createNewView(view);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MandatoryFieldsNullException();
        }
    }

    //API for deleting User objects based on ID attribute
    @RequestMapping(path = "views/showViewsByOwner/{owner}", method = RequestMethod.GET)
    public Collection<View> getViewsByIdForOneUser(@PathVariable String owner) {
        try {
             return viewService.getViewsForOneUser(owner);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }

    //API for getting views for a certain user,based on a Views's owner, and ID
    @RequestMapping(path = "views/showViewByIdAndOwner/{owner}/{id}", method = RequestMethod.GET)
    public Collection<View> getCommonColumnsAndSendFEResponse(@PathVariable String owner,
                                                              @PathVariable Long id)
    {
        try {
            return viewService.findViewByIdAndOwner(owner,id);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
    //API for comparing arraylist from FE and BE,and sending the commmon elements as Arraylist to the FE
    @RequestMapping(path = "views/getCommonColumns/{id}", method = RequestMethod.GET)
    public ArrayList<FEViewTable> getCommonColumns(@PathVariable Long id) {
        try {

            return feViewColumnsImplementation.findCommon(feViewColumnsImplementation.getFeViewColumns(), feViewColumnsImplementation.sortView(id));

        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new IllegalStateException();
        }
    }


    //API for Deleting View based on ID
    @RequestMapping(path = "views/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteViewById(@PathVariable("id") Long id) {
        try {
            viewService.deleteViewById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }
}
