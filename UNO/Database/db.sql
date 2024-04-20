-- This file creates the database schema in the 'uno' database.

-- Select the 'student_space' database.
use student_space;

-- Drop the user table.
DROP TABLE uno_user;

-- Create the user table.
CREATE TABLE uno_user (
    username VARCHAR(32),
    password VARCHAR(32)
);

-- Add the primary key constraint to the user table.
ALTER TABLE uno_user   
    ADD CONSTRAINT uno_user_username_pk PRIMARY KEY (username);

-- Add the not null constraint to the user table.
ALTER TABLE uno_user
    MODIFY password VARCHAR(32) NOT NULL;
