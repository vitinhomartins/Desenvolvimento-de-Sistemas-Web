import "reflect-metadata";
import { DataSource } from "typeorm";
import path from "path";
import Event from "@modules/events/typeorm/entities/Event";
export const AppDataSource = new DataSource({
    type: "postgres",
    host: "localhost",
    port: 5433,
    username: "postgres",
    password: "docker",
    database: "api-eventosacademicos",
    synchronize: true, 
    logging: false,
    entities: [Event],
    migrations: [path.join("src", "shared", "typeorm", "migrations", "*.ts")],
    subscribers: [],
});