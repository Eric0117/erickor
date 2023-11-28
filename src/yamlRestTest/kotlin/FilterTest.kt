import com.carrotsearch.randomizedtesting.annotations.Name
import com.carrotsearch.randomizedtesting.annotations.ParametersFactory
import org.elasticsearch.test.rest.yaml.ClientYamlTestCandidate
import org.elasticsearch.test.rest.yaml.ESClientYamlSuiteTestCase

class FilterTest(
    @Name("yaml") testCandidate: ClientYamlTestCandidate,
) : ESClientYamlSuiteTestCase(testCandidate) {
    companion object {
        @JvmStatic
        @ParametersFactory
        @Throws(Exception::class)
        fun parameters(): Iterable<Array<Any>> {
            return createParameters()
        }
    }
}