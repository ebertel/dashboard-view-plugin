package hudson.plugins.view.dashboard.test;

import hudson.model.Job;

public class FailedTestResult {

   private Job job;
   private String name;
   private int age;
   public FailedTestResult(Job job, String name, int age) {
      super();
      this.job = job;
      this.name = name;
      this.age = age;
   }


  public Job getJob() {
    return job;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
