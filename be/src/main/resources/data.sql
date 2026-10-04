insert into project("ID","NAME","DESCRIPTION", "PROGRESS")
    values ('9974af9a-a233-46af-ac4d-31b4d00c3bf2','ExampleProject','Example Project Description', 45);

insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
    values (false,'Update Api Description', '2026-11-15', 'Update API','9974af9a-a233-46af-ac4d-31b4d00c3bf2');
insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
    values (false,'Update FE Description', '2026-11-15', 'Update FE', '9974af9a-a233-46af-ac4d-31b4d00c3bf2');
insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
    values (true,'Add  data.sql description', '2026-09-15', 'Add data.sql', '9974af9a-a233-46af-ac4d-31b4d00c3bf2');

insert into project("ID","NAME","DESCRIPTION", "PROGRESS")
values ('9974af9a-a233-46af-ac4d-31b4d00c3bf3','ExampleProject2','Example Project 2 Description', 70);

insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
values (false,'Update 2 Api Description', '2026-11-15', 'Update API 2', '9974af9a-a233-46af-ac4d-31b4d00c3bf3');
insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
values (false,'Update 2 FE Description', '2026-11-15', 'Update FE 2', '9974af9a-a233-46af-ac4d-31b4d00c3bf3');
insert into task("COMPLETED" ,"DESCRIPTION", "DUE_DATE", "NAME", "PROJECT_ID")
values (true,'Add data.sql description 2', '2026-09-15', 'Add data.sql 2', '9974af9a-a233-46af-ac4d-31b4d00c3bf3');

