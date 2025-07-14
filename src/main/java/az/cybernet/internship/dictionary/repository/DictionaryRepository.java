package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntity;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryRepository {



    DictionaryEntity getById(@Param("id") UUID id);
    @Insert("INSERT INTO dictionary (id, category, value," +
            " description, is_active, deleted_at) " +
            "VALUES ('invoice_status_pending', 'Invoice Status', " +
            "'Pending', 'Invoice received but not yet processed', TRUE)")
    void insert(DictionaryEntity entity);
    @Update("""
    UPDATE dictionary SET 
        category = CASE id
            WHEN 'invoice_status_pending' THEN 'Invoice Status'
            WHEN 'invoice_status_validated' THEN 'Invoice Status'
            WHEN 'invoice_status_approved' THEN 'Invoice Status'
            WHEN 'invoice_status_paid' THEN 'Invoice Status'
        END,
        value = CASE id
            WHEN 'invoice_status_pending' THEN 'Pending'
            WHEN 'invoice_status_validated' THEN 'Validated'
            WHEN 'invoice_status_approved' THEN 'Approved'
            WHEN 'invoice_status_paid' THEN 'Paid'
        END,
        description = CASE id
            WHEN 'invoice_status_pending' THEN 'Invoice received but not yet processed'
            WHEN 'invoice_status_validated' THEN 'Invoice data has passed validation rules'
            WHEN 'invoice_status_approved' THEN 'Approved for payment by authorized approver'
            WHEN 'invoice_status_paid' THEN 'Invoice has been fully paid'
        END,
        is_active = TRUE,
        deleted_at = NULL
    WHERE id IN (
        'invoice_status_pending', 
        'invoice_status_validated', 
        'invoice_status_approved', 
        'invoice_status_paid'
    )
""")
    void update(DictionaryEntity entity);
    @Update("""
    UPDATE dictionary SET 
        is_active = FALSE,
        deleted_at = CURRENT_TIMESTAMP
    WHERE id IN (
        'invoice_status_pending', 
        'invoice_status_validated', 
        'invoice_status_approved', 
        'invoice_status_paid'
    )
""")
    void softDelete(@Param("id") UUID id);
    @Update("""
    UPDATE dictionary SET 
        is_active = TRUE,
        deleted_at = NULL
    WHERE id IN (
        'invoice_status_pending', 
        'invoice_status_validated', 
        'invoice_status_approved', 
        'invoice_status_paid'
    )
""")
    void restore(@Param("id") UUID id);
}