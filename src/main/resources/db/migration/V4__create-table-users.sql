CREATE TABLE tb_users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cliente_id BIGINT,
    barbeiro_id BIGINT,
    tipo_usuario VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_cliente FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id) ON DELETE SET NULL,
    CONSTRAINT fk_user_barbeiro FOREIGN KEY (barbeiro_id) REFERENCES tb_barbeiros(id) ON DELETE SET NULL
);
