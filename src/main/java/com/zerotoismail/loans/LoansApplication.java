package com.zerotoismail.loans;

import com.zerotoismail.loans.audit.AuditAwareImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API",
                description = "Handle all loans services",
                summary = "This is the loan summary",
                version = "v1",
                contact = @Contact(
                        name = "Loans - Ismail Khan",
                        url = "http://www.ismailkhan.tech",
                        email = "ismail@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.ismailkhan.tech"
                )
        )
)
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
