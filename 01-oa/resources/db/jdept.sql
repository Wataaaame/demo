drop table if exists jdept;
create table jdept (
	deptno int primary key,
	dname varchar(255),
	loc varchar(255)
);

insert into jdept values(10, 'XiaoShou', 'Beijing');
insert into jdept values(20, 'YanFa', 'Shanghai');
insert into jdept values(30, 'JiShu', 'Guangzhou');
insert into jdept values(40, 'MeiTi', 'Shenzhen');
commit;

select * from jdept;