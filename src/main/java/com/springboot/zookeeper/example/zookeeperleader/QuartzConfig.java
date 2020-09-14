package com.springboot.zookeeper.example.zookeeperleader;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public ZkSchedulerFactoryBean schedulerFactoryBean() throws Exception {
        ZkSchedulerFactoryBean schedulerFactoryBean=new ZkSchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(jobDetail());
        schedulerFactoryBean.setTriggers(trigger());
        return schedulerFactoryBean;
    }


    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(QuartzJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder simpleScheduleBuilder=
                SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(1).repeatForever();
        return TriggerBuilder.newTrigger().forJob(jobDetail()).
                withSchedule(simpleScheduleBuilder).build();
    }
}
