import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { usorecurso } from '../models/usorecurso';
import { environment } from '../../environments/environment';
import { Observable, Subject } from 'rxjs';
import {  rmenosutilizadoDTO } from '../models/rmenosutilizadoDTO';
import { UseBetweenDateDTO } from '../models/UseBetweenDateDTO';
import { RecursosPorTipo } from '../models/recursosTipoDTO';
const base = environment.base
@Injectable({
  providedIn: 'root'
})
export class UsorecursoService {
  private url = `${base}/usorecurso`;
  private listaCambio = new Subject<usorecurso[]>();
  constructor(private http:HttpClient) { }
  list()
  {
    return this.http.get<usorecurso[]>(this.url)
  }
  insert(ur: usorecurso) {
    return this.http.post(this.url, ur);
  }

  setList(listaNueva: usorecurso[]) {
    this.listaCambio.next(listaNueva);
  }
  getList() {
    return this.listaCambio.asObservable(); 
  }

  listId(id: number) {
    return this.http.get<usorecurso>(`${this.url}/${id}`);
  }
  update(r: usorecurso) {
    return this.http.put(this.url, r);
  }
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`)
  }
  rmenosutilizado():Observable<rmenosutilizadoDTO[]>{
    return this.http.get<rmenosutilizadoDTO[]>(`${this.url}/menosutilizado`)
  }

  usoentrefechas(fechaInicio: string, fechaFin: string): Observable<UseBetweenDateDTO[]> {
    const params = { fechainicio: fechaInicio, fechafin: fechaFin };
    return this.http.get<UseBetweenDateDTO[]>(`${this.url}/maasutilizadoportiempo`, { params });
  }
}
