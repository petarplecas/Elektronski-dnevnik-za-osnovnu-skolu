import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Nastavnik } from '../models/Nastavnik';
import { environment } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';
import { Roditelj } from '../models/Roditelj';
import { Predmet } from '../models/Predmet';
import { NazivPredmeta } from '../models/NazivPredmeta';
import { Razred_SkolskaGodina } from '../models/Razred_SkolskaGodina';
import { Ucenik } from '../models/Ucenik';
import { Odeljenje } from '../models/Odeljenje';
import { Razred_SkolskaGodinaDTO } from '../models/Razred_SkolskaGodinaDTO';
import { Nas_Ode_pre } from '../models/Nas_Ode_Pre';




@Injectable()
export class AdminService {
  private url = environment.apiBaseUrl;
  private nastavnikUrl = environment.apiBaseUrl + '/nastavnik/';
  private roditeljUrl = environment.apiBaseUrl + '/roditelj/';
  private predmetUrl = environment.apiBaseUrl + '/predmet/';
  private ucenikUrl = environment.apiBaseUrl + '/ucenik/svi/';
  private vezniUrl = environment.apiBaseUrl + '/vezna/';

  constructor(private httpClient: HttpClient,
              private messageService: MessageService,
              private authService: AuthService) { }

  getNastavnike (): Observable<Nastavnik[]> {
    return this.httpClient.get<Nastavnik[]>(this.nastavnikUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(nastavnici => this.log('fetched nastavnike')),
        catchError(this.handleError<any>()))
  }

  getRoditelje (): Observable<Roditelj[]> {
    return this.httpClient.get<Roditelj[]>(this.roditeljUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(roditelji => this.log('fetched roditelje')),
        catchError(this.handleError<any>()))
  }

  getUcenike (): Observable<Ucenik[]> {
    return this.httpClient.get<Ucenik[]>(this.ucenikUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(ucenici => this.log('fetched ucenike')),
        catchError(this.handleError<any>()))
  }

  getNasOde (): Observable<Nas_Ode_pre[]> {
    return this.httpClient.get<Nas_Ode_pre[]>(this.vezniUrl, {headers: this.authService.getHeaders()})
      .pipe(
        tap(veze => this.log('fetched veze')),
        catchError(this.handleError<any>()))
  }

