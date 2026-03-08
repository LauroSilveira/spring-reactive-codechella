package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.database.postgres.event.EventRepository;
import br.com.alura.codechella.adapters.rest.translation.LanguageTag;
import br.com.alura.codechella.domain.event.service.GetTextTranslatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetEventTranslatedUseCase {

    private final EventRepository eventRepository;
    private final GetTextTranslatedService getTextTranslatedService;

    public Mono<String> getTranslation(final Long id, final LanguageTag languageTag) {
        return eventRepository.findById(id)
                .flatMap(event -> getTextTranslatedService.getTranslation(event.getDescription(),
                        languageTag));
    }
}
