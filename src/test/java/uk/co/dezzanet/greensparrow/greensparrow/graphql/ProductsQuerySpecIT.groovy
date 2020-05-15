package uk.co.dezzanet.greensparrow.greensparrow.graphql

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.ReadContext
import graphql.ExecutionResult
import graphql.spring.web.servlet.GraphQLInvocation
import graphql.spring.web.servlet.GraphQLInvocationData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import uk.co.dezzanet.greensparrow.greensparrow.GreenSparrowApiApplication

import java.util.concurrent.CompletableFuture

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GreenSparrowApiApplication.class)
@AutoConfigureMockMvc
class ProductsQuerySpecIT extends Specification {
    @Autowired
    GraphQLInvocation invocation

    @Autowired
    ObjectMapper objectMapper

    def "it returns a list of products"() {
        given: "A graphQL query"
        def query = '''
        {
            products {
              id
              name
              priceInPence
            }
        }
        '''

        when: "The query is executed"
        def response = doGraphQLQuery(query)

        then: "there are no errors"
        response.read('$.errors', List.class).size() == 0

        and: "the correct number of products are returned"
        response.read('$.data.products', List.class).size() == 2

        and: "the first product is correct"
        response.read('$.data.products[0].id') == '1'
        response.read('$.data.products[0].name') == 'product1'
        response.read('$.data.products[0].priceInPence') == 114

        and: "the second product is correct"
        response.read('$.data.products[1].id') == '2'
        response.read('$.data.products[1].name') == 'product2'
        response.read('$.data.products[1].priceInPence') == 70
    }

    private ReadContext doGraphQLQuery(String query) {
        def operationName = null
        def variables = null
        def webRequest = null
        GraphQLInvocationData invocationData = new GraphQLInvocationData(query, operationName, variables)
        CompletableFuture<ExecutionResult> executionResult = invocation.invoke(invocationData, webRequest)
        ExecutionResult result = executionResult.get()

        return getJsonPath(result)
    }

    private ReadContext getJsonPath(ExecutionResult executionResult) {
        String jsonResponse = objectMapper.writeValueAsString([
                errors: executionResult.getErrors(),
                data  : executionResult.getData(),
        ])

        return JsonPath.parse(jsonResponse)
    }
}
