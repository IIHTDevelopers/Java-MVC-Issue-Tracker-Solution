package com.yaksha.training.issuetrack.repository;

import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE issue i set i.status = :status where i.id = :id",
            nativeQuery = true)
    void updateIssueStatus(@Param("status") String status, @Param("id") Long id);

    @Query(value = "Select i from Issue i "
            + "where (:keyword IS NULL "
            + "OR lower(i.issueTitle) like %:keyword% "
            + "OR lower(i.issueDesc) like %:keyword%  "
            + "OR lower(i.owner) like %:keyword%) "
            + "AND (:status IS NULL OR i.status = :status )")
    Page<Issue> findByIssueTitleDescOwnerAndStatus(@Param("keyword") String keyword,
                                                   @Param("status") Status status,
                                                   Pageable pageable);

}