  getPredmete (): Observable<Predmet[]> {
    const url = `${this.predmetUrl}admin/`; 
    return this.httpClient.get<Predmet[]>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(predmeti => this.log('fetched predmete')),
        catchError(this.handleError<any>()))
  }

  getOdeljenja (): Observable<Odeljenje[]> {
    const url = `${this.url}/odeljenje/`; 
    return this.httpClient.get<Odeljenje[]>(url, {headers: this.authService.getHeaders()})
      .pipe(
        tap(odeljenja => this.log('fetched odeljenja')),
        catchError(this.handleError<any>()))
  }


  
  getNastavnik(id: number): Observable<Nastavnik> {
    const url = `${this.nastavnikUrl}${id}/`;
    return this.httpClient.get<Nastavnik>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched nastavnik id=${id}`)),
      catchError(this.handleError<any>()))
  }

  getRoditelj(id: number): Observable<Roditelj> {
    const url = `${this.roditeljUrl}${id}/`;
    return this.httpClient.get<Roditelj>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched roditelj id=${id}`)),
      catchError(this.handleError<any>()))
  }

  getUcenik(id: number): Observable<Ucenik> {
    const url = `${this.ucenikUrl}${id}`;
    return this.httpClient.get<Ucenik>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched ucenik id=${id}`)),
      catchError(this.handleError<any>()))
  }

  getPredmet(id: number): Observable<Predmet> {
    const url = `${this.predmetUrl}predmet/admin/${id}/`;
    return this.httpClient.get<Predmet>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched predmet id=${id}`)),
      catchError(this.handleError<any>()))
  }

  getNazivPredmeta(id: number): Observable<NazivPredmeta> {
    const url = `${this.url}/nazivpredmeta/${id}/`;
    return this.httpClient.get<NazivPredmeta>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched naziv predmeta id=${id}`)),
      catchError(this.handleError<any>()))
  }



  getPredmetNazive(): Observable<NazivPredmeta[]> {
    return this.httpClient.get<NazivPredmeta[]>('http://localhost:9000/api/v1/nazivpredmeta/', {headers: this.authService.getHeaders()})
      .pipe(
        tap(predmeti => this.log('fetched nazive predmeta')),
        catchError(this.handleError<any>()))
  }

  getRazredi(): Observable<Razred_SkolskaGodina[]> {
    return this.httpClient.get<Razred_SkolskaGodina[]>('http://localhost:9000/api/v1/admin/RazredSG/', {headers: this.authService.getHeaders()})
      .pipe(
        tap(razredi => this.log('fetched razrede')),
        catchError(this.handleError<any>()))
  }

  getRazred(id: number): Observable<Razred_SkolskaGodina> {
    const url = `${this.url}/admin/RazredSG/${id}/`;
    return this.httpClient.get<Razred_SkolskaGodina>(url, {headers: this.authService.getHeaders()})
    .pipe(
      tap(_ => this.log(`fetched razred id=${id}`)),
      catchError(this.handleError<any>()))
  }

  deleteNastavnik (id: number): Observable<{}> {
    const url = `${this.nastavnikUrl}${id}/`; 
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteRoditelj (id: number): Observable<{}> {
    const url = `${this.roditeljUrl}${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteUcenik(id: number): Observable<{}> {
    const url = `${this.url}/ucenik/${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteNasOde(id: number): Observable<{}> {
    const url = `${this.url}/vezna/${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteOdeljenje(id: number): Observable<{}> {
    const url = `${this.url}/odeljenje/${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deletePredmet (id: number): Observable<{}> {
    const url = `${this.predmetUrl}${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteRazred (id: number): Observable<{}> {
    const url = `${this.url}/admin/RazredSG/${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  deleteNazivPredmeta (id: number): Observable<{}> {
    const url = `${this.url}/nazivpredmeta/${id}/`;
    return this.httpClient.delete(url, {headers: this.authService.getHeaders()})
      .pipe(
        catchError(this.handleError<any>()))
  }

  updateNastavnik(nastavnik: Nastavnik): Observable<Nastavnik>{
    return this.httpClient
    .put<Nastavnik>(this.nastavnikUrl + nastavnik.idNastavnik + '/', nastavnik, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen Nastavnik sa id "${a.idNastavnik}"`)),
      catchError(this.handleError<any>()))
  }

  updateRoditelj(roditelj: Roditelj): Observable<Roditelj>{
    return this.httpClient
    .put<Roditelj>(this.roditeljUrl + roditelj.idRoditelj + '/', roditelj, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen roditelj sa id "${a.idRoditelj}"`)),
      catchError(this.handleError<any>()))
  }

  updateUcenik(ucenik: Ucenik): Observable<Ucenik>{
    const urla = `${this.url}/ucenik/${ucenik.idUcenik}/`;
    return this.httpClient
    .put<Ucenik>(urla, ucenik, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen ucenik sa id "${a.idUcenik}"`)),
      catchError(this.handleError<any>()))
  }

  updatePredmet(predmet: Predmet): Observable<Predmet>{
    const url = `${this.predmetUrl}${predmet.idPredmet}/`;
    return this.httpClient
    .put<Predmet>(url, predmet, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen predmet sa id "${a.idPredmet}"`)),
      catchError(this.handleError<any>()))
  }

  updateRazred(razred: Razred_SkolskaGodina): Observable<Razred_SkolskaGodina>{
    return this.httpClient
    .put<Razred_SkolskaGodina>(this.url + '/admin/RazredSG/' + razred.idRsg + '/', razred, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen razred sa id "${a.idRsg}"`)),
      catchError(this.handleError<any>()))
  }

  updateNazivPredmeta(naziv: NazivPredmeta): Observable<NazivPredmeta>{
    return this.httpClient
    .put<NazivPredmeta>(this.url + '/nazivpredmeta/' + naziv.idNazivPredmet + '/', naziv, {headers: this.authService.getHeaders()})
    .pipe(
      tap(a => this.log(`Izmenjen naziv predmeta sa id "${a.idNazivPredmet}"`)),
      catchError(this.handleError<any>()))
  }



  addNastavnik(nastavnik: Nastavnik): Observable<Nastavnik> {
    return this.httpClient
      .post<Nastavnik>(this.nastavnikUrl, nastavnik , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat nastavnik sa imenom "${a.ime}"`)),
        catchError(this.handleError<any>()))
  }

  
  addRoditelj(roditelj: Roditelj): Observable<Roditelj> {
    return this.httpClient
      .post<Roditelj>(this.roditeljUrl, roditelj , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat roditelj sa imenom "${a.ime}"`)),
        catchError(this.handleError<any>()))
  }

  addPredmet(data): Observable<Predmet> {
    let sendData = {
      nedeljniFondCasova: data.fond
    }
    return this.httpClient
      .post<Predmet>(`http://localhost:9000/api/v1/predmet/nazivPredmeta/${data.predmeti}/razred/${data.razredi}/`, sendData , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat predmet`)),
        catchError(this.handleError<any>()))
  }

  addUcenik(data): Observable<Ucenik> {
    let sendData = {
      ime: data.ime,
      prezime: data.prezime,
      korisnickoIme: data.korisnickoIme,
      lozinka: data.lozinka
    }
    return this.httpClient
      .post<Ucenik>(`http://localhost:9000/api/v1/ucenik/odeljenje/${data.odeljenja}/roditelj/${data.roditelji}/`, sendData , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat ucenik`)),
        catchError(this.handleError<any>()))
  }

  addNasOde(data): Observable<Nas_Ode_pre> {
    let sendData = {}
    return this.httpClient
      .post<Nas_Ode_pre>(`http://localhost:9000/api/v1/vezna/nastavnik/${data.nastavnici}/odeljenje/${data.odeljenja}/predmet/${data.predmeti}/`, sendData , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodata veza`)),
        catchError(this.handleError<any>()))
  }

  addOdeljenje(data): Observable<Odeljenje> {
    let sendData = {
      odeljenje: data.odeljenje,
      razredi: data.razred
    }
    return this.httpClient
      .post<Odeljenje>(`http://localhost:9000/api/v1/odeljenje/razred/${data.razredi}/`, sendData , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodato odeljenje`)),
        catchError(this.handleError<any>()))
  }

  addRazred(razred: Razred_SkolskaGodinaDTO): Observable<Razred_SkolskaGodinaDTO> {
    return this.httpClient
      .post<Razred_SkolskaGodinaDTO>(this.url + '/admin/RazredSG/', razred , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat razred za školsku godinu "${a.skolskaGodina}"`)),
        catchError(this.handleError<any>()))
  }

  addNazivPredmeta(naziv: NazivPredmeta): Observable<NazivPredmeta> {
    return this.httpClient
      .post<NazivPredmeta>(this.url + '/nazivpredmeta/', naziv , {headers: this.authService.getHeaders()})
      .pipe(
        tap(a => this.log(`Dodat naziv predmeta: "${a.nazivPredmeta}"`)),
        catchError(this.handleError<any>()))
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

  // private handleError(error: HttpErrorResponse) {
  //   if (error.error instanceof ErrorEvent) {
  //     // A client-side or network error occurred. Handle it accordingly.
  //     console.error('An error occurred:', error.error.message);
  //   } else {
  //     // The backend returned an unsuccessful response code.
  //     // The response body may contain clues as to what went wrong,
  //     console.error(
  //       `Backend returned code ${error.status}, ` +
  //       `body was: ${error.error.message}`)
  //   }
  //   // return an observable with a user-facing error message
  //   return throwError(
  //     'SomethrowErrorlease try again later.');
  // };
}
