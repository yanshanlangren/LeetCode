package elvis.openapi;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.OpenAiService;

public class OpenAPIDemo {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
