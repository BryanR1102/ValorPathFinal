import { Component, NgModule, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsorecursoService } from '../../../services/usorecurso.service';

import { ReactiveFormsModule } from '@angular/forms';
import { UseBetweenDateDTO } from '../../../models/UseBetweenDateDTO';
import { MatError, MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import {  MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { BaseChartDirective } from 'ng2-charts';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { MatInputModule } from '@angular/material/input';


@Component({
  selector: 'app-reports-usorecurso',
  standalone: true,
  imports: [ReactiveFormsModule,MatFormField,MatLabel,MatDatepickerModule,MatError,CommonModule,BaseChartDirective, MatFormFieldModule, MatInputModule],
  providers: [provideNativeDateAdapter()],
  templateUrl: './reports-usorecurso.component.html',
  styleUrl: './reports-usorecurso.component.css'
})
export class ReportsUsorecursoComponent {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = []; // Etiquetas para los recursos
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartData: ChartDataset[] = []; // Datos del gráfico

  dateForm: FormGroup; // Formulario para las fechas

  constructor(private usorecursoService: UsorecursoService, private fb: FormBuilder) {
    this.dateForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: ['']
    });
  }

  onSubmit(): void {
    const { fechaInicio, fechaFin } = this.dateForm.value;
    if (fechaInicio && fechaFin) {
      this.usorecursoService.usoentrefechas(fechaInicio, fechaFin).subscribe((data) => {
        this.barChartLabels = data.map(item => item.tiporecurso); // Actualiza las etiquetas
        this.barChartData = [
          {
            data: data.map(item => item.totalusos), // Actualiza los datos
            label: 'Usos por Recurso',
            backgroundColor: ['#42A5F5', '#66BB6A', '#FFA726', '#FF7043'], // Colores opcionales
            borderColor: '#1E88E5',
            borderWidth: 1,
          }
        ];
      });
    }
  }
}


