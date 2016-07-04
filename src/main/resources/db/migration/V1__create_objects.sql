--------------------------------------------------------
--  File created - ponedjeljak-srpnja-04-2016
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CITY
--------------------------------------------------------

  CREATE TABLE "CITY"
   (	"ID" NUMBER(19,0),
	"NAME" VARCHAR2(255 CHAR),
	"POSTAL_CODE" NUMBER(10,0),
	"COUNTRY_ID" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table COUNTRY
--------------------------------------------------------

  CREATE TABLE "COUNTRY"
   (	"ID" NUMBER(19,0),
	"CODE" VARCHAR2(255 CHAR),
	"NAME" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table END_USER
--------------------------------------------------------

  CREATE TABLE "END_USER"
   (	"ID" NUMBER(19,0),
	"ADDRESS" VARCHAR2(255 CHAR),
	"FIRST_NAME" VARCHAR2(255 CHAR),
	"LANGUAGE" VARCHAR2(255 CHAR),
	"LAST_NAME" VARCHAR2(255 CHAR),
	"CITY_ID" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Index SYS_C008829
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C008829" ON "CITY" ("ID");
--------------------------------------------------------
--  DDL for Index SYS_C008831
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C008831" ON "COUNTRY" ("ID");
--------------------------------------------------------
--  DDL for Index SYS_C008833
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C008833" ON "END_USER" ("ID");
--------------------------------------------------------
--  Constraints for Table CITY
--------------------------------------------------------

  ALTER TABLE "CITY" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "CITY" MODIFY ("COUNTRY_ID" NOT NULL ENABLE);
  ALTER TABLE "CITY" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COUNTRY
--------------------------------------------------------

  ALTER TABLE "COUNTRY" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "COUNTRY" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table END_USER
--------------------------------------------------------

  ALTER TABLE "END_USER" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "END_USER" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CITY
--------------------------------------------------------

  ALTER TABLE "CITY" ADD CONSTRAINT "FK_ETXV5JFIK8LI7DEVKAAAQM6G7" FOREIGN KEY ("COUNTRY_ID")
	  REFERENCES "COUNTRY" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table END_USER
--------------------------------------------------------

  ALTER TABLE "END_USER" ADD CONSTRAINT "FK_2YGUW6UEAJYXGGKYIEMJJR2VF" FOREIGN KEY ("CITY_ID")
	  REFERENCES "CITY" ("ID") ENABLE;

CREATE SEQUENCE SEQ_CITY;
CREATE SEQUENCE SEQ_COUNTRY;
CREATE SEQUENCE SEQ_END_USER;