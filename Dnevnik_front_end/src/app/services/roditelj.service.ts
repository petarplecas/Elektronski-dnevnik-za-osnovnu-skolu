import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MessageService } from './message.service';
import { AuthService } from './auth.service';
import { Ucenik } from '../models/Ucenik';
import { Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { tap, catchError } from 'rxjs/operators';
import { Predmet } from '../models/Predmet';
import { Ocena } from '../models/Ocena';

@Injectable()
export class RoditeljService {
  predmet: Predmet[];
  private url = environment.apiBaseUrl + '/roditelj/deca/';
  private predmetUrl = environment.apiBaseUrl + '/predmet/ucenik/';
  private ocenaUrl = environment.apiBaseUrl + '/roditelj/ocene/';
  private predmetRUrl = environment.apiBaseUrl + '/predmet/roditelj/predmet/';

  constructor(private httpClient: HttpClient,
              private messageService: MessageService,
              private authService: AuthService) { }

  

  getDecu(): Observable<Ucenik[]> {
    return this.httpClient.get<Ucenik[]>(this.url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(deca => this.log('fetched decu')),
        catchError(this.handleError('getDecu', []))
      );
  }

  getPredmeteDeteta(id: number): Observable<Predmet[]> {
    const url = `${this.predmetUrl}${id}`;
    return this.httpClient.get<Predmet[]>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(predmeti => this.log('fetched predmete')),
        catchError(this.handleError('getPredmete', []))
      );
  }

  getPredmet(id: number): Observable<Predmet> {
    const url = `${this.predmetRUrl}${id}`;
    return this.httpClient.get<Predmet>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(_ => this.log(`fetched predmet id=${id}`)),
        catchError(this.handleError<Predmet>(`getPredmet id=${id}`))
      );
  }

  getOcene(idPredmet:number): Observable<Ocena[]> {
    const url = `${this.ocenaUrl}${idPredmet}/`;
    return this.httpClient.get<Ocena[]>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(ocene => this.log('fetched ocene')),
      catchError(this.handleError('getOcene', []))
    );
  }

  private log(message: string) {
    this.messageService.add('RoditeljService: ' + message);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
