package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.translation.LanguageTag;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import br.com.alura.codechella.domain.event.service.GetTextTranslatedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEventTranslatedUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final GetTextTranslatedService getTextTranslatedService;

    public Mono<String> getTranslation(final Long id, final LanguageTag languageTag) {
        return eventRepositoryPort.findById(id)
                .flatMap(event -> getTextTranslatedService.getTranslation(event.description(),
                        languageTag))
                .doOnSuccess(translatedText -> log.info("Text: {} successfully translated to: {}", translatedText, languageTag));
    }
}
