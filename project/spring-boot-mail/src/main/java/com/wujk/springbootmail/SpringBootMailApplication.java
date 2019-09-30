package com.wujk.springbootmail;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMailApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MailService service) {
        return args -> {
            System.out.println("start sendMail.....");
            service.sendTxtMail();
            service.sendHtmlMail();
            service.sendAttachedImageMail();
            service.sendAttendedFileMail();
            System.out.println("end sendMail.....");
        };
    }
}
