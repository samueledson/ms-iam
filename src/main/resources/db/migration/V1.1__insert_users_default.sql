INSERT IGNORE INTO `users` (id, status_id, cpf, email, password, name, cellphone, created_at, updated_at,
                                           deleted_at)
VALUES (1, 1, '12345678901', 'user@default.com', '$2a$10$wbQqIA3yHEoqbOy8pMvFQubXViV39zQlNopSBwrv8CQYUqz.7pkFq',
        'User Default', '34999999999', NOW(), null, null);
