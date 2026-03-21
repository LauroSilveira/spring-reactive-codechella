package br.com.alura.codechella.domain.event.service;

import br.com.alura.codechella.domain.translation.LanguageTag;
import br.com.alura.codechella.domain.event.out.GetTranslationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetTextTranslatedService {

    private final GetTranslationAdapter getTranslationAdapter;

    public Mono<String> getTranslation(final String tetToTranslate, final LanguageTag languageTag) {
        return getTranslationAdapter.getTranslation(tetToTranslate, languageTag);
    }
}
