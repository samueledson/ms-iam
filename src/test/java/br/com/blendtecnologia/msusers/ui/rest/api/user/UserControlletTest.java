package br.com.blendtecnologia.msusers.ui.rest.api.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blendtecnologia.msuser.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.msuser.core.domain.usecases.user.GetUserUseCase;
import br.com.blendtecnologia.msuser.infrastructure.ApplicationProperties;
import br.com.blendtecnologia.msuser.ui.config.WebSecurityConfig;
import br.com.blendtecnologia.msuser.ui.rest.api.user.CreateUserUseCaseInputMapper;
import br.com.blendtecnologia.msuser.ui.rest.api.user.UserControllerImpl;
import br.com.blendtecnologia.msuser.ui.rest.api.user.UserRequest;
import br.com.blendtecnologia.msuser.ui.usecases.UseCaseExecutorImpl;
import br.com.blendtecnologia.msusers.ui.rest.api.TestEntityGenerator;
import br.com.blendtecnologia.msusers.ui.rest.api.common.BaseControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserControllerImpl.class)
class UserControlletTest extends BaseControllerTest {
    
    private JacksonTester<UserRequest> jsonUserRequest;

    @MockBean
    private ApplicationProperties applicationProperties;

    @Configuration
    @ComponentScan(basePackages = {
        "br.com.blendtecnologia.msuser.ui.rest.api.user",
        "br.com.blendtecnologia.msuser.ui.rest.api.common"
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

    @Test
    void createUserReturnsNotAuthenticate() throws Exception {
        // given
        UserRequest userRequest = TestEntityGenerator.randomUserRequest();

        String payload = jsonUserRequest.write(userRequest).getJson();

        // when
        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isUnauthorized());
    }
}
