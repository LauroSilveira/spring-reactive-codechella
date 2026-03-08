package br.com.alura.codechella.adapters.rest.event;

import br.com.alura.codechella.adapters.rest.translation.LanguageTag;
import br.com.alura.codechella.usecase.event.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final FindAllEventsUseCase findAllEvents;
    private final FindEventByIdUseCase findEventByIdUseCase;
    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final FindEventByCategoryTypeUseCase findEventByCategoryTypeUseCase;
    private final GetEventTranslatedUseCase getEventTranslateduseCase;
    private final EventMapper eventMapper;
    //"Back Pressure Buffer" avoid to overcharge the client (browser or other type) by sending many requests
    private final Sinks.Many<EventDTO> sink = Sinks.many().multicast().onBackpressureBuffer();


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDTO> findAll() {
        return findAllEvents.findAll().map(EventDTO::toDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<EventDTO> findById(@PathVariable final Long id) {
        return findEventByIdUseCase.findById(id).map(EventDTO::toDTO);
    }

    @GetMapping(value = "/{id}/translate/{languageTag}")
    public Mono<String> getEventTranslatedById(@PathVariable final Long id, @PathVariable final LanguageTag languageTag) {
        return getEventTranslateduseCase.getTranslation(id, languageTag);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EventDTO> create(@RequestBody final EventDTO dto) {
        return createEventUseCase.create(eventMapper.toEntity(dto)).map(EventDTO::toDTO)
                .doOnSuccess(sink::tryEmitNext);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable final Long id) {
        return deleteEventUseCase.delete(id);
    }

    @PatchMapping("{id}")
    public Mono<EventDTO> update(@PathVariable Long id, @RequestBody final EventDTO dto) {
        return updateEventUseCase.update(eventMapper.toEntity(dto), id).map(EventDTO::toDTO);
    }

    //TEXT_EVENT_STREAM_VALUE enables Server-Sent Events, the server send updates to client.
    //It is a one-way communication; only the server sends updates; the client cannot request updates.
    @GetMapping(value = "/category/{type}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDTO> findByCategoryType(@PathVariable final String type) {
        return findEventByCategoryTypeUseCase.findByCategoryType(type).map(EventDTO::toDTO)
                .mergeWith(sink.asFlux())
                .delayElements(Duration.ofSeconds(3));
    }
}
