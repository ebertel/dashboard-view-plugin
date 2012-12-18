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
}
