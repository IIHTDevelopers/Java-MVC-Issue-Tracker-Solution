package com.yaksha.training.issuetrack.service;

import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.repository.IssueRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.issuetrack.utils.MasterData.asJsonString;
import static com.yaksha.training.issuetrack.utils.MasterData.getIssue;
import static com.yaksha.training.issuetrack.utils.MasterData.getIssueList;
import static com.yaksha.training.issuetrack.utils.TestUtils.businessTestFile;
import static com.yaksha.training.issuetrack.utils.TestUtils.currentTest;
import static com.yaksha.training.issuetrack.utils.TestUtils.testReport;
import static com.yaksha.training.issuetrack.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class IssueServiceTest {

    @InjectMocks
    private IssueService issueService;

    @Mock
    private IssueRepository issueRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetIssues() throws Exception {
        List<Issue> actual = getIssueList(5);
        when(issueRepository.findAll()).thenReturn(actual);
        List<Issue> expected = issueService.getIssues();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveIssue() throws Exception {
        Issue actual = getIssue();
        when(issueRepository.save(actual)).thenReturn(actual);
        Issue expected = issueService.saveIssue(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetIssue() throws Exception {
        Issue actual = getIssue();
        when(issueRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Issue expected = issueService.getIssue(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }


    @Test
    public void testServiceDeleteIssue() throws Exception {
        Issue actual = getIssue();
        boolean expected = issueService.deleteIssue(actual);
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }


}
