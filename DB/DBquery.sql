-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- member Table Create SQL
CREATE TABLE member
(
    `uuid`       INT            NOT NULL    AUTO_INCREMENT COMMENT 'uuid', 
    `email`      VARCHAR(45)    NOT NULL    COMMENT '이메일', 
    `password`   VARCHAR(45)    NOT NULL    COMMENT '비번', 
    `name`       VARCHAR(45)    NOT NULL    COMMENT '이름', 
    `phone`      VARCHAR(45)    NOT NULL    COMMENT '연락처', 
    `role`       VARCHAR(20)    NOT NULL    COMMENT '역할', 
    `join_date`  TIMESTAMP      NOT NULL    DEFAULT NOW() COMMENT '가입일', 
    PRIMARY KEY (uuid)
);

ALTER TABLE member COMMENT '사용자';


-- member Table Create SQL
CREATE TABLE board
(
    `board_id`     INT             NOT NULL    AUTO_INCREMENT COMMENT '글번호', 
    `uuid`         INT             NOT NULL    COMMENT '작성자uuid', 
    `title`        VARCHAR(200)    NOT NULL    COMMENT '제목', 
    `content`      TEXT(3000)      NULL        COMMENT '내용', 
    `hit`          INT             NOT NULL    DEFAULT 0 COMMENT '조회수', 
    `upload_date`  TIMESTAMP       NOT NULL    DEFAULT NOW() COMMENT '작성일', 
    `category`     VARCHAR(30)     NOT NULL    DEFAULT '일반' COMMENT '카테고리명', 
    PRIMARY KEY (board_id)
);

ALTER TABLE board COMMENT '커뮤니티(QnA, 공지사항, 일반)';

ALTER TABLE board
    ADD CONSTRAINT FK_board_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- member Table Create SQL
CREATE TABLE lawyer
(
    `uuid`          INT             NOT NULL    COMMENT 'uuid', 
    `career`        TEXT(2000)      NULL        COMMENT '경력', 
    `introduction`  TEXT(2000)      NULL        COMMENT '자기소개', 
    `address`       VARCHAR(255)    NULL        COMMENT '주소', 
    `photo`         VARCHAR(255)    NULL        DEFAULT 'default.jpg' COMMENT '사진', 
    `dept`          VARCHAR(255)    NULL        COMMENT '소속', 
    `check`         CHAR(1)         NOT NULL    DEFAULT 'N' COMMENT '변호사합격확인', 
    PRIMARY KEY (uuid)
);

ALTER TABLE lawyer COMMENT '변호사';

ALTER TABLE lawyer
    ADD CONSTRAINT FK_lawyer_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- member Table Create SQL
CREATE TABLE matching
(
    `matching_id`       INT           NOT NULL    AUTO_INCREMENT COMMENT '자문번호', 
    `uuid`              INT           NOT NULL    COMMENT '신청자uuid', 
    `lawyer_uuid`       INT           NOT NULL    COMMENT '변호사uuid', 
    `create_date`       TIMESTAMP     NOT NULL    DEFAULT NOW() COMMENT '작성일', 
    `content`           TEXT(2000)    NOT NULL    COMMENT '내용', 
    `state`             VARCHAR(8)    NULL        COMMENT '진행상태', 
    `reservation_date`  TIMESTAMP     NULL        COMMENT '예약시간', 
    PRIMARY KEY (matching_id)
);

ALTER TABLE matching COMMENT '변호사자문';

ALTER TABLE matching
    ADD CONSTRAINT FK_matching_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE matching
    ADD CONSTRAINT FK_matching_lawyer_uuid_member_uuid FOREIGN KEY (lawyer_uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- member Table Create SQL
CREATE TABLE comment
(
    `comment_id`  INT             NOT NULL    AUTO_INCREMENT COMMENT '댓글번호', 
    `board_id`    INT             NOT NULL    COMMENT '글번호', 
    `uuid`        INT             NOT NULL    COMMENT '작성자uuid', 
    `content`     VARCHAR(200)    NOT NULL    COMMENT '내용', 
    PRIMARY KEY (comment_id)
);

ALTER TABLE comment COMMENT '댓글';

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_board_id_board_board_id FOREIGN KEY (board_id)
        REFERENCES board (board_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- member Table Create SQL
CREATE TABLE controversy
(
    `controversy_id`  INT             NOT NULL    AUTO_INCREMENT COMMENT '글번호', 
    `uuid`            INT             NOT NULL    COMMENT '작성자uuid', 
    `start_date`      TIMESTAMP       NOT NULL    DEFAULT NOW() COMMENT '시작날짜', 
    `end_date`        TIMESTAMP       NOT NULL    COMMENT '종료날짜', 
    `title`           VARCHAR(200)    NOT NULL    COMMENT '제목', 
    `content`         TEXT(2000)      NOT NULL    COMMENT '내용', 
    PRIMARY KEY (controversy_id)
);

ALTER TABLE controversy COMMENT '찬반논쟁';

ALTER TABLE controversy
    ADD CONSTRAINT FK_controversy_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- member Table Create SQL
CREATE TABLE proposal
(
    `opinion_id`  INT             NOT NULL    AUTO_INCREMENT COMMENT '의견번호', 
    `uuid`        INT             NOT NULL    COMMENT '작성자uuid', 
    `content`     VARCHAR(200)    NULL        COMMENT '내용', 
    `agree`       CHAR(1)         NULL        COMMENT '찬성,반대', 
    PRIMARY KEY (opinion_id)
);

ALTER TABLE proposal COMMENT '의견(찬반논쟁댓글)';

ALTER TABLE proposal
    ADD CONSTRAINT FK_proposal_uuid_member_uuid FOREIGN KEY (uuid)
        REFERENCES member (uuid) ON DELETE RESTRICT ON UPDATE RESTRICT;


