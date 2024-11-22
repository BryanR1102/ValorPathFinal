import { User } from './user';

export class Forum {
  id: number = 0;
  title: string = '';
  date: Date = new Date(Date.now());
  description: string = '';
  psychologist: User = new User();
}


/**
 *     private int id;
    private String title;
    private LocalDate date;
    private String description;
    private Users psychologist;
 * 
 */