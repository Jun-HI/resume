--테이블 삭제
drop table member_resume;

--시퀀스 삭제
drop sequence member_resume_resume_id_seq;

--이력서 회원테이블
create table member_resume (
  resume_id     number(8),    --이력서 관리번호
  id_person     varchar2(20), -- 이력서 아이디
  resume_title  varchar2(20),
  self_intro    clob,           -- 자기소개서
  cdate         timestamp default systimestamp,
  udate         timestamp default systimestamp
  );

--기본키 생성
alter table member_resume add constraint member_resume_resume_id_pk primary key (resume_id);

--제약조건
alter table member_resume add constraint member_resume_id_person_fk
foreign key (id_person) references MEMBER_PERSON(id_person);


--시퀀스

create sequence member_resume_resume_id_seq;
desc member_resume;

commit;


----이력서 상세테이블-----


--테이블 삭제
drop table resume_detail;

--시퀀스 삭제
drop sequence resume_detail_resume_detail_id_seq;

create table resume_detail (
  resume_detail_id number(8), --이력서 상세보기 관리번호
  resume_id        number(8), --이력서 관리번호 fk
  resume_item_id   number(3), --이력서 아이템
  id_person        varchar(20), --이력서 아이디 fk
  business_name    varchar(20),  -- 회사명
  business_date    number(8),    -- 근무일수
  business_work     varchar(30),  -- 근무명
  cdate            timestamp default systimestamp,
  udate            timestamp default systimestamp
  );

--기본키 생성
alter table resume_detail add constraint resume_detail_resume_detail_id_pk primary key(resume_detail_id);

--제약조건
alter table resume_detail add constraint resume_detail_resume_id_fk
foreign key (resume_id) REFERENCES member_resume(resume_id);

--시퀀스
create sequence resume_detail_resume_detail_id_seq;
desc resume_detail;

commit;





