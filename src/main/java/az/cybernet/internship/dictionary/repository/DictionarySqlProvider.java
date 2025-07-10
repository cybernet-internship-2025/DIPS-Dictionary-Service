package az.cybernet.internship.dictionary.repository; // <-- CORRECTED PACKAGE NAME

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DictionarySqlProvider implements ProviderMethodResolver {

    public String findDictionaries(Map<String, Object> params) {
        return new SQL() {{
            SELECT("*");
            FROM("dictionaries");

            if (params.containsKey("id") && params.get("id") != null) {
                WHERE("id = #{id}");
            }

            if (params.containsKey("value") && params.get("value") != null) {
                WHERE("LOWER(value) LIKE LOWER(#{value})");
            }

            if (params.containsKey("isActive") && params.get("isActive") != null) {
                WHERE("is_active = #{isActive}");
            } else {
                // Default to active entries if isActive is not specified
                WHERE("is_active = TRUE");
            }

            // Order by for consistent results
            ORDER_BY("created_at DESC");

        }}.toString() + (params.containsKey("limit") && params.get("limit") != null ? " LIMIT #{limit}" : "");
    }
}