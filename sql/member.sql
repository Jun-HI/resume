--테이블 삭제
drop table MEMBER_PERSON;

--시퀀스삭제
drop sequence MEMBER_PERSON_PERSON_ID_PK_SEQ;

-------
--회원
-------
create table MEMBER_PERSON (
    PERSON_ID_PK              number(8),      --내부 관리 아이디
    ID_PERSON                 varchar2(20),   --로긴 아이디
    PW_PERSON                 varchar2(30),   --로긴 비밀번호
    NAME_PERSON               varchar2(30),   --이름
    BIRTH_PERSON              NUMBER(8),      --생년월일
    GENDER_PERSON             CHAR(3),        --성별(선택)
    ADDRESS_PERSON            varchar2(300),   --주소
    ADDRESSDETAIL_PERSON      varchar2(300),  --상세주소
    EMAIL_PERSON              varchar2(40),   --이메일
    PHONE_PERSON              number(11),     --휴대폰번호
    CDATE_DATE_PERSON          timestamp default systimestamp,         --생성일시
    UDATE_DATE_PERSON          timestamp default systimestamp          --수정일시
);

--기본키생성
alter table MEMBER_PERSON add Constraint MEMBER_PERSON_PERSON_ID_PK_PK primary key (PERSON_ID_PK);

--제약조건
alter table MEMBER_PERSON add constraint MEMBER_PERSON_ID_PERSON_UK unique (ID_PERSON);
alter table MEMBER_PERSON add constraint MEMBER_PERSON_email_PERSON_UK unique (email_person);
alter table MEMBER_PERSON modify ID_PERSON not null;
alter table MEMBER_PERSON modify PW_PERSON not null;
alter table MEMBER_PERSON modify NAME_PERSON not null;
alter table MEMBER_PERSON modify BIRTH_PERSON not null;
alter table MEMBER_PERSON modify ADDRESS_PERSON not null;
alter table MEMBER_PERSON modify ADDRESSDETAIL_PERSON not null;
alter table MEMBER_PERSON modify EMAIL_PERSON not null;
alter table MEMBER_PERSON modify PHONE_PERSON not null;
alter table MEMBER_PERSON modify CDATE_DATE_PERSON not null;
alter table MEMBER_PERSON modify UDATE_DATE_PERSON not null;

alter table MEMBER_PERSON add constraint MEMBER_PERSON_GENDER_PERSON_CK check(gender_person IN('남','여'));

--시퀀스
create sequence MEMBER_PERSON_PERSON_ID_PK_SEQ;
desc MEMBER_PERSON

commit;