import { TipOcene } from "./TipOcene";
import { Ucenik } from "./Ucenik";

export class Ocena {
    idOcena: number;
    visinaOcene: number;
    tipOcene: TipOcene
    datum: Date;
    opisOcene: string;
    nastavnik: string;
    predmet: string;
    ucenik: Ucenik;
}