package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.repository.event.EventRepository;
import br.com.alura.codechella.adapters.rest.translation.LanguageTag;
import br.com.alura.codechella.domain.event.service.GetTextTranslatedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEventTranslatedUseCase {

    private final EventRepository eventRepository;
    private final GetTextTranslatedService getTextTranslatedService;

    public Mono<String> getTranslation(final Long id, final LanguageTag languageTag) {
        return eventRepository.findById(id)
                .flatMap(event -> getTextTranslatedService.getTranslation(event.getDescription(),
                        languageTag))
                .doOnSuccess(translatedText -> log.info("Text: {} successfully translated to: {}", translatedText, languageTag));
    }
}
