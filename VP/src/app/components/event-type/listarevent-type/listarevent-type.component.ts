import { Component, OnInit, ViewChild } from '@angular/core';
import { EventTypeService } from '../../../services/event-type.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { EventType } from '../../../models/event-type';
import { MatPaginator } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-listarevent-type',
  standalone: true,
  imports: [MatTableModule, MatIconModule, RouterModule],
  templateUrl: './listarevent-type.component.html',
  styleUrl: './listarevent-type.component.css'
})
export class ListareventTypeComponent implements OnInit{

  dataSource: MatTableDataSource<EventType> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = ['c1', 'c2', 'c3', 'accion01'/* , 'accion02' */]

  constructor(private etS: EventTypeService){}
  ngOnInit(): void {
    this.etS.list().subscribe((data)=>{
      this.dataSource= new MatTableDataSource(data);

    })

    this.etS.getList().subscribe((data)=>{
      this.dataSource = new MatTableDataSource(data);
    })

  }

  eliminar(id: number) {
    this.etS.delete(id).subscribe(() => {
      this.etS.list().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
        this.etS.setlist(data);
      })
    })
  }
}
