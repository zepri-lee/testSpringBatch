package com.psc.example.q102;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@SpringBootApplication
public class Q102Application {

  public static void main(String[] args) {
    SpringApplication.run(Q102Application.class, args);
  }

  @Bean
  Tasklet tasklet() {
    return (contribution, context) -> {
      System.out.println("Hello World!");
      return RepeatStatus.FINISHED;
    };
  }

  @Bean
  Job job(JobRepository jobRepository, Step step) {
    return new JobBuilder("job", jobRepository).start(step).build();
  }

  @Bean
  Step step1(JobRepository jobRepository, Tasklet tasklet,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository).tasklet(tasklet, transactionManager).build();
  }
}
