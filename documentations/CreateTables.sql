
CREATE TABLE global_users(
id INTEGER NOT NULL PRIMARY KEY,
user_name VARCHAR(255) NOT NULL,
phone_number VARCHAR(255) NOT NULL,
email_address VARCHAR(255),
added_to_db_by_phone_number VARCHAR(255) FOREIGN KEY REFERENCES registered_users (phone_number)
)

CREATE TABLE registered_users(
phone_number VARCHAR(255) NOT NULL PRIMARY KEY,
user_name VARCHAR(255) NOT NULL,
email_address VARCHAR(255)
)

CREATE TABLE user_login_credentials(
phone_number VARCHAR(255) NOT NULL PRIMARY KEY,
password VARCHAR(255) NOT NULL
)

CREATE TABLE user_spam_scores (
phone_number VARCHAR(255) NOT NULL PRIMARY KEY,
spam_count INTEGER DEFAULT 0,
not_spam_count INTEGER DEFAULT 0
)






