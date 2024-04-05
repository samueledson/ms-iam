package br.com.blendtecnologia.iam.ui.rest.api.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.GetUserUseCase;
import br.com.blendtecnologia.iam.infrastructure.ApplicationProperties;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.UserRepositoryImpl;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa.JpaUserRepository;
import br.com.blendtecnologia.iam.ui.config.WebSecurityConfig;
import br.com.blendtecnologia.iam.ui.rest.api.TestEntityGenerator;
import br.com.blendtecnologia.iam.ui.rest.api.common.BaseControllerTest;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutorImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(value = UserControllerImpl.class)
class UserControlletTest extends BaseControllerTest {
    
    private JacksonTester<UserRequest> jsonUserRequest;

    @Configuration
    @ComponentScan(basePackages = {
        "br.com.blendtecnologia.iam.infrastructure.persistence.repositories.*",
        "br.com.blendtecnologia.iam.ui.rest.api.user",
        "br.com.blendtecnologia.iam.ui.rest.api.common"
    }, basePackageClasses = {
        ApplicationProperties.class
    })
    @Import(WebSecurityConfig.class)
    static class Config {}

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private GetUserUseCase getUserUseCase;

    @MockBean
    private CreateUserUseCaseInputMapper createUserUseCaseInputMapper;

    @SpyBean
    private UseCaseExecutorImpl useCaseExecutor;

    @SpyBean
    private UserRepositoryImpl userRepositoryImpl;

    @SpyBean
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Override
    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    //@Test
    void createUserReturnsNotAuthenticate() throws Exception {
        // given
        UserRequest userRequest = TestEntityGenerator.randomUserRequest();

        String payload = jsonUserRequest.write(userRequest).getJson();

        // when
        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isUnauthorized());
    }
}
