import { Uloga } from "./Uloga";
import { Roditelj } from "./Roditelj";
import { Odeljenje } from "./Odeljenje";

export class Ucenik{
    idUcenik:number;
    ime:string;
    prezime:string;
    korisnickoIme:string;
    lozinka:string;
    odeljenje: Odeljenje;
    roditelj: Roditelj;
    uloga: Uloga;
}