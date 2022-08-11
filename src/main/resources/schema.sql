CREATE TABLE gpa (
                     gpa_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                     balance DOUBLE NOT NULL
);


CREATE TABLE app_user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      gpa_id BIGINT NOT NULL,
                      user_name VARCHAR(50) NOT NULL
);
ALTER TABLE app_user
    ADD FOREIGN KEY (gpa_id)
        REFERENCES gpa(gpa_id);



CREATE TABLE merchant (
                     merchant_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                     mcc VARCHAR(50) NOT NULL ,
                     merchant_name VARCHAR(50) NOT NULL ,
                     gpa_id BIGINT NOT NULL
);

ALTER TABLE merchant
    ADD FOREIGN KEY (gpa_id)
        REFERENCES gpa(gpa_id);

CREATE TABLE card (
                          card_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                          gpa_id BIGINT NOT NULL ,
                          card_number VARCHAR(50) NOT NULL ,
                          cvv VARCHAR(3) NOT NULL ,
                          expiry VARCHAR(5) NOT NULL ,
                          state VARCHAR(1) NOT NULL ,
                          mcc_list VARCHAR(100) NOT NULL
);

ALTER TABLE card
      ADD FOREIGN KEY (gpa_id)
      REFERENCES gpa(gpa_id);

CREATE TABLE transaction (
                          transaction_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                          transaction_amount DOUBLE  NOT NULL ,
                          merchant_id BIGINT NOT NULL,
                          transaction_date DATETIME NOT NULL,
                          card_id BIGINT NOT NULL
);

