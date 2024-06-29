import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from "../modules/shared/utils/alert-type";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UtilsService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();
    private ip$ : string | null = null;

    constructor(private http: HttpClient) {}

    /**
     * Display an alert message using the alert component in the app.
     * @param message The message to display
     * @param type The type of alert to display.
     * @default type AlertType.DEFAULT
     */
    alert(message: string, type: AlertType = AlertType.DEFAULT) {
        this.alertSubject.next({message, type});
    }

    fetchIp() {
        return this.http.get<{ip: string}>('https://api.ipify.org?format=json');
    }

    get ip(): string | null{
        return this.ip$;
    }

    set ip(ip: string) {
        this.ip$ = ip;
    }
}
