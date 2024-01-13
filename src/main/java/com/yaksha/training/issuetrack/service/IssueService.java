package com.yaksha.training.issuetrack.service;

import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.enums.Status;
import com.yaksha.training.issuetrack.repository.IssueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    public Issue saveIssue(Issue theIssue) {
        return issueRepository.save(theIssue);
    }

    public Issue getIssue(Long issueId) {
        return issueRepository.findById(issueId).get();
    }

    public boolean deleteIssue(Issue issue) {
        issueRepository.delete(issue);
        return true;
    }

    public boolean updateStatus(Status status, Long id) {
        issueRepository.updateIssueStatus(status.name(), id);
        return true;
    }

    public Page<Issue> searchIssues(String searchKey, String status, Pageable pageable) {
        searchKey = searchKey != null && searchKey.isEmpty() ? null : searchKey;
        Status status1 = status!=null && !status.isEmpty() ? Status.valueOf(status) : null;
        return issueRepository.findByIssueTitleDescOwnerAndStatus(searchKey, status1, pageable);
    }

}
