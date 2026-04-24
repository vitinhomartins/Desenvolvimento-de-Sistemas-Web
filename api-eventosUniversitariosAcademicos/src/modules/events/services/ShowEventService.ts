import AppError from "@shared/errors/AppError";
import { AppDataSource } from "@shared/typeorm/data-source";
import Event from "../typeorm/entities/Event";

interface IRequest {
  id?: string;
  title?: string;
}

export default class ShowEventService {
  public async execute({ id, title }: IRequest): Promise<Event> {
    const eventsRepository = AppDataSource.getRepository(Event);

    if (!id && !title) {
      throw new AppError("Informe o id ou o título do evento.");
    }

    let event;

    if (id) {
      event = await eventsRepository.findOneBy({ id });
    } else if (title) {
      event = await eventsRepository.findOneBy({ title });
    }

    if (!event) {
      throw new AppError("Evento não encontrado.");
    }

    return event;
  }
}