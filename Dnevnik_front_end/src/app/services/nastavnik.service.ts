import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse  } from '@angular/common/http';
import { MessageService } from './message.service';
import { AuthService } from './auth.service';
import { Ucenik } from '../models/Ucenik';
import { Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { tap, catchError } from 'rxjs/operators';
import { Ocena } from '../models/Ocena';
import { Odeljenje } from '../models/Odeljenje';
import { Predmet } from '../models/Predmet';

@Injectable()
export class NastavnikService {

  private sviUceniciUrl = environment.apiBaseUrl + '/nastavnik/pregled/ucenika';
  private ocenaUrl = environment.apiBaseUrl + '/nastavnik/ocene/';
  private ocenaDUrl = environment.apiBaseUrl + '/ocena/brisanje/ocene/';
  private ocenaUUrl = environment.apiBaseUrl + '/ocena/izmena/ocene/'
  private ocenaGUrl = environment.apiBaseUrl + '/ocena/get/'
  private odeljenjaUrl = environment.apiBaseUrl +'/nastavnik/odeljenja';
  private uceniciOdeljenjaUrl = environment.apiBaseUrl + '/nastavnik/ucenici/odeljenje/';
  private sviPredmetiUrl = environment.apiBaseUrl + '/nastavnik/moji/predmeti/';
  private addOcenaUrl = environment.apiBaseUrl;


  constructor(private httpClient: HttpClient,
              private messageService: MessageService,
              private authService: AuthService) { }


  getUcenike(): Observable<Ucenik[]> {
    return this.httpClient.get<Ucenik[]>(this.sviUceniciUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(ucenici => this.log('fetched predmete')),
        catchError(this.handleError('getNastavnike', []))
      );
  }

  getOcena(id: number): Observable<Ocena> {
    const url = `${this.ocenaGUrl}${id}/`;
    return this.httpClient.get<Ocena>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched ocena id=${id}`)),
      catchError(this.handleError<Ocena>(`getOcena id=${id}`))
    );
  }

  getOcene(idUcenik: number): Observable<Ocena[]> {
    const url = `${this.ocenaUrl}${idUcenik}/`;
    return this.httpClient.get<Ocena[]>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(ocene => this.log('fetched ocene')),
      catchError(this.handleError('getOcene', []))
    );
  }

  deleteOcenu(id: number): Observable<{}> {
    const url = `${this.ocenaDUrl}${id}/`; 
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError('deleteNastavnik'))
      );
  }

  
  updateOcena(ocena: Ocena): Observable<Ocena>{
    return this.httpClient
    .put<Ocena>(this.ocenaUUrl + ocena.idOcena + '/', ocena, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjena ocena sa id "${a.idOcena}"`)),
      catchError(this.handleError<Ocena>('updateOcena')));
  }

  getOdeljenja(): Observable<Odeljenje[]> {
    return this.httpClient.get<Odeljenje[]>(this.odeljenjaUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(odeljenja => this.log('fetched odeljenja')),
        catchError(this.handleError('getOdeljenja', []))
      );
  }

  getUcenikeOdeljenja(id: number): Observable<Ucenik[]> {
    const url = `${this.uceniciOdeljenjaUrl}${id}`;
    return this.httpClient.get<Ucenik[]>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched ucenike odeljenja id=${id}`)),
      catchError(this.handleError<Ucenik[]>(`getUcenikaOdeljenja id=${id}`))
    );
  }

  getPredmete(idOdeljenja: number): Observable<Predmet[]> {
    return this.httpClient.get<Predmet[]>(`${this.sviPredmetiUrl}${idOdeljenja}`, {headers: this.authService.getHeaders()})
      .pipe(
        tap(predmeti => this.log('fetched predmete')),
        catchError(this.handleError('getPredmete', []))
      );
  }

  addOcena(data): Observable<Ocena> {
    let ruta;
    let sendData = {
      visinaOcene: data.visinaOcene,
      opisOcene: data.opisOcene
    };
    
    switch (data.ocenaTip) {
      case '1':
        ruta = this.addOcenaUrl + '/ocena/redovna/pprvo/predmet/' + data.idPredmet + '/ucenik/' + data.idUcenik + '/';
        break;
      case '2':
        ruta = this.addOcenaUrl + '/polugodisna/predmet/' +  data.idPredmet +'/ucenik/' + data.idUcenik  + '/';
        break;
      case '3':
         ruta = this.addOcenaUrl + '/ocena/redovna/pdrugo/predmet/' + data.idPredmet + '/ucenik/' + data.idUcenik  + '/';
        break;
      case '4':
         ruta = this.addOcenaUrl + '/zakljucna/predmet/' +  data.idPredmet +'/ucenik/' + data.idUcenik + '/';
        break;
    
      default:
        break;
    }
    return this.httpClient
      .post<Ocena>(ruta, sendData , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodata ocena uceniku sa id:  "${data.idUcenik}"`)),
        catchError(this.handleError<Ocena>('addOcena')));
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