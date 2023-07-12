package com.technical.test.appinventory.config;

import com.technical.test.appinventory.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class DepreciationSchedulerConfig {

    private final ProductDao productDao;

    @Scheduled(cron = "0 0 0 1 1 *")
    public void performDepreciation() {
        productDao.performDepreciation();
    }
}
