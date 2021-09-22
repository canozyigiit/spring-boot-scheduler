package com.example.schedulerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
    /*
    * Her gün 12:00 (öğlen) :
        0 12 * * ?
    Her gün 13:00'te başlayıp 13:55'te biten ve ardından 18:00'de başlayıp 18:55'te biten beş dakikada bir :
        0/5 13,18 * * ?
    Her gün 13 : 00'te başlayıp 13:05'te biten her dakika :
         0-5 13 * * ?
    Haziran ayı boyunca her Salı 13:15 ve 13:45 :
        15,45 13 ? 6 Tue
    Her Pazartesi, Salı, Çarşamba, Perşembe ve Cuma saat 9:30'da :
        30 9 ? * MON-FRI
    Her ayın 15. günü saat 9:30'da :
        30 9 15 * ?
    Her ayın son günü saat 18.00'de :
        0 18 L * ?
    Her ayın üçüncü ila son günü saat 18:00'de :
        0 18 L-3 * ?
    Her ayın son Perşembe günü saat 10:30'da :
        30 10 ? * 5L
    Her ayın üçüncü Pazartesi günü saat 10:00'da :
        0 10 ? * 2#3
    Ayın 10. gününden başlayarak beş gün boyunca her gün gece yarısı 12'de :
        0 0 10/5 * ?*/

}
