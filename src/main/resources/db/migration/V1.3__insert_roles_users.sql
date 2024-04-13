INSERT IGNORE INTO `roles` (`name`, `description`, `created_at`) VALUES
('user:create', 'Criar usuário', NOW()),
('user:read', 'Visualizar usuário', NOW()),
('user:update', 'Alterar usuário', NOW()),
('user:detele', 'Remover usuário', NOW()),
('users:list', 'Listar usuários', NOW());