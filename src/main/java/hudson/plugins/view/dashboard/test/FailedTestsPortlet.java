package hudson.plugins.view.dashboard.test;

import hudson.Extension;
import hudson.maven.reporters.SurefireAggregatedReport;
import hudson.model.Descriptor;
import hudson.model.Job;
import hudson.model.TopLevelItem;
import hudson.plugins.view.dashboard.DashboardPortlet;
import hudson.plugins.view.dashboard.Messages;
import hudson.tasks.junit.CaseResult;
import hudson.tasks.test.AbstractTestResultAction;
import hudson.tasks.test.TestResultProjectAction;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.*;

/**
 * Portlet that presents a grid of falied tests sorted by the age
 * 
 * @author Eung-Gun Bertelmann
 */
public class FailedTestsPortlet extends DashboardPortlet {
	@DataBoundConstructor
	public FailedTestsPortlet(String name) {
		super(name);
	}

  public List<FailedTestResult> getFailedTests(Collection<TopLevelItem> jobs) {

    List<FailedTestResult> results = new ArrayList<FailedTestResult>();

    for (TopLevelItem item : jobs) {
      if (item instanceof Job) {
        Job job = (Job) item;
        TestResultProjectAction testResults = job.getAction(TestResultProjectAction.class);
        if (testResults != null) {
          AbstractTestResultAction tra = testResults.getLastTestResultAction();
          if (tra != null) {
            List<CaseResult> failedTests = tra.getFailedTests();
            if (failedTests != null) {
              for (CaseResult failedTest : failedTests) {
                results.add(new FailedTestResult(job, failedTest));
              }
            }
          }
        } else {
          SurefireAggregatedReport surefireTestResults = job.getAction(SurefireAggregatedReport.class);
          if (surefireTestResults != null) {
            List<CaseResult> failedTests = surefireTestResults.getFailedTests();
            if (failedTests != null) {
              for (CaseResult failedTest : failedTests) {
                results.add(new FailedTestResult(job, failedTest));
              }
            }
          }
        }
      }
    }

    Collections.sort(results, new Comparator<FailedTestResult>() {
      public int compare(FailedTestResult ltr, FailedTestResult rtr) {
        return rtr.getResult().getAge()-ltr.getResult().getAge();
      }
    });


    return results;
  }



  @Extension
	public static class DescriptorImpl extends Descriptor<DashboardPortlet> {

		@Override
		public String getDisplayName() {
			return Messages.Dashboard_FailedTests();
		}
	}
}
