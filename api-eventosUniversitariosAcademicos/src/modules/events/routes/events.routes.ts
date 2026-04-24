import { Router } from 'express';
import { celebrate, Joi, Segments } from 'celebrate';
import EventController from '../controllers/EventController';

const eventsRouter = Router();
const eventController = new EventController();

// GET /events
eventsRouter.get('/', async (req, res, next) => {
  try {
    await eventController.index(req, res, next);
  } catch (err) {
    next(err);
  }
});

// GET /events/:id
eventsRouter.get('/:id',
    celebrate({[Segments.PARAMS]: {
        id: Joi.string().uuid().required(),
    }}), async (req, res, next) => {
  try {
    await eventController.show(req, res, next);
  } catch (err) {
    next(err);
  }
});

// POST /events
eventsRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      title: Joi.string().required(),
      description: Joi.string().required(),
      location: Joi.string().required(),
      organizer: Joi.string().required(),
      event_date: Joi.date().required(),
      capacity: Joi.number().min(1).required(),
    },
  }),
  async (req, res, next) => {
    try {
      await eventController.create(req, res, next);
    } catch (err) {
      next(err);
    }
  },
);

// PUT /events/:id
eventsRouter.put(
  '/:id',
  celebrate({
    [Segments.PARAMS]: {
      id: Joi.string().uuid().required(),
    },
    [Segments.BODY]: {
      title: Joi.string().required(),
      description: Joi.string().required(),
      location: Joi.string().required(),
      organizer: Joi.string().required(),
      event_date: Joi.date().required(),
      capacity: Joi.number().min(1).required(),
    },
  }),
  async (req, res, next) => {
    try {
      await eventController.update(req, res, next);
    } catch (err) {
      next(err);
    }
  },
);

// DELETE /events/:id
eventsRouter.delete(
  '/:id',
  celebrate({
    [Segments.PARAMS]: {
      id: Joi.string().uuid().required(),
    },
  }),
  async (req, res, next) => {
    try {
      await eventController.delete(req, res, next);
    } catch (err) {
      next(err);
    }
  },
);
export default eventsRouter;