package br.com.alura.codechella.adapters.rest.event;

import br.com.alura.codechella.domain.event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Slf4j
class EventControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void should_find_all() {
        //Given & When & Then
        webTestClient.get()
                .uri("/events")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(EventDTO.class)
                .getResponseBody()
                .doOnNext(eventDTO -> log.info("Event received: {}", eventDTO))
                .as(StepVerifier::create)
                .expectNextCount(10)  // Verify 10 elements has been received
                .thenCancel()
                .verify();
    }

    @Test
    void should_find_by_id() {
        //Given
        webTestClient.get().uri("/events/{id}", 8)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(EventDTO.class)
                .getResponseBody()
                .doOnNext(eventDTO -> log.info("Event found with id 8: {}", eventDTO))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }

    @Test
    void should_get_event_by_id_translated() {
        webTestClient.get()
                .uri("/events/{id}/translate/{languageTag}", 1, "EN_GB")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(eventDTO -> assertThat(eventDTO).isNotNull());
    }

    @Test
    void should_create_new_event() {
        //Given
        final var eventDTOExpected = new EventDTO(null, "KISS", LocalDate.now(), "KISS Concert", EventType.SHOW);
        //When
        final EventDTO responseBody = webTestClient.post()
                .uri("/events")
                .bodyValue(eventDTOExpected)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventDTO.class)
                .returnResult().getResponseBody();

        assertThat(responseBody)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(eventDTOExpected);
    }

    @Test
    void should_find_all_events() {
        //Given
        final var eventDTOExpected = new EventDTO(13L, "The Weeknd", LocalDate.parse("2025-11-02"),
                "Um show eletrizante ao ar livre com muitos efeitos especiais.", EventType.SHOW);
        //When
        webTestClient.get()
                .uri("/events")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(EventDTO.class)
                .value(response -> assertThat(response).isNotNull().contains(eventDTOExpected));
    }

    @Test
    void should_create_delete_an_event() {
        //Given a POST to create a new Event
        final var event = new EventDTO(null, "Metallica", LocalDate.now().plusMonths(2),
                "Show do metalica", EventType.SHOW);
        final var responseBody = webTestClient.post().uri("/events")
                .bodyValue(event)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.id()).isNotNull();

        //When & Then a DELETE request is sent
        webTestClient.delete()
                .uri("/events/{id}", responseBody.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    void should_update_an_event() {
        //Given
        final EventDTO eventDTO = webTestClient.get()
                .uri("/events/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EventDTO.class)
                .returnResult()
                .getResponseBody()
                .getFirst();

        assertThat(eventDTO).isNotNull();

        //When an event is updated
        final EventDTO eventUpdated = new EventDTO(null, "BON JOVI",
                LocalDate.now().plusDays(2),
                "SHOW do BON JOVI", EventType.SHOW);
        //Then
        final EventDTO response = webTestClient.patch().uri("/events/{id}", 1L)
                .bodyValue(eventUpdated)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(EventDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(eventUpdated);
    }

    @Test
    void should_find_event_by_category() {
        //When
        webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(10))
                .build()
                .get()
                .uri("/events/category/{type}", EventType.SHOW)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(EventDTO.class)
                .getResponseBody()
                .doOnNext(eventDTO -> log.info("Event: {}", eventDTO))
                .take(1) // Take the first element and closes the STREAM
                .as(StepVerifier::create)
                .expectNextCount(1)
                .thenCancel()// Cancel the STREAM
                .verify();
    }
}