-- Run this file after testing the database. Note: Any accounts created during the testing phase 
-- will need to be removed manually.
-- This file removes the default accounts for the testing phase.

-- Select the 'uno' database.
use student_space;

-- Remove the accounts.
DELETE FROM uno_user WHERE username='Admin1';
DELETE FROM uno_user WHERE username='Admin2';
DELETE FROM uno_user WHERE username='Admin3';
DELETE FROM uno_user WHERE username='Admin4';
DELETE FROM uno_user WHERE username='Admin5';
DELETE FROM uno_user WHERE username='Admin6';
DELETE FROM uno_user WHERE username='Admin7';
DELETE FROM uno_user WHERE username='Admin8';
DELETE FROM uno_user WHERE username='Admin9';
DELETE FROM uno_user WHERE username='Admin10';

-- Commit the changes.
commit;
