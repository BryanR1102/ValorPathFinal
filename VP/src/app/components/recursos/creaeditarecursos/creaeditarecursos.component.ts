import { Component, OnInit } from "@angular/core";
import { MatSelectModule } from "@angular/material/select";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from "@angular/forms";
import { CommonModule, NgIf } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { ActivatedRoute, Params, Router, RouterLink, RouterModule } from "@angular/router";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { recursos } from "../../../models/recursos";
import { RecursosService } from "../../../services/recursos.service";
import { User } from "../../../models/user";
import { MatCard, MatCardModule } from "@angular/material/card";


@Component({
  selector: "app-creaeditarecursos",
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
    RouterModule,
    MatCardModule
  ],
  templateUrl: "./creaeditarecursos.component.html",
  styleUrl: "./creaeditarecursos.component.css",
})
export class CreaeditarecursosComponent {
  form: FormGroup = new FormGroup({});
  recursos: recursos = new recursos();
  mensaje: string = "";
  id: number = 0;
  inputText = '';
  responseData: any = null;
  isLoading = false;
  error: string | null = null;
  edicion: boolean = false;
  Tipos  = [
    {value: 'Texto', viewValue: 'Texto'},
    {value: 'Video', viewValue: 'Video'},
    {value: 'Audio', viewValue: 'Audio'},
  ];
  constructor(
    private formBuild: FormBuilder,
    private rs: RecursosService,

    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data["id"];
      this.edicion = data["id"] != null;
      this.init()
    });
    this.form = this.formBuild.group({
      id: [''],
      autor: ['', Validators.required],
      nombre: ['', Validators.required],
      tipo: ['', Validators.required],
      descripcion: ["", Validators.required],

    });

    this.isLoading = true;
    this.error = null;

    this.rs.runLoop(this.inputText).subscribe({
      next: (data) => {
        this.responseData = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Error al consumir la API.';
        console.error(err);
        this.isLoading = false;
      },
    });
  }

  aceptar(): void {
 
    if (this.form.valid) {
      console.log('Formulario válido');
      this.recursos.id = this.form.value.id;
      this.recursos.autor = this.form.value.autor;
      this.recursos.titulo = this.form.value.nombre;
      this.recursos.tipo=this.form.value.tipo;
      this.recursos.descripcion=this.form.value.descripcion;

      if (this.edicion) {
        this.rs.update(this.recursos).subscribe(() => {
          this.rs.list().subscribe((data) => {
            this.rs.setList(data);
          },
          (error) => {
            console.error('Error al actualizar la lista:', error);
          });
        },
        (error) => {
          console.error('Error al insertar el recurso:', error);
        });
      } else {
        this.rs.insert(this.recursos).subscribe((data) => {
          this.rs.list().subscribe((data) => {
            this.rs.setList(data); // lista nueva a listacambio
          });
        });
      }
      this.router.navigate(['recursos']); // router  qme lleve a usuraios una vez dado click al aceptar
    } else {
      this.mensaje = 'Complete todos los campos, revise!!';
    }
  }
  init() {
    if (this.edicion) {
      this.rs.listId(this.id).subscribe((data) => {
        this.form = new FormGroup({
          id: new FormControl(data.id),
          autor: new FormControl(data.autor),
          nombre: new FormControl(data.titulo),
          tipo: new FormControl(data.tipo),
          descripcion: new FormControl(data.descripcion),
        });
      });
    }
}}
