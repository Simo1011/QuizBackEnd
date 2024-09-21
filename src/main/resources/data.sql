-- Insert into subject table
INSERT INTO subject (id, name) VALUES (1, 'Math');
INSERT INTO subject (id, name) VALUES (2, 'Science');

-- Insert into question table (linked to subjects)
INSERT INTO question (id, question_text, correct_answer, subject_id) VALUES (1, 'What is 2+2?', '4', 1);
INSERT INTO question (id, question_text, correct_answer, subject_id) VALUES (2, 'What is H2O?', 'Water', 2);
