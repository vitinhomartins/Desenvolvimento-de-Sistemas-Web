import AppError from "@shared/errors/AppError";
import { AppDataSource } from "@shared/typeorm/data-source";
import Event from "../typeorm/entities/Event";

interface IRequest {
  id: string;
}

export default class DeleteEventService {
  public async execute({ id }: IRequest): Promise<void> {

    const eventsRepository = AppDataSource.getRepository(Event);

    const event = await eventsRepository.findOneBy({ id });

    if (!event) {
      throw new AppError("Evento não encontrado.");
    }

    await eventsRepository.remove(event);
  }
}