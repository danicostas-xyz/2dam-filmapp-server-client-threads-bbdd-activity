CREATE TABLE director (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50) NOT NULL,
);

CREATE TABLE film (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    id_director INT NOT NULL,
    rating DECIMAL(3, 1) NOT NULL CHECK (rating >= 0 AND rating <= 10),
    CONSTRAINT fk_director FOREIGN KEY (id_director) REFERENCES director(id)
);
