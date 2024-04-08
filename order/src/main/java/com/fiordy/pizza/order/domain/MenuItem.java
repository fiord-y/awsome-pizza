package com.fiordy.pizza.order.domain;

import lombok.Getter;

import java.util.List;

// This needs to be configured in the database
public enum MenuItem {
    MARGHERITA(6d, "Tomato sauce", "Mozzarella", "Parmigiano", "Olive Oil", "Basil"),
    BUFALA(7d, "Tomato sauce", "Mozzarella di bufala", "Parmigiano", "Olive Oil", "Basil"),
    MARINARA(5d, "Tomato sauce", "Garlic", "Oregano", "Basil"),
    ROMANA(6d, "Tomato sauce", "Mozzarella", "Anchovies", "Capers", "Olives", "Oregano"),
    NAPOLITANA(6d, "Tomato sauce", "Mozzarella", "Anchovies", "Capers", "Oregano"),
    QUATTRO_STAGIONI(6d, "Tomato sauce", "Mozzarella", "Ham", "Mushrooms", "Artichokes", "Olives"),
    QUATTRO_FORMAGGI(7d, "Gorgonzola", "Mozzarella", "Parmigiano", "Fontina"),
    CAPRICCIOSA(7d, "Tomato sauce", "Mozzarella", "Ham", "Mushrooms", "Artichokes", "Olives"),
    PROSCIUTTO_FUNGHI(7d, "Tomato sauce", "Mozzarella", "Ham", "Mushrooms", "Olive Oil"),
    DIAVOLA(7d, "Tomato sauce", "Mozzarella", "Spianata"),
    SALSICCIA_FRIARIELLI(7d, "Tomato sauce", "Mozzarella", "Salsiccia", "Friarielli", "Olive Oil"),
    PISTACCHIO_BUFALA(8d, "Mozzarella di bufala", "Pistachios", "Olive Oil"),
    RUCOLA_PARMIGIANO(7.5d, "Tomato sauce", "Mozzarella", "Arugula", "Parmigiano", "Prosciutto"),
    TONNO_CIPOLLA(7.d, "Tomato sauce", "Mozzarella", "Tuna", "Onion", "Olives"),
    FUNGHI_TARTUFO(7.5d, "Mozzarella", "Mushrooms", "Truffle Cream", "Parmigiano", "Olive Oil"),
    POMODORO_BASILICO(6d, "Tomato sauce", "Mozzarella", "Cherry Tomatoes", "Basil", "Olive Oil"),
    FRUTTI_DI_MARE(8d, "Tomato sauce", "Mozzarella", "Mixed Seafood", "Garlic", "Parsley"),
    MELANZANE_RICOTTA(7d, "Tomato sauce", "Mozzarella", "Eggplant", "Ricotta", "Parmigiano"),
    GAMBERETTI_ZUCCHINE(8d, "Tomato sauce", "Mozzarella", "Shrimp", "Zucchini", "Garlic", "Chili Flakes"),
    WATER(5d, "Water");

    @Getter
    private List<String> ingredients;

    @Getter
    private Double cost;

    MenuItem(Double cost, String... ingredients){
        this.cost = cost;
        this.ingredients = List.of(ingredients);
    }
}
