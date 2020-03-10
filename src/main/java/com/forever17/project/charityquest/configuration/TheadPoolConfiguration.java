package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.constants.CharityConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * configuration of thread pool for async methods.
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 10 Mar 2020
 * @since 1.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = CharityConstants.THREAD_POOL_PREFIX)
public class TheadPoolConfiguration {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

    private int keepAliveSeconds;

    private boolean waitForTasksToComplete;

    private int awaitTermination;

    @Bean("ThreadPoolTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(CharityConstants.THREAD_POOL_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToComplete);
        executor.setAwaitTerminationSeconds(awaitTermination);
        return executor;
    }
}
