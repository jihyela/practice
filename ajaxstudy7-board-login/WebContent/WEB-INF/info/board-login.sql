create table member2(
	id varchar2(100) primary key,
	name varchar2(100) not null,
	password varchar2(100) not null
)

select count(*) from member2 where id='java' and password='1234';

create sequence borad2_seq nocache;

create table board2(
	no number primary key,
	title varchar2(100) not null,
	content clob not null,
	hits number default 0,
	time_posted date not null,
	id varchar2(100) not null,
	constraint fk_member2 foreign key(id) references member2(id)
)

insert into member2(id, name, password) values('java', '정우성', '1234');
insert into member2(id, name, password) values('jsp', '아이유', '1234');
insert into member2(id, name, password) values('sql', '김태희', '1234');

insert into board2(no, title, content, time_posted, id)
values(borad2_seq.nextval, '테스트', '테스트입니다', sysdate, 'java');
insert into board2(no, title, content, time_posted, id)
values(borad2_seq.nextval, '테스트2', '테스트입니다', sysdate, 'jsp');
insert into board2(no, title, content, time_posted, id)
values(borad2_seq.nextval, '테스트3', '테스트입니다', sysdate, 'sql');
insert into board2(no, title, content, time_posted, id)
values(borad2_seq.nextval, '테스트4', '테스트입니다', sysdate, 'sql');

select b.no, b.title, m.name, b.content, b.hits, b.time_posted, b.id
from board2 b, member2 m where b.id ='java' and m.id ='java';

select A.no, A.title, B.name, A.hits, A.time_posted, A.id
from (select no,title,to_char(time_posted,'YYYY.MM.DD') 
as time_posted,hits,ceil(rownum/5) as page, id from board2) A, member2 B 
where B.id = A.id and A.page = 1;

select name from member2 where id='java' and password='1234';

select no,title,writer,content,to_char(time_posted,'YYYY.MM.DD HH24:MI:SS') as time_posted,hits from board_inst where no=?

select b.no, b.title, m.name, b.content, b.hits, to_char(b.time_posted,'YYYY.MM.DD HH24:MM:SS')as time_posted, b.id
from board2 b, member2 m where b.id = m.id and b.no='1';

select borad2_seq.nextval from dual;

insert into board2(no, title, content, time_posted, id)
values(borad2_seq.nextval, '테스트', '테스트입니다', sysdate, 'java');

update board2 set title='ㅋㅋㅋㅋ',content='비온다...' where no=2;


select * from BOARD2;
