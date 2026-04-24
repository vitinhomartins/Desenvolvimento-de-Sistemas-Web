import AppError from "@shared/errors/AppError";
import { AppDataSource } from "@shared/typeorm/data-source";
import Event from "../typeorm/entities/Event";

interface IRequest {
  id: string;
  title: string;
  description: string;
  location: string;
  organizer: string;
  event_date: Date;
  capacity: number;
}

export default class UpdateEventService {
  public async execute({
    id,
    title,
    description,
    location,
    organizer,
    event_date,
    capacity,
  }: IRequest): Promise<Event> {

    const eventsRepository = AppDataSource.getRepository(Event);

    const event = await eventsRepository.findOneBy({ id });

    if (!event) {
      throw new AppError("Evento não encontrado.");
    }

    event.title = title;
    event.description = description;
    event.location = location;
    event.organizer = organizer;
    event.event_date = event_date;
    event.capacity = capacity;

    await eventsRepository.save(event);

    return event;
  }
}