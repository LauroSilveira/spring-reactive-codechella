package br.com.alura.codechella.adapters.rest.translation;

import br.com.alura.codechella.adapters.rest.event.TranslationsDTO;
import br.com.alura.codechella.domain.event.out.GetTranslationAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class TranslationRestClient implements GetTranslationAdapter {

    private static final String URL = "https://api-free.deepl.com/v2/translate";
    private final WebClient webClient = WebClient.builder()
            .baseUrl(URL)
            .build();

    @Override
    public Mono<String> getTranslation(final String tetToTranslate, final LanguageTag languageTag) {
        var request = new LinkedMultiValueMap<String, String>();
        request.add("text", tetToTranslate);
        request.add("target_lang", languageTag.getValue());

        return webClient.post()
                .header(HttpHeaders.AUTHORIZATION, "DeepL-Auth-Key " + "4ca8fea8-63b0-492a-99f4-853483f53883:fx")
                .body(BodyInserters.fromFormData(request))
                .retrieve()
                .bodyToMono(TranslationsDTO.class)
                .map(TranslationsDTO::getFirstTranslation);
    }
}
