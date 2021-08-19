do $$
begin
for r in 1..1000 loop
insert into epam_training.skills (name) values(concat ('Skill',r));
end loop;
end;
$$;
---------------------
do $$
begin
for r in 1..1000 loop
insert into epam_training.subjects (name, tutor_name) values(concat ('Subject',r), concat ('Tutor',r));
end loop;
end;
$$;
------------------------------------------------------
do $$
declare
y integer := 2000;
m integer := 1;
d integer := 1;
ph integer;
min_skill_id integer;
max_skill_id integer;
begin
for r in 1..100000 loop
select min(id) into strict min_skill_id from epam_training.skills;
select max(id) into strict max_skill_id from epam_training.skills;
y := (floor(random() * (2010 - 1950 + 1)) + 1950);
m := (floor(random() * (12 - 1 + 1)) + 1);
d := (floor(random() * (28 - 1 + 1)) + 1);
ph := (floor(random() * (999999999 - 100000000 + 1)) + 100000000);

insert into epam_training.students (first_name, last_name, date_of_birth, phone_number, created_date, updated_date, primary_skill) 
values (concat('FirstName', r), concat('LastName', r%2), make_date ( y , m, d), ph, make_date(2021,08,17), NOW(), (floor(random() * (max_skill_id - min_skill_id + 1)) + min_skill_id));
end loop;
end;
$$;
-----------------------------------
do $$
declare
student integer;
min_student_id integer;
max_student_id integer;
subject integer;
min_subject_id integer;
max_subject_id integer;
mark integer;

begin
select min(id) into strict min_student_id from epam_training.students;
select max(id) into strict max_student_id from epam_training.students;
select min(id) into strict min_subject_id from epam_training.subjects;
select max(id) into strict max_subject_id from epam_training.subjects;
for r in 1..1000000 loop
student := (floor(random() * (max_student_id - min_student_id + 1)) + min_student_id);
subject := (floor(random() * (max_subject_id - min_subject_id + 1)) + min_subject_id);
mark := (floor(random() * (10 - 1 + 1)) + 1);
insert into epam_training.exam_results (student_id, subject_id, mark) 
values (student, subject, mark);
end loop;
end;
$$;
