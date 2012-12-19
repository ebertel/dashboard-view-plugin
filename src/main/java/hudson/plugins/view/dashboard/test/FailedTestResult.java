package hudson.plugins.view.dashboard.test;

import hudson.model.Job;
import hudson.tasks.junit.CaseResult;

public class FailedTestResult {

  private Job job;
  private CaseResult result;


  public FailedTestResult(Job job, CaseResult result) {
    this.job = job;
    this.result = result;
  }

  public Job getJob() {
    return job;
  }

  public CaseResult getResult() {
    return result;
  }

  public String getUrl() {
    return result.getOwner().getUrl() + "testReport" + result.getUrl();
  }

  public String getFailureRate() {
    int tests = 0, fails = 0;
    CaseResult oldResult = result;
    do {
      tests++;
      if (!oldResult.isPassed()) {
        fails++;
      }
      oldResult = oldResult.getPreviousResult();
    } while (oldResult != null);

    return fails + " / " + tests;
  }
}
