package br.com.alura.codechella.adapters.rest.event;

import br.com.alura.codechella.domain.event.EventType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class EventControllerIT {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void getEventTranslatedById() {
    }

    @Test
    void shouldCreateNewEvent() {
        //Given
        final var eventDTO = new EventDTO(null, "KISS", LocalDate.now(), "KISS Concert", EventType.SHOW);
        //When
        webTestClient.post()
                .uri("/events")
                .bodyValue(eventDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventDTO.class)
                .value(response -> {
                    assertThat(response)
                            .usingRecursiveComparison()
                            .ignoringFields("id")
                            .isEqualTo(eventDTO);
                });
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findByCategoryType() {
    }
}