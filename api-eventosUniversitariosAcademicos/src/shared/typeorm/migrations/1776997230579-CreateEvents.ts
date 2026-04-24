import { MigrationInterface, QueryRunner } from "typeorm";

export class CreateEvents1776997230579 implements MigrationInterface {
    name = 'CreateEvents1776997230579'

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.query(`CREATE TABLE "events" ("id" uuid NOT NULL DEFAULT uuid_generate_v4(), "title" character varying NOT NULL, "description" character varying NOT NULL, "location" character varying NOT NULL, "organizer" character varying NOT NULL, "event_date" TIMESTAMP NOT NULL, "capacity" integer NOT NULL, "created_at" TIMESTAMP NOT NULL DEFAULT now(), "updated_at" TIMESTAMP NOT NULL DEFAULT now(), CONSTRAINT "PK_40731c7151fe4be3116e45ddf73" PRIMARY KEY ("id"))`);
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.query(`DROP TABLE "events"`);
    }

}
