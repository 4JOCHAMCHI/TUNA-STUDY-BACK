package com.team4chamchi.tunastudy.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Scheduled(cron = "0 * * * * *")
    public void perform() throws Exception {
        jobLauncher.run(job, new JobParametersBuilder().addLong("time",  System.currentTimeMillis()).toJobParameters());
    }
}
