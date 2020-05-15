package uk.co.dezzanet.greensparrow.greensparrow.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import uk.co.dezzanet.greensparrow.greensparrow.GreenSparrowApiApplication

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GreenSparrowApiApplication.class)
@AutoConfigureMockMvc
class WebSecurityConfigSpecIT extends Specification {

    @Autowired
    private MockMvc mvc

    def "GraphQL requests are correctly configured for CORS"() {
        when: "I make an OPTIONS request to the graphQL endpoint"
        def response = mvc.perform(options("/graphql")
            .header("Access-Control-Request-Method", "POST")
            .header("Access-Control-Request-Headers", "content-type")
            .header("Origin", "http://localhost:3000"))

        then: "I get a successful CORS response"
        response.andExpect(status().isOk())
        response.andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
        response.andExpect(header().string("Access-Control-Allow-Methods", "GET,POST"))
        response.andExpect(header().string("Access-Control-Allow-Headers", "content-type"))
    }
}
