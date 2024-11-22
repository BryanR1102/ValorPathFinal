import { Forum } from './forum';
import { User } from './user';

export class Post {
  id: number = 0;
  title: string = '';
  comment: string = '';
  date: Date = new Date(Date.now());
  veteran: User = new User();
  forum: Forum = new Forum();
}

/**
 *     private int id;
    private String title;
    private String comment;
    private LocalDate date;
    private Users veteran;
    private Forum forum;
 * 
 */