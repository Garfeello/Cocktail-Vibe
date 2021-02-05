package pl.application.cocktailVibe.securityTEST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecuredControllerSpringBootIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/cocktailVibe/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/cocktailList").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/alcoholList").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/ingredient/ingredientList").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/register").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/logoutSuccess").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAuthRequestOnPrivateService_shouldRedirect() throws Exception {
        mvc.perform(get("/cocktailVibe/user/cocktails").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/cocktailVibe/addCocktail").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/cocktailVibe/editCocktail").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/cocktailVibe/user/alcohols").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/cocktailVibe/addAlcohol").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/cocktailVibe/editAlcohol").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("ADMIN@ADMIN") // user must exist in order to test it
    public void givenAuthRequestOnPrivateService_shouldConnectWith200() throws Exception {
        mvc.perform(get("/cocktailVibe/user/cocktails").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/addCocktail").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/editCocktail?cocktailId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/user/alcohols").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/addAlcohol").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/cocktailVibe/editAlcohol?alcoholId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}