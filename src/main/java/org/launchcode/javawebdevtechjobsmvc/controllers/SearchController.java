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

    private static List<Job> jobs = new ArrayList<>();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.


    public String displaySearchResults(Model model, String column, String searchTerm){
    if (searchTerm.toLowerCase().equals("all") || searchTerm.toLowerCase().equals("")){
        jobs = JobData.findAll();
    } else {
        jobs = JobData.findByColumnAndValue(columnChoices.get(column), searchTerm);
    }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
    return "results";
    }
}




//addAttribute is how template accesses data via the model
//    "searchTerm" must be a string, accessed by the template using ${searchTerm}
//    the attributeValue can be a variable of any type
//    return specifies the path to the template
//    RequestParam pulls data out of incoming request
//    ModelAttribute used for model binding. Looks at the JobData object in the POST request, sees that it
//    has the keys and values of a JobData object.
//    Is ModelAttribute/Model binding needed for this handler?
//    Instructions emphasize correct annotation.
//