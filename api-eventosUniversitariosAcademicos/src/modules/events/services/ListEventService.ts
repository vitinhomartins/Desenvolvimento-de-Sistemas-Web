import { AppDataSource } from "@shared/typeorm/data-source";
import Event from "../typeorm/entities/Event";

export default class ListEventService {
  public async execute(): Promise<Event[]> {
    const eventsRepository = AppDataSource.getRepository(Event);

    return await eventsRepository.find();
  }
}