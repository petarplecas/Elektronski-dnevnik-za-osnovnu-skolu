import { NgModule } from '@angular/core';
import { RouterModule, Route } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { UcenikComponent } from './ucenik/ucenik.component';
import { NastavnikComponent } from './nastavnik/nastavnik.component';
import { RoditeljComponent } from './roditelj/roditelj.component';

import { AdminFilterService } from './services/filters/admin-filter.service'
import { UcenikFilterService } from './services/filters/ucenik-filter.service'
import { RoditeljFilterService } from './services/filters/roditelj-filter.service'
import { NastavnikFilterService } from './services/filters/nastavnik-filter.service'
import { AddNastavnikComponent } from './admin/add-nastavnik/add-nastavnik.component';
import { PutNastavnikComponent } from './admin/put-nastavnik/put-nastavnik.component';
import { GetNastavnikeComponent } from './admin/get-nastavnike/get-nastavnike.component';
import { GetPredmeteComponent } from './ucenik/get-predmete/get-predmete.component';
import { OceneComponent } from './ucenik/ocene/ocene.component';
import { GetPredmeteDetetaComponent } from './roditelj/get-predmete-deteta/get-predmete-deteta.component';
import { PregledOcenaComponent } from './roditelj/pregled-ocena/pregled-ocena.component';
import { GetUcenikeComponent } from './nastavnik/get-ucenike/get-ucenike.component';
import { GetOceneUcenikaComponent } from './nastavnik/get-ocene-ucenika/get-ocene-ucenika.component';
import { DodavanjeOcenaComponent } from './nastavnik/dodavanje-ocena/dodavanje-ocena.component';
import { IzmenaOcenaComponent } from './nastavnik/izmena-ocena/izmena-ocena.component';
import { GetUcenikeOdeljenjaComponent } from './nastavnik/dodavanje-ocena/get-ucenike-odeljenja/get-ucenike-odeljenja.component';
import { GetRoditeljeComponent } from './admin/get-roditelje/get-roditelje.component';
import { AddRoditeljComponent } from './admin/add-roditelj/add-roditelj.component';
import { PutRoditeljComponent } from './admin/put-roditelj/put-roditelj.component';
import { AddPredmetComponent } from './admin/add-predmet/add-predmet.component';
import { PutPredmetComponent } from './admin/put-predmet/put-predmet.component';
import { GetPredmeteAdminaComponent } from './admin/get-predmete-admina/get-predmete-admina.component';
import { AddOcenaComponent } from './nastavnik/dodavanje-ocena/add-ocena/add-ocena.component';
import { GetUcenikeAdminaComponent } from './admin/get-ucenike-admina/get-ucenike-admina.component';
import { PutUcenikaAdminaComponent } from './admin/put-ucenika-admina/put-ucenika-admina.component';
import { AddUcenikAdminComponent } from './admin/add-ucenik-admin/add-ucenik-admin.component';
import { GetRazredComponent } from './admin/get-razred/get-razred.component';
import { PutRazredComponent } from './admin/put-razred/put-razred.component';
import { AddRazredComponent } from './admin/add-razred/add-razred.component';
import { GetNazivePredmetaComponent } from './admin/get-nazive-predmeta/get-nazive-predmeta.component';
import { AddNazivPredmetaComponent } from './admin/add-naziv-predmeta/add-naziv-predmeta.component';
import { PutNazivPredmetaComponent } from './admin/put-naziv-predmeta/put-naziv-predmeta.component';
import { AddOdeljenjeComponent } from './admin/add-odeljenje/add-odeljenje.component';
import { PutOdeljenjeComponent } from './admin/put-odeljenje/put-odeljenje.component'
import { GetOdeljenjaComponent } from './admin/get-odeljenja/get-odeljenja.component';
import { GetNasOdeComponent } from './admin/get-nas-ode/get-nas-ode.component';
import { AddNasOdeComponent } from './admin/add-nas-ode/add-nas-ode.component';

