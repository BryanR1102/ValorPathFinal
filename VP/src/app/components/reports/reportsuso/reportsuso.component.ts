import { Component } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { RecursosService } from '../../../services/recursos.service';
import { RecursosPorTipo } from '../../../models/recursosTipoDTO';

@Component({
  selector: 'app-reportsuso',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './reportsuso.component.html',
  styleUrl: './reportsuso.component.css'
})
export class ReportsusoComponent {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'pie';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  constructor(private rs: RecursosService) { }
  ngOnInit(): void {
    this.rs.recursosPorTipo().subscribe((data) => {
      this.barChartLabels = data.map((item) => item.tipo);
      this.barChartData = [
        {
          data: data.map((item) => item.cantidad),
          label: 'Recursos Por Tipo',
          backgroundColor: ['#ee3007', '#f08e79', '#e98215'],
          borderColor: '#e94215',
          borderWidth: 1,
        },
      ];
    });
  }
}
