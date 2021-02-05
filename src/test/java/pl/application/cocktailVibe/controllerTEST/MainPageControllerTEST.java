package pl.application.cocktailVibe.controllerTEST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainPageControllerTEST {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void givenGetRequest_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/cocktailVibe/search").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/search?searchedString=sugar").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