const routes: Route[] = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'admin', component: AdminComponent, canActivate : [AdminFilterService], children: [
    { path: 'nastavnici', component: GetNastavnikeComponent, canActivate : [AdminFilterService]},
    { path: 'roditelji', component: GetRoditeljeComponent, canActivate : [AdminFilterService]},
    { path: 'predmeti', component: GetPredmeteAdminaComponent, canActivate : [AdminFilterService]},
    { path: 'ucenici', component: GetUcenikeAdminaComponent, canActivate : [AdminFilterService]},
    { path: 'razred', component: GetRazredComponent, canActivate : [AdminFilterService]},
    { path: 'nazivPredmeta', component: GetNazivePredmetaComponent, canActivate : [AdminFilterService]},
    { path: 'odeljenja', component: GetOdeljenjaComponent, canActivate : [AdminFilterService]},
    { path: 'nasOdePre', component: GetNasOdeComponent, canActivate : [AdminFilterService]}
  ]},
  { path: 'ucenik', component: UcenikComponent, canActivate : [UcenikFilterService], children: [
    { path: 'predmeti', component: GetPredmeteComponent, canActivate : [UcenikFilterService]}
  ]},
  { path: 'nastavnik', component: NastavnikComponent, canActivate : [NastavnikFilterService], children: [
    { path: 'predmeti', component: GetUcenikeComponent, canActivate : [NastavnikFilterService]},
    { path: 'ocena', component: DodavanjeOcenaComponent, canActivate : [NastavnikFilterService]}
  ]},

  { path: 'putOcena/:id', component: IzmenaOcenaComponent, canActivate : [NastavnikFilterService]},
  { path: 'getOceneN/:id', component: GetOceneUcenikaComponent, canActivate : [NastavnikFilterService]},
  { path: 'getUcenikeOdeljenja/:id', component: GetUcenikeOdeljenjaComponent, canActivate : [NastavnikFilterService]},
  { path: 'addOcena/:idOdeljenje/:idUcenik', component: AddOcenaComponent, canActivate : [NastavnikFilterService]},

  { path: 'roditelj', component: RoditeljComponent, canActivate : [RoditeljFilterService], children: [
    { path: 'predmeti/:id', component: GetPredmeteDetetaComponent, canActivate : [RoditeljFilterService]}
  ]},
  { path: 'getOceneR/:id', component: PregledOcenaComponent, canActivate : [RoditeljFilterService]},
  { path: 'addNastavnik', component: AddNastavnikComponent, canActivate : [AdminFilterService]},
  { path: 'addRoditelj', component: AddRoditeljComponent, canActivate : [AdminFilterService]},
  { path: 'addUcenik', component: AddUcenikAdminComponent, canActivate : [AdminFilterService]},
  { path: 'addOdeljenje', component: AddOdeljenjeComponent, canActivate : [AdminFilterService]},
  { path: 'addPredmet', component: AddPredmetComponent, canActivate : [AdminFilterService]},
  { path: 'addRazred', component: AddRazredComponent, canActivate : [AdminFilterService]},
  { path: 'addNazivPredmeta', component: AddNazivPredmetaComponent, canActivate : [AdminFilterService]},
  { path: 'addNasOde', component: AddNasOdeComponent, canActivate : [AdminFilterService]},
  { path: 'putNastavnik/:id', component: PutNastavnikComponent, canActivate : [AdminFilterService]},
  { path: 'putRoditelj/:id', component: PutRoditeljComponent, canActivate : [AdminFilterService]},
  { path: 'putUcenikaAdmina/:id', component: PutUcenikaAdminaComponent, canActivate : [AdminFilterService]},
  { path: 'putOdeljenjeAdmina/:id', component: PutOdeljenjeComponent, canActivate : [AdminFilterService]},
  { path: 'putPredmet/:id', component: PutPredmetComponent, canActivate : [AdminFilterService]},
  { path: 'putRazred/:id', component: PutRazredComponent, canActivate : [AdminFilterService]},
  { path: 'putNazivPredmeta/:id', component: PutNazivPredmetaComponent, canActivate : [AdminFilterService]},
  { path: 'getOcene/:id', component: OceneComponent, canActivate : [UcenikFilterService]}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
