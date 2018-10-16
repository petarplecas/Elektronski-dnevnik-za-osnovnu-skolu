import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { MessageService } from './message.service';
import { AuthService } from './auth.service';
import { Observable, of } from 'rxjs';
import { Predmet } from '../models/Predmet';
import { tap, catchError } from 'rxjs/operators';
import { Ocena } from '../models/Ocena';

@Injectable()
export class UcenikService {

  private predmetUrl = environment.apiBaseUrl + '/predmet/predmet/';
  private ucenikUrl = environment.apiBaseUrl + '/ucenik/';
  private sviPredmetiUrl = environment.apiBaseUrl + '/predmet/'

  constructor(private httpClient: HttpClient,
              private messageService: MessageService,
              private authService: AuthService) { }

  getPredmete(): Observable<Predmet[]> {
    return this.httpClient.get<Predmet[]>(this.sviPredmetiUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(predmeti => this.log('fetched predmete')),
        catchError(this.handleError('getNastavnike', []))
      );
  }


  getPredmet(id: number): Observable<Predmet> {
    const url = `${this.predmetUrl}${id}`;
    return this.httpClient.get<Predmet>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(_ => this.log(`fetched predmet id=${id}`)),
        catchError(this.handleError<Predmet>(`getPredmet id=${id}`))
      );
  }
 

  getOceneI(idPredmet: number): Observable<Ocena[]> {
    const url = `${this.ucenikUrl}redovna/prvo/polugodiste/predmet/${idPredmet}/`;
    return this.httpClient.get<Ocena[]>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(oceneI => this.log('fetched ocene')),
        catchError(this.handleError<Ocena[]>('getOceneI', []))
      );
   }

   getPolugodiste(idPredmet: number): Observable<Ocena> {
    const url = `${this.ucenikUrl}polugodina/ocena/predmet/${idPredmet}/`;
    return this.httpClient.get<Ocena>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(pol => this.log('fetched ocena')),
        catchError(this.handleError<Ocena>('getPolugodiste'))
      );
   }

   
  getOceneII(idPredmet: number): Observable<Ocena[]> {
    const url = `${this.ucenikUrl}redovna/drugo/polugodiste/predmet/${idPredmet}/`;
    return this.httpClient.get<Ocena[]>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(oceneII => this.log('fetched ocene')),
        catchError(this.handleError<Ocena[]>('getOceneII', []))
      );
   }

   getZakljucena(idPredmet: number): Observable<Ocena> {
    const url = `${this.ucenikUrl}zakljucna/ocena/predmet/${idPredmet}/`;
    return this.httpClient.get<Ocena>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(kraj => this.log('fetched ocena')),
        catchError(this.handleError<Ocena>('getZakljucena'))
      );
   }




  private log(message: string) {
    this.messageService.add('NastavnikService: ' + message);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
