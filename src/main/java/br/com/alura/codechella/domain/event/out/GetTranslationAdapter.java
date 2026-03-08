package br.com.alura.codechella.domain.event.out;

import br.com.alura.codechella.adapters.rest.translation.LanguageTag;
import reactor.core.publisher.Mono;

public interface GetTranslationAdapter {
    Mono<String> getTranslation(String tetToTranslate, LanguageTag languageTag);
}
