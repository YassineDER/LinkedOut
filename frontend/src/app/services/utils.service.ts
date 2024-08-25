import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from "../modules/shared/utils/alert-type";
import {HttpClient} from "@angular/common/http";
import {NotificationType} from "../modules/shared/utils/notification-type";

@Injectable({
    providedIn: 'root'
})
export class UtilsService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();
    private ip$ : string | null = null;
    private audio = new Audio();

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

    /**
     * Play a sound when a new message is received.
     * @param type The type of notification to play.
     * @default type NotificationType.DEFAULT
     */
    playSound(type: NotificationType) {
        let sound = '';
        switch (type) {
            case NotificationType.DEFAULT:
                sound = 'assets/sound/notification.wav';
                break;
            case NotificationType.POST:
                sound = 'assets/sound/post.wav';
                break;
        }

        this.audio.src = sound;
        this.audio.load();
        this.audio.play();
    }

    /**
     * Format the local date time (Java 8) to a human readable format.
     * @param localDateTime The local date time to format.
     */
    formatDate(localDateTime: number[]) {
        const [year, month, day, hour, minute, second, nanosecond] = localDateTime;
        const date = new Date(year, month - 1, day, hour, minute, second, nanosecond / 1000000);
        return date.toLocaleString();
    }

    /**
     * This api fetches the ip address of the user.
     */
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
