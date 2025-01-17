package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.launchcode.javawebdevtechjobsmvc.models.JobField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    ArrayList<Job> jobs = new ArrayList<>();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value="results")
    public String displaySearchResults(Model model, @ModelAttribute String key,
                                       @ModelAttribute String value){
        ArrayList<Job> jobs;
        if(key.toLowerCase().equals("all")||value.toLowerCase().equals("all")){
            jobs = JobData.findAll();
//            model.addAttribute("title", "All Jobs");

        } else {
            jobs = JobData.findByColumnAndValue(key, value);
//            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + columnChoices.get(value));
        }
        model.addAttribute("jobs", jobs);
        return "search";
    }

//    @PostMapping(value="field")
//    public String displayFieldValueSearchResults(Model model, @ModelAttribute Job job, @ModelAttribute String fieldName){
//        ArrayList<Job> jobs;
//        if(fieldName.toLowerCase().equals("all")){
//            jobs = JobData.findAll();
//        } else {
//            jobs = JobData.findByValue(fieldName);
//        }
//        model.addAttribute("fieldNames", jobs);
//        return "search-field";
//    }
}
