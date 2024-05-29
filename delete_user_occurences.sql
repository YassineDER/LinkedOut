DO $$
DECLARE
    r record;
    user_id_to_delete INTEGER := 1; 
BEGIN
    FOR r IN (
        SELECT 
            tc.table_schema, 
            tc.table_name, 
            kcu.column_name
        FROM 
            information_schema.table_constraints AS tc 
            JOIN information_schema.key_column_usage AS kcu
              ON tc.constraint_name = kcu.constraint_name
              AND tc.table_schema = kcu.table_schema
            JOIN information_schema.constraint_column_usage AS ccu
              ON ccu.constraint_name = tc.constraint_name
        WHERE tc.constraint_type = 'FOREIGN KEY' 
          AND ccu.table_name = 'user'
    ) LOOP
        EXECUTE 
            'DELETE FROM ' || quote_ident(r.table_schema) || '.' || quote_ident(r.table_name) || 
            ' WHERE ' || quote_ident(r.column_name) || ' = ' || user_id_to_delete;
    END LOOP;

    -- Finally, delete the user from the users table
    EXECUTE 'DELETE FROM public."user" WHERE user_id = ' || user_id_to_delete;
END $$;
