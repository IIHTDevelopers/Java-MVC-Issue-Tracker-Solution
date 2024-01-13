package com.yaksha.training.issuetrack.controller;

import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.enums.Status;
import com.yaksha.training.issuetrack.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/issue", "/"})
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = {"/list", "/", "/search"})
    public String listIssues(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                             @RequestParam(value = "status", required = false) String status,
                             @PageableDefault(size = 5) Pageable pageable,
                             Model theModel) {
        Page<Issue> issues = issueService.searchIssues(theSearchName, status, pageable);
        theModel.addAttribute("issues", issues.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("status", status==null || status.isEmpty() ? "": status);
        theModel.addAttribute("statusList", List.of(Status.OPEN.name(), Status.IN_PROGRESS.name(), Status.COMPLETED.name(), ""));
        theModel.addAttribute("totalPage", issues.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-issues";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("issue", new Issue());
        return "issue-add";
    }

    @PostMapping("/saveIssue")
    public String saveIssue(@Valid @ModelAttribute("issue") Issue theIssue, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "issue-add";
        }
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

    @RequestMapping("/updateStatus")
    public String updateStatus(@RequestParam("status") Status status, @RequestParam("id") Long id, Model theModel) {
        issueService.updateStatus(status, id);
        return "redirect:/issue/list";

    }


}
