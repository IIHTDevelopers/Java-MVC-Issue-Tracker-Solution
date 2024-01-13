package com.yaksha.training.issuetrack.functional;

import com.yaksha.training.issuetrack.controller.IssueController;
import com.yaksha.training.issuetrack.entity.Issue;
import com.yaksha.training.issuetrack.service.IssueService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.issuetrack.utils.MasterData.getIssue;
import static com.yaksha.training.issuetrack.utils.MasterData.getIssueList;
import static com.yaksha.training.issuetrack.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.issuetrack.utils.TestUtils.businessTestFile;
import static com.yaksha.training.issuetrack.utils.TestUtils.currentTest;
import static com.yaksha.training.issuetrack.utils.TestUtils.testReport;
import static com.yaksha.training.issuetrack.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(issueController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListIssuesHome() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Issue> issues = getIssueList(5);
        Page<Issue> issuePage = new PageImpl<>(issues);
        when(issueService.searchIssues(null, null, pageable)).thenReturn(issuePage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-issues"), businessTestFile);
    }

    @Test
    public void testControllerListIssues() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Issue> issues = getIssueList(5);
        Page<Issue> issuePage = new PageImpl<>(issues);
        when(issueService.searchIssues(null, null, pageable)).thenReturn(issuePage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-issues"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/showFormForAdd")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add"), businessTestFile);
    }

    @Test
    public void testControllerSaveIssue() throws Exception {
        Issue issue = getIssue();
        MvcResult result = this.mockMvc.perform(post("/saveIssue")
                .flashAttr("issue", issue)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/issue/list"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Issue issue = getIssue();
        when(issueService.getIssue(issue.getId())).thenReturn(issue);
        MvcResult result = this.mockMvc.perform(get("/showFormForUpdate")
                .param("issueId", issue.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("issue-add"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForDeleteIssue() throws Exception {
        Issue issue = getIssue();
        MvcResult result = this.mockMvc.perform(get("/showFormForDelete")
                .param("issueId", issue.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/issue/list"), businessTestFile);
    }

    @Test
    public void testControllerUpdateStatus() throws Exception {
        Issue issue = getIssue();
        MvcResult result = this.mockMvc.perform(get("/updateStatus")
                        .param("id", issue.getId().toString())
                        .param("status", "COMPLETED"))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/issue/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchIssuesWithSearchKey() throws Exception {
        String keyword = randomStringWithSize(5);
        String status = "COMPLETED";
        Pageable pageable = PageRequest.of(0, 5);
        List<Issue> issues = getIssueList(5);
        Page<Issue> issuePage = new PageImpl<>(issues);
        when(issueService.searchIssues(keyword, status, pageable)).thenReturn(issuePage);
        MvcResult result = this.mockMvc.perform(get("/search")
                        .param("theSearchName", keyword)
                        .param("status", status))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-issues"), businessTestFile);
    }

    @Test
    public void testControllerFavSearchIssuesWithNullSearchKey() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Issue> issues = getIssueList(5);
        Page<Issue> issuePage = new PageImpl<>(issues);
        when(issueService.searchIssues(null, null, pageable)).thenReturn(issuePage);
        MvcResult result = this.mockMvc.perform(get("/search")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-issues"), businessTestFile);
    }


}
