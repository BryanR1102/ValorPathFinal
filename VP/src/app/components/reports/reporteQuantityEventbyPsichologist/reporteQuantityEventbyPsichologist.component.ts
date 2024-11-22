import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { QuantityEventbyPsichologistDTO } from '../../../models/QuantityEventbyPsichologistDTO';
import { EventService } from '../../../services/event.service';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-reporteQuantityEventbyPsichologist',
  standalone: true,
  imports: [MatTableModule, MatIconModule, RouterModule, CommonModule],
  templateUrl: './reporteQuantityEventbyPsichologist.component.html',
  styleUrls: ['./reporteQuantityEventbyPsichologist.component.css']
})

export class ReporteQuantityEventbyPsichologistComponent implements OnInit{
    dataSource: MatTableDataSource<QuantityEventbyPsichologistDTO> = new MatTableDataSource();
    displayedColumns: string[] = ['psychologist', 'quantityEvents'];

    constructor(private eS: EventService) {}
    ngOnInit(): void {
        this.eS.getQuantityEventbyPsichologist().subscribe((data) => {
            this.dataSource = new MatTableDataSource(data);
        });
    }
}
