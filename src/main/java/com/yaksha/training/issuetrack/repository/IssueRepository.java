package com.yaksha.training.issuetrack.repository;

import com.yaksha.training.issuetrack.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
