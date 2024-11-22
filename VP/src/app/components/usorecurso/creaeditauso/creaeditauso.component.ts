import { CommonModule, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Params, Router, RouterLink } from '@angular/router';
import { User } from '../../../models/user';
import { UserService } from '../../../services/user.service';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { RecursosService } from '../../../services/recursos.service';
import { recursos } from '../../../models/recursos';
import { usorecurso } from '../../../models/usorecurso';
import { UsorecursoService } from '../../../services/usorecurso.service';

@Component({
  selector: 'app-creaeditauso',
  standalone: true,
  imports: [
    MatFormFieldModule,
    NgIf,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    CommonModule,
    RouterLink,
    MatCheckboxModule
  ],
  templateUrl: './creaeditauso.component.html',
  styleUrl: './creaeditauso.component.css'
})
export class CreaeditausoComponent {
  form: FormGroup = new FormGroup({});
  listaUsuarios: User[] = [];
  listaRecursos: recursos[] = [];
  mensaje: string = '';
  id: number = 0;
  edicion: boolean = false;
  recursoUse: usorecurso = new usorecurso()

  constructor(
    private formBuil: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private uS: UserService,
    private rS:RecursosService,
    private urS: UsorecursoService
  ) { }
  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data['id'];
      this.edicion = data['id'] != null;
      this.init();
    });

    this.form = this.formBuil.group({
      id: [''],
      fecha: ['', Validators.required],
      usuario: ['', Validators.required],
      recurso: ['', Validators.required],
    });

    this.uS.list().subscribe((data) => {
      this.listaUsuarios = data;
    });

    this.rS.list().subscribe((data) => {
      this.listaRecursos = data;
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.recursoUse.iduso = this.form.value.id;
      this.recursoUse.usuario = this.form.value.usuario;
      this.recursoUse.recurso = this.form.value.recurso;
      this.recursoUse.fecha = this.form.value.fecha;

      if (this.edicion) {
        this.urS.update(this.recursoUse).subscribe((data) => {
          this.urS.list().subscribe((data) => {
            this.urS.setList(data);
          });
        });
      } else {
        this.urS.insert(this.recursoUse).subscribe((data) => {
          this.urS.list().subscribe((data) => {
            this.urS.setList(data);
          });
        });
      }
      this.router.navigate(['recursosusu']);
    } else {
      this.mensaje = 'Ingrese todos los campos!!';
    }
  }

  init() {
    if (this.edicion) {
      this.urS.listId(this.id).subscribe((data) => {
        this.form = new FormGroup({
          id: new FormControl(data.iduso),
          fecha: new FormControl(data.fecha),
          usuario: new FormControl(data.usuario),
          recurso: new FormControl(data.recurso),
        });
      });
    }
  }
}
