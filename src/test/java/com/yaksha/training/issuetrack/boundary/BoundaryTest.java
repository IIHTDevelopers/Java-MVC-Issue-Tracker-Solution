package com.yaksha.training.issuetrack.boundary;


import com.yaksha.training.issuetrack.entity.Issue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.issuetrack.utils.MasterData.getIssue;
import static com.yaksha.training.issuetrack.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.issuetrack.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.issuetrack.utils.TestUtils.currentTest;
import static com.yaksha.training.issuetrack.utils.TestUtils.testReport;
import static com.yaksha.training.issuetrack.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testBouncerNotBlank() throws Exception {
        Issue issue = getIssue();
        issue.setBouncer("");
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBouncerNotNull() throws Exception {
        Issue issue = getIssue();
        issue.setBouncer(null);
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBouncerMinTwo() throws Exception {
        Issue issue = getIssue();
        issue.setBouncer(randomStringWithSize(1));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBouncerMaxFourty() throws Exception {
        Issue issue = getIssue();
        issue.setBouncer(randomStringWithSize(41));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testIssueNotBlank() throws Exception {
        Issue issue = getIssue();
        issue.setIssue("");
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testIssueNotNull() throws Exception {
        Issue issue = getIssue();
        issue.setIssue(null);
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testIssueMinTwo() throws Exception {
        Issue issue = getIssue();
        issue.setIssue(randomStringWithSize(1));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testIssueMaxTwoHundred() throws Exception {
        Issue issue = getIssue();
        issue.setIssue(randomStringWithSize(201));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFillingDateNotNull() throws Exception {
        Issue issue = getIssue();
        issue.setFilingDate(null);
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testOwnerNotBlank() throws Exception {
        Issue issue = getIssue();
        issue.setOwner("");
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testOwnerNotNull() throws Exception {
        Issue issue = getIssue();
        issue.setOwner(null);
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testOwnerMinTwo() throws Exception {
        Issue issue = getIssue();
        issue.setOwner(randomStringWithSize(1));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testOwnerMaxFourty() throws Exception {
        Issue issue = getIssue();
        issue.setOwner(randomStringWithSize(41));
        Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


}
