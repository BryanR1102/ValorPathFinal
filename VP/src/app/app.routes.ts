import { Routes } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { UserCreaeditaComponent } from './components/user/user-creaedita/user-creaedita.component';
import { RolComponent } from './components/rol/rol.component';
import { RolCreaeditaComponent } from './components/rol/rol-creaedita/rol-creaedita.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { AppointmentCreaeditaComponent } from './components/appointment/appointment-creaedita/appointment-creaedita.component';
import { ForumComponent } from './components/forum/forum.component';
import { ForumCreaeditaComponent } from './components/forum/forum-creaedita/forum-creaedita.component';
import { PostComponent } from './components/post/post.component';
import { PostCreaeditaComponent } from './components/post/post-creaedita/post-creaedita.component';
import { ReportsComponent } from './components/reports/reports.component';
import { ReportsPostComponent } from './components/reports/reports-post/reports-post.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { seguridadGuard } from './guard/seguridad.guard';
import { EventTypeComponent } from './components/event-type/event-type.component';
import { CreaeditaeventTypeComponent } from './components/event-type/creaeditaevent-type/creaeditaevent-type.component';
import { EventComponent } from './components/event/event.component';
import { CreaeditaeventComponent } from './components/event/creaeditaevent/creaeditaevent.component';
import { CitasaporfechaComponent } from './components/reports/citasporfecha/citasaporfecha.component';
import { CitascanceladasComponent } from './components/reports/citascanceladas/citascanceladas.component';
import { RecursosComponent } from './components/recursos/recursos.component';
import { CreaeditarecursosComponent } from './components/recursos/creaeditarecursos/creaeditarecursos.component';
import { UsorecursoComponent } from './components/usorecurso/usorecurso.component';
import { CreaeditausoComponent } from './components/usorecurso/creaeditauso/creaeditauso.component';
import { MostactiveforumComponent } from './components/reports/mostactiveforum/mostactiveforum.component';
import { QuantityforumxpsyComponent } from './components/reports/quantityforumxpsy/quantityforumxpsy.component';
import { ReporteQuantityEventbyPsichologistComponent } from './components/reports/reporteQuantityEventbyPsichologist/reporteQuantityEventbyPsichologist.component';
import { ReporteQuantityVeteraninEventComponent } from './components/reports/reporteQuantityVeteraninEvent/reporteQuantityVeteraninEvent.component';
import { ReportsAppointmentComponent } from './components/reports/reports-appointment/reports-appointment.component';
import { ReportsusoComponent } from './components/reports/reportsuso/reportsuso.component';
import { ReportsUsorecursoComponent } from './components/reports/reports-usorecurso/reports-usorecurso.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: "landing",
    component: LandingComponent,
  },
  {
    path: 'usuarios',
    component: UserComponent,
    children: [
      {
        path: 'nuevo',
        component: UserCreaeditaComponent,
      },
      {
        path: 'ediciones/:id',
        component: UserCreaeditaComponent,
      },
    ],
    canActivate: [seguridadGuard],
  },
  {
    path: 'roles',
    component: RolComponent,
    children: [
      {
        path: 'nuevo',
        component: RolCreaeditaComponent,
      },
      {
        path: 'ediciones/:id',
        component: RolCreaeditaComponent,
      },
    ],
    canActivate: [seguridadGuard],
  },
  {
    path: 'citas',
    component: AppointmentComponent,
    children: [
      {
        path: 'nuevo',
        component: AppointmentCreaeditaComponent,
      },
      {
        path: 'ediciones/:id',
        component: AppointmentCreaeditaComponent,
      },
    ],
    canActivate: [seguridadGuard],
  },
  {
    path: 'foros',
    component: ForumComponent,
    children: [
      {
        path: 'nuevo',
        component: ForumCreaeditaComponent,
      },
      {
        path: 'ediciones/:id',
        component: ForumCreaeditaComponent,
      },
    ],
    canActivate: [seguridadGuard],
  },
  {
    path: 'publicaciones',
    component: PostComponent,
    children: [
      {
        path: 'nuevo',
        component: PostCreaeditaComponent,
      },
      {
        path: 'ediciones/:id',
        component: PostCreaeditaComponent,
      },
    ],
    canActivate: [seguridadGuard],
  },
  {
    path: 'tipoevento',
    component: EventTypeComponent,
    children: [
      {
        path: 'nuevo',
        component: CreaeditaeventTypeComponent,
      },
      {
        path: 'ediciones/:id',
        component: CreaeditaeventTypeComponent,
      }
    ]
  },
  {
    path: 'eventos',
    component: EventComponent,
    children: [
      {
        path: 'nuevo',
        component: CreaeditaeventComponent
      },
      {
        path: 'ediciones/:id',
        component: CreaeditaeventComponent
      }

    ]
  },
  {
    path: 'eventostipos',
    component: EventTypeComponent,
    children: [
      {
        path: 'nuevo',
        component: CreaeditaeventTypeComponent
      },
      {
        path: 'ediciones/:id',
        component: CreaeditaeventTypeComponent
      }

    ]
  },
  {
    path: 'recursos',
    component: RecursosComponent,
    children: [
      {
        path: 'nuevo',
        component: CreaeditarecursosComponent
      },
      {
        path: 'ediciones/:id',
        component: CreaeditarecursosComponent
      }

    ]
  },
  {
    path: 'recursosusu',
    component: UsorecursoComponent,
    children: [
      {
        path: 'nuevo',
        component: CreaeditausoComponent
      },
      {
        path: 'ediciones/:id',
        component: CreaeditausoComponent
      }

    ]
  },
  {
    path: 'reportes',
    component: ReportsComponent,
    children: [
      {
        path: 'citasporfecha',
        component: CitasaporfechaComponent
      },
      {
        path: 'citascanceladas',
        component: CitascanceladasComponent
      },
      {
        path: 'mostactiveforum', component: MostactiveforumComponent
      },
      {
        path: 'quantityforumxpsy', component: QuantityforumxpsyComponent
      },
      {
        path: 'reporteQuantityEventbyPsichologist', component: ReporteQuantityEventbyPsichologistComponent
      },
      {
        path: 'reporteQuantityVeteraninEvent',
        component: ReporteQuantityVeteraninEventComponent
      },
      {
        path: 'reports-appointment',
        component: ReportsAppointmentComponent
      },
      {
        path: 'citascanceladas',
        component: CitascanceladasComponent
      },
      {
        path: 'reports-post',
        component: ReportsPostComponent
      },
      {
        path: 'reportsuso',
        component: ReportsusoComponent
      },
      {
        path: 'reports-usorecurso',
        component: ReportsUsorecursoComponent
      }
    ]
  },
];
