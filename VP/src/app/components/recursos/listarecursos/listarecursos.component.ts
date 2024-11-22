import { Component, OnInit } from "@angular/core";
import { RecursosService } from "../../../services/recursos.service";
import { MatPaginatorModule, PageEvent } from "@angular/material/paginator";
import { MatTableModule } from "@angular/material/table";
import { MatCardModule } from "@angular/material/card";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { CommonModule } from "@angular/common";
import { RouterLink } from "@angular/router";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { recursos } from "../../../models/recursos";

@Component({
  selector: "app-listarecursos",
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
    RouterLink,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: "./listarecursos.component.html",
  styleUrl: "./listarecursos.component.css",
})
export class ListarecursosComponent implements OnInit {
  datos: any[] = [];
  filteredData: any[] = [];
  paginatedData: any[] = [];
  pageSize: number = 5;
  currentPage: number = 0;

  constructor(private rs: RecursosService) { }

  ngOnInit(): void {
    this.rs.list().subscribe((data) => {
      this.datos = data;
      this.filteredData = data;
      this.updatePaginatedData();
    });

    this.rs.getList().subscribe(data => {
      this.datos = data;
      this.filteredData = data;
      this.updatePaginatedData();
    })
  }


  pageChange(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.updatePaginatedData();
  }

  updatePaginatedData() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedData = this.filteredData.slice(startIndex, endIndex);
  }

  eliminar(id: number) {
    const confirmacion = confirm('¿Estás seguro de que deseas eliminar este recurso?');
    if (confirmacion) {
      this.datos = this.datos.filter((elemento) => elemento.id !== id);
      this.rs.delete(id).subscribe({
        next: () => {
         
          this.rs.getList().subscribe((data) => {
            this.datos = data; 
            alert('Recurso eliminado');
          });
        },
        error: (err) => {
          console.error('Error al eliminar el recurso:', err);
        },
      });
    }
  }
}
