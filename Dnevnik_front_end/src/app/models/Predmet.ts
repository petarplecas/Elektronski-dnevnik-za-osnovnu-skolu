import { Razred_SkolskaGodina } from "./Razred_SkolskaGodina";
import { NazivPredmeta } from "./NazivPredmeta";

export class Predmet {
    idPredmet:number;
    nedeljniFondCasova:number;
    razred: Razred_SkolskaGodina;
    nazivPredmeta: NazivPredmeta;
}