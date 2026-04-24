import AppError from "@shared/errors/AppError";
import { AppDataSource } from "@shared/typeorm/data-source";
import Event from "../typeorm/entities/Event";

interface IRequest{
    title: string;
    description: string;
    location: string;
    organizer: string;
    event_date: Date;
    capacity: number;
}

export default class CreateEventService{
    public async execute({title, description, location, organizer, event_date, capacity} : IRequest): Promise<Event> {
        const eventsRepository = AppDataSource.getRepository(Event);
        const eventExists = await eventsRepository.findOne({
            where: {title},
        });
        if(eventExists){
            throw new AppError("Já existe um evento criado com esse título!");
        }
        const event = eventsRepository.create({
            title,
            description,
            location,
            organizer,
            event_date,
            capacity,
        });
        await eventsRepository.save(event);
        return event;
    }
}