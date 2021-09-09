-- Table: epam_training.skills

-- DROP TABLE epam_training.skills;

CREATE TABLE epam_training.skills
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT skills_pkey PRIMARY KEY (id)
)


-- Table: epam_training.subjects

-- DROP TABLE epam_training.subjects;

CREATE TABLE epam_training.subjects
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default",
    tutor_name character varying COLLATE pg_catalog."default",
    CONSTRAINT subjects_pkey PRIMARY KEY (id)
)

-- Table: epam_training.students

-- DROP TABLE epam_training.students;

CREATE TABLE epam_training.students
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    date_of_birth date,
    phone_number character varying COLLATE pg_catalog."default",
    created_date date,
    updated_date date,
    primary_skill integer,
    CONSTRAINT students_pkey PRIMARY KEY (id),
    CONSTRAINT students_skills_fkey FOREIGN KEY (primary_skill)
        REFERENCES epam_training.skills (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

-- Index: fki_students_skills_fkey

-- DROP INDEX epam_training.fki_students_skills_fkey;

CREATE INDEX fki_students_skills_fkey
    ON epam_training.students USING btree
    (primary_skill ASC NULLS LAST)
    

-- Table: epam_training.exam_results

-- DROP TABLE epam_training.exam_results;

CREATE TABLE epam_training.exam_results
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    student_id integer,
    subject_id integer,
    mark integer,
    CONSTRAINT exam_results_pkey PRIMARY KEY (id),
    CONSTRAINT exam_results_students_fkey FOREIGN KEY (student_id)
        REFERENCES epam_training.students (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT exam_results_subjects_fkey FOREIGN KEY (subject_id)
        REFERENCES epam_training.subjects (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)


-- Index: fki_exam_results_students_fkey

-- DROP INDEX epam_training.fki_exam_results_students_fkey;

CREATE INDEX fki_exam_results_students_fkey
    ON epam_training.exam_results USING btree
    (student_id ASC NULLS LAST);
-- Index: fki_exam_results_subjects_fkey

-- DROP INDEX epam_training.fki_exam_results_subjects_fkey;

CREATE INDEX fki_exam_results_subjects_fkey
    ON epam_training.exam_results USING btree
    (subject_id ASC NULLS LAST);


