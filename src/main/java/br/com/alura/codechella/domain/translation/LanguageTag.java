package br.com.alura.codechella.domain.translation;

import lombok.Getter;

@Getter
public enum LanguageTag {
    PT_BR("PT-BR"),
    PT_PT("PT-PT"),
    EN_US("EN-US"),
    EN_GB("EN-GB"),
    ES("ES"),
    FR("FR");

    private final String value;

    LanguageTag(final String value) {
        this.value = value;
    }
}
