import { Request, Response, NextFunction } from 'express';

import CreateEventService from '../services/CreateEventService';
import DeleteEventService from '../services/DeleteEventService';
import ListEventService from '../services/ListEventService';
import ShowEventService from '../services/ShowEventService';
import UpdateEventService from '../services/UpdateEventService';
import { AppDataSource } from '@shared/typeorm/data-source';

export default class EventController {

public async index(
        request: Request,
        response: Response,
        next: NextFunction,
    ): Promise<Response | void> {
        try {
        const listEvents = new ListEventService();
        const events = await listEvents.execute();

        return response.status(200).json(events);
        } catch (err) {
        next(err);
        }
}


public async show(
  request: Request,
  response: Response,
  next: NextFunction,
): Promise<Response | void> {
  try {
    const id = request.params.id as string | undefined;
    const title = request.query.title as string | undefined;

    const showEvent = new ShowEventService();

    const data: { id?: string; title?: string } = {};

    if (id) data.id = id;
    if (title) data.title = title;

    const event = await showEvent.execute(data);

    return response.status(200).json(event);
  } catch (err) {
    next(err);
  }
}

  public async create(
    request: Request,
    response: Response,
    next: NextFunction,
  ): Promise<Response | void> {
    try {
      const {
        title,
        description,
        location,
        organizer,
        event_date,
        capacity,
      } = request.body;

      const createEvent = new CreateEventService();

      const event = await createEvent.execute({
        title,
        description,
        location,
        organizer,
        event_date,
        capacity,
      });

      return response.status(201).json(event);
    } catch (err) {
      next(err);
    }
  }

  public async update(
    request: Request,
    response: Response,
    next: NextFunction,
  ): Promise<Response | void> {
    try {
      const id = request.params.id as string;

      const {
        title,
        description,
        location,
        organizer,
        event_date,
        capacity,
      } = request.body;

      const updateEvent = new UpdateEventService();

      const event = await updateEvent.execute({
        id,
        title,
        description,
        location,
        organizer,
        event_date,
        capacity,
      });

      return response.status(200).json(event);
    } catch (err) {
      next(err);
    }
  }

  public async delete(
    request: Request,
    response: Response,
    next: NextFunction,
  ): Promise<Response | void> {
    try {
      const id = request.params.id as string;

      const deleteEvent = new DeleteEventService();

      await deleteEvent.execute({ id });

      return response.status(204).send();
    } catch (err) {
      next(err);
    }
  }
}