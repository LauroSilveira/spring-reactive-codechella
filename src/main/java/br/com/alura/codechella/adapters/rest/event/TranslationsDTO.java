package br.com.alura.codechella.adapters.rest.event;

import java.util.List;

public record TranslationsDTO(List<TextDTO> translations) {

    public String getFirstTranslation() {
        return translations.getFirst().text();
    }
}
