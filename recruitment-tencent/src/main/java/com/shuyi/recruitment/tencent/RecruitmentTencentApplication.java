package com.shuyi.recruitment.tencent;

import com.shuyi.recruitment.repository.RecruitmentRepositoryApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {RecruitmentRepositoryApplication.class})
public class RecruitmentTencentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentTencentApplication.class, args);
    }

}
