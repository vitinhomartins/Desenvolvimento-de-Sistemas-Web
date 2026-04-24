import { AppDataSource } from "@shared/typeorm/data-source";
import { Repository } from "typeorm";
import Event from "../entities/Event";

export default class EventRepository {
  private ormRepository: Repository<Event>;

  constructor() {
        this.ormRepository = AppDataSource.getRepository(Event);
    }
    public async findByTitle(title: string): Promise<Event | null> {
        const event = await this.ormRepository.findOne({
        where: { title },
        });
        return event;
    }
}