package dreamgames.backendtask.monolith.model.enums;

public enum Country {
    TURKEY,
    GERMANY,
    FRANCE,
    UNITED_KINGDOM,
    UNITED_STATES;

    public static Country getRandomCountry() {
        return values()[(int) (Math.random() * values().length)];
    }
}
