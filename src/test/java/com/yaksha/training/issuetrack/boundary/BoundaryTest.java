package com.yaksha.training.issuetrack.boundary;

import static com.yaksha.training.issuetrack.utils.MasterData.getIssue;
import static com.yaksha.training.issuetrack.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.issuetrack.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.issuetrack.utils.TestUtils.currentTest;
import static com.yaksha.training.issuetrack.utils.TestUtils.testReport;
import static com.yaksha.training.issuetrack.utils.TestUtils.yakshaAssert;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yaksha.training.issuetrack.entity.Issue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testBoundaryIssueTitleNotBlank() throws Exception {
		Issue issue = getIssue();
		issue.setIssueTitle("");
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueTitleNotNull() throws Exception {
		Issue issue = getIssue();
		issue.setIssueTitle(null);
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueTitleMinTwo() throws Exception {
		Issue issue = getIssue();
		issue.setIssueTitle(randomStringWithSize(1));
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueTitleMaxForty() throws Exception {
		Issue issue = getIssue();
		issue.setIssueTitle(randomStringWithSize(41));
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueDescNotBlank() throws Exception {
		Issue issue = getIssue();
		issue.setIssueDesc("");
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueDescNotNull() throws Exception {
		Issue issue = getIssue();
		issue.setIssueDesc(null);
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueDescMinTwo() throws Exception {
		Issue issue = getIssue();
		issue.setIssueDesc(randomStringWithSize(1));
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIssueDescMaxTwoHundred() throws Exception {
		Issue issue = getIssue();
		issue.setIssueDesc(randomStringWithSize(201));
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
	public void testOwnerMaxForty() throws Exception {
		Issue issue = getIssue();
		issue.setOwner(randomStringWithSize(41));
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStatusNotNull() throws Exception {
		Issue issue = getIssue();
		issue.setStatus(null);
		Set<ConstraintViolation<Issue>> violations = validator.validate(issue);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
