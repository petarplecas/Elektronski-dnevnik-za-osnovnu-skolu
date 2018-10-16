import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { UcenikComponent } from './ucenik/ucenik.component';
import { NastavnikComponent } from './nastavnik/nastavnik.component';
import { AdminComponent } from './admin/admin.component';
import { RoditeljComponent } from './roditelj/roditelj.component';
import { IzmenaOcenaComponent } from './nastavnik/izmena-ocena/izmena-ocena.component';
import { DodavanjeOcenaComponent } from './nastavnik/dodavanje-ocena/dodavanje-ocena.component';
import { PregledOcenaComponent } from './roditelj/pregled-ocena/pregled-ocena.component';
import { OceneComponent } from './ucenik/ocene/ocene.component';
import { LoginComponent } from './login/login.component'
import { MessageService } from './services/message.service';
import { NastavnikService } from './services/nastavnik.service';
import { MessageListComponent } from './message-components/message-list/message-list.component';
import { AddNastavnikComponent } from './admin/add-nastavnik/add-nastavnik.component';
import { AdminService } from './services/admin.service';
import { UcenikService } from './services/ucenik.service';
import { PutNastavnikComponent } from './admin/put-nastavnik/put-nastavnik.component';
import { GetNastavnikeComponent } from '../app/admin/get-nastavnike/get-nastavnike.component';
import { GetPredmeteComponent } from './ucenik/get-predmete/get-predmete.component';
import { RoditeljService } from './services/roditelj.service';
import { GetPredmeteDetetaComponent } from './roditelj/get-predmete-deteta/get-predmete-deteta.component';
import { GetUcenikeComponent } from './nastavnik/get-ucenike/get-ucenike.component';
import { GetOceneUcenikaComponent } from './nastavnik/get-ocene-ucenika/get-ocene-ucenika.component';
import { GetUcenikeOdeljenjaComponent } from './nastavnik/dodavanje-ocena/get-ucenike-odeljenja/get-ucenike-odeljenja.component';
import { AddRoditeljComponent } from './admin/add-roditelj/add-roditelj.component';
import { GetRoditeljeComponent } from './admin/get-roditelje/get-roditelje.component';
import { PutRoditeljComponent } from './admin/put-roditelj/put-roditelj.component';
import { PutPredmetComponent } from './admin/put-predmet/put-predmet.component';
import { AddPredmetComponent } from './admin/add-predmet/add-predmet.component';
import { GetPredmeteAdminaComponent } from './admin/get-predmete-admina/get-predmete-admina.component';
import { GetUcenikeAdminaComponent } from './admin/get-ucenike-admina/get-ucenike-admina.component';
import { PutUcenikaAdminaComponent } from './admin/put-ucenika-admina/put-ucenika-admina.component';
import { AddOcenaComponent } from './nastavnik/dodavanje-ocena/add-ocena/add-ocena.component';
import { AddUcenikAdminComponent } from './admin/add-ucenik-admin/add-ucenik-admin.component';
import { GetRazredComponent } from './admin/get-razred/get-razred.component';
import { PutRazredComponent } from './admin/put-razred/put-razred.component';
import { AddRazredComponent } from './admin/add-razred/add-razred.component';
import { GetNazivePredmetaComponent } from './admin/get-nazive-predmeta/get-nazive-predmeta.component';
import { AddNazivPredmetaComponent } from './admin/add-naziv-predmeta/add-naziv-predmeta.component';
import { PutNazivPredmetaComponent } from './admin/put-naziv-predmeta/put-naziv-predmeta.component';
import { GetOdeljenjaComponent } from './admin/get-odeljenja/get-odeljenja.component';
import { AddOdeljenjeComponent } from './admin/add-odeljenje/add-odeljenje.component';
import { PutOdeljenjeComponent } from './admin/put-odeljenje/put-odeljenje.component';
import { GetNasOdeComponent } from './admin/get-nas-ode/get-nas-ode.component';
import { AddNasOdeComponent } from './admin/add-nas-ode/add-nas-ode.component';
import { PutNasOdeComponent } from './admin/put-nas-ode/put-nas-ode.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UcenikComponent,
    NastavnikComponent,
    AdminComponent,
    RoditeljComponent,
    IzmenaOcenaComponent,
    DodavanjeOcenaComponent,
    PregledOcenaComponent,
    OceneComponent,
    LoginComponent,
    MessageListComponent,
    AddNastavnikComponent,
    PutNastavnikComponent,
    GetNastavnikeComponent,
    GetPredmeteComponent,
    GetPredmeteDetetaComponent,
    GetUcenikeComponent,
    GetOceneUcenikaComponent,
    GetUcenikeOdeljenjaComponent,
    AddRoditeljComponent,
    GetRoditeljeComponent,
    PutRoditeljComponent,
    PutPredmetComponent,
    AddPredmetComponent,
    GetPredmeteAdminaComponent,
    GetUcenikeAdminaComponent,
    PutUcenikaAdminaComponent,
    AddOcenaComponent,
    AddUcenikAdminComponent,
    GetRazredComponent,
    PutRazredComponent,
    AddRazredComponent,
    GetNazivePredmetaComponent,
    AddNazivPredmetaComponent,
    PutNazivPredmetaComponent,
    GetOdeljenjaComponent,
    AddOdeljenjeComponent,
    PutOdeljenjeComponent,
    GetNasOdeComponent,
    AddNasOdeComponent,
    PutNasOdeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [MessageService, NastavnikService, AdminService, UcenikService, RoditeljService],
  bootstrap: [AppComponent]
})
export class AppModule { }
