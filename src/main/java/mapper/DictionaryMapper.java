package mapper;

public enum DictionaryMapper {
    DICTIONARY_MAPPER(
            "invoice_status_pending", "Invoice Status", "Pending", "Invoice received but not yet processed"),
    DICTIONARY_MAPPER_VALIDATED(
            "invoice_status_validated", "Invoice Status", "Validated", "Invoice data has passed validation rules"),
    DICTIONARY_MAPPER_APPROVED(
            "invoice_status_approved", "Invoice Status", "Approved", "Approved for payment by authorized approver"),
    DICTIONARY_MAPPER_PAID(
            "invoice_status_paid", "Invoice Status", "Paid", "Invoice has been fully paid");

    private final String id;
    private final String category;
    private final String value;
    private final String description;

    DictionaryMapper(String id, String category, String value, String description) {
        this.id = id;
        this.category = category;
        this.value = value;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
