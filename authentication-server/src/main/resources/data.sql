-- client/secret
-- resourceserver/resourceserver
INSERT INTO clients (id, client_id, client_secret, scope)
VALUES ('1', 'client', '$2a$10$ow7g/dN/y2yeNgRlsemjQOFasZwBgKEir4L.kfxpCI0iYIWLo0pJK', 'write');
INSERT INTO clients (id, client_id, client_secret, scope)
VALUES ('2', 'resourceserver', '$2a$10$2Qt3CSiZ2b5QODITe/z.1udJngNIJQ7qUpp/iY7Fphz6dgWoKjQvC', 'read');

-- admin/changeme
-- john/12345
-- jack/12345
INSERT INTO users (id, username, password, authority)
VALUES ('1', 'admin', '$2a$10$qccpm8kSwlsA9Q9MvdZuyeWh7cdSHS1Ynxati6/zjf2UPwUEe3hji', 'write');
INSERT INTO users (id, username, password, authority)
VALUES ('2', 'john', '$2a$10$Bzo60x72H.6mBCuUT/KJMOdFma3aKztb1DjoPhQoV3S4Ynj48bqhq', 'read');
INSERT INTO users (id, username, password, authority)
VALUES ('3', 'jack', '$2a$10$Bzo60x72H.6mBCuUT/KJMOdFma3aKztb1DjoPhQoV3S4Ynj48bqhq', 'read');