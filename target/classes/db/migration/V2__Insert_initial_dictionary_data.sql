INSERT INTO dictionaries (id, value, description, is_active, created_at)
VALUES
    -- Invoice Statuses
    ('a9a7a5da-5de2-4b2a-b7e5-15a3b6f5f9e1', 'Invoice Status Pending', 'Invoice Status', TRUE, NOW()),
    ('b3c1b6e4-3e9f-4f2b-8a9c-27e1b9d4e8f2', 'Invoice Status Validated', 'Invoice Status', TRUE, NOW()),
    ('c7d5c9f8-2d8e-4a1b-9b8d-36f2c8e5d7f3', 'Invoice Status Approved', 'Invoice Status', TRUE, NOW()),
    ('d1e9d2a2-1c7d-4b0a-8c7e-45a3d7f6e6f4', 'Invoice Status Paid', 'Invoice Status', TRUE, NOW()),
    ('e5f3e5b6-0b6c-4a9a-7b6f-54b4e6f7d5f5', 'Invoice Status Canceled', 'Invoice Status', TRUE, NOW()),

    -- Tax Codes
    ('f9a1a4b2-9a5b-4c8a-6b5e-63c5f4a8c4f6', 'Tax Code VAT 18%', 'Tax Code', TRUE, NOW()),
    ('0b3b7c8e-8a4a-4d7a-5a4d-72d6e3b9b3f7', 'Tax Code VAT 8%', 'Tax Code', TRUE, NOW()),
    ('1c7c1d2f-7b3b-4e6a-4b3c-81e7d2c8a2f8', 'Tax Code VAT Exempt', 'Tax Code', TRUE, NOW()),

    -- Currencies
    ('2d1d5e6a-6c2a-4f5a-3a2b-90f8c1b7a1f9', 'Currency AZN', 'Currency', TRUE, NOW()),
    ('3e5e9f0b-5b1a-4a4a-2a1a-a9a9b0c6b0fa', 'Currency USD', 'Currency', TRUE, NOW()),
    ('4f9f3a4c-4a0a-4a3a-1a0a-b8b8a9d5a9fb', 'Currency EUR', 'Currency', TRUE, NOW()),

    -- Payment Terms
    ('5a3a7b8d-3b9a-4b2a-0a9a-c7c7b8e4b8fc', 'Payment Term Net 30', 'Payment Term', TRUE, NOW()),
    ('6b7b1c2e-2a8a-4c1a-9a8a-d6d6c7f3c7fd', 'Payment Term Due on Receipt', 'Payment Term', TRUE, NOW()),

    -- Document Types
    ('7c1c5d6f-1b7a-4d0a-8a7a-e5e5d6a2d6fe', 'Document Type Invoice', 'Document Type', TRUE, NOW()),
    ('8d5d9e0a-0a6a-4e9a-7a6a-f4f4e5b1e5ff', 'Document Type Credit Note', 'Document Type', TRUE, NOW()),

    -- Payment Methods
    ('9e9e3f4b-9b5a-4f8a-6a5a-03f3f4c0f4a0', 'Payment Method Wire Transfer', 'Payment Method', TRUE, NOW());