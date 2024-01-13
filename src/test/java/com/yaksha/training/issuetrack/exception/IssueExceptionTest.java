package com.yaksha.training.issuetrack.exception;

import com.yaksha.training.issuetrack.controller.IssueController;
import com.yaksha.training.issuetrack.entity.Issue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.yaksha.training.issuetrack.utils.MasterData.getIssue;
import static com.yaksha.training.issuetrack.utils.TestUtils.currentTest;
import static com.yaksha.training.issuetrack.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.issuetrack.utils.TestUtils.testReport;
import static com.yaksha.training.issuetrack.utils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class IssueExceptionTest {

    @InjectMocks
    private IssueController issueController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionIssueTitleNull() throws Exception {
        Issue issue = getIssue();
        issue.setIssueTitle(null);
        MvcResult result = this.mockMvc.perform(post("/saveIssue")
                .flashAttr("issue", issue)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionIssueNull() throws Exception {
        Issue issue = getIssue();
        issue.setIssueDesc(null);
        MvcResult result = this.mockMvc.perform(post("/saveIssue")
                .flashAttr("issue", issue)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionOwnerNull() throws Exception {
        Issue issue = getIssue();
        issue.setOwner(null);
        MvcResult result = this.mockMvc.perform(post("/saveIssue")
                .flashAttr("issue", issue)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionFillingDateNull() throws Exception {
        Issue issue = getIssue();
        issue.setFilingDate(null);
        MvcResult result = this.mockMvc.perform(post("/saveIssue")
                .flashAttr("issue", issue)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add")), exceptionTestFile);
    }

}
