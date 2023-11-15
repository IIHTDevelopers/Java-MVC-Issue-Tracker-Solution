package com.yaksha.training.issuetrack.controller;

import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.service.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/issue", "/"})
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = {"/list", "/"})
    public String listIssues(Model theModel) {
        theModel.addAttribute("issues", issueService.getIssues());
        return "list-issues";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("issue", new Issue());
        return "issue-add";
    }

    @PostMapping("/saveIssue")
    public String saveIssue(@Valid @ModelAttribute("issue") Issue theIssue, Model theModel) {
        issueService.saveIssue(theIssue);
        return "redirect:/issue/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("issueId") Long issueId, Model theModel) {
        theModel.addAttribute("issue", issueService.getIssue(issueId));
        return "issue-add";

    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("issueId") Long issueId, Model theModel) {
        issueService.deleteIssue(issueService.getIssue(issueId));
        return "redirect:/issue/list";

    }
}
