import { Router } from 'express';
import eventsRouter from '@modules/events/routes/events.routes';

const routes = Router();

routes.use('/events', eventsRouter);

routes.get('/', (request, response) => {
  return response.json({ message: 'API de Eventos funcionando!!!' });
});

export default routes;