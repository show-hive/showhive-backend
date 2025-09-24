package com.showhive.admin.application.command.usecase;

import com.showhive.admin.DataBaseCleaner;
import com.showhive.admin.fixture.MemberGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(DataBaseCleaner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class BaseUseCaseTest {

    @Autowired
    protected MemberGenerator memberGenerator;
}
