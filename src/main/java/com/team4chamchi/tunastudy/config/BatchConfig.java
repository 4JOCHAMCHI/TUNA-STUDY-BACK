package com.team4chamchi.tunastudy.config;

import com.team4chamchi.tunastudy.reservation.service.ReservationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReservationService reservationService;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, ReservationService reservationService) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reservationService = reservationService;
    }

    //배치 작업
    @Bean
    public Job myJob() {
        return new JobBuilder("myJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(myStep())
                .build();
    }

    //배치 작업의 단위
    @Bean
    public Step myStep() {
        return new StepBuilder("myStep", jobRepository)
                .tasklet(myTasklet(), transactionManager)
                .build();
    }

    //배치 작업을 수행하는 실제 동작을 정의
    @Bean
    public Tasklet myTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("실행");
            reservationService.tenMinutesNotification();
            return RepeatStatus.FINISHED;
        };
    }
}
