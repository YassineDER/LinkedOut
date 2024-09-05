import {Injectable, TemplateRef} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AlertType} from "../features/shared/utils/alert-type";
import {HttpClient} from "@angular/common/http";
import {NotificationType} from "../features/shared/utils/notification-type";

@Injectable({
    providedIn: 'root'
})
export class UtilsService {
    private alertSubject = new BehaviorSubject<{ message: string, type: AlertType } | null>(null);
    alert$ = this.alertSubject.asObservable();

    private ip$: string | null = null;
    private audio = new Audio();

    constructor(private http: HttpClient) {
    }


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
     * @param humanReadable Whether to format the date time to a human readable format.
     */
    formatDate(localDateTime: number[]) {
        const [year, month, day, hour, minute, second, nanosecond] = localDateTime;
        return new Date(year, month - 1, day, hour, minute, second, nanosecond / 1000000);
    }

    /**
     * Calculate the total duration between two dates.
     * @param start the local date time to start from.
     * @param end the local date time to end at.
     */
    totalDuration(start: number[], end: number[]): string {
        const formatedStartDate = this.formatDate(start);
        const formatedEndDate = this.formatDate(end);
        const diff = formatedEndDate.getTime() - formatedStartDate.getTime();
        const formatedDate = new Date(diff);
        return `${formatedDate.getFullYear() - 1970} ans, ${formatedDate.getMonth()} mois`;
    }

    /**
     * Get the elapsed time since a given date.
     * @param localDateTime The local date time to calculate the elapsed time from.
     */
    getElapsedTime(localDateTime: number[]): string {
        const formatedDate = this.formatDate(localDateTime);
        const now = new Date();
        const diff = now.getTime() - formatedDate.getTime();

        const seconds = Math.floor(diff / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const days = Math.floor(hours / 24);

        if (days > 0)
            return `il y a ${days} jour${days > 1 ? 's' : ''}`;
        else if (hours > 0)
            return `il y a ${hours} heure${hours > 1 ? 's' : ''}`;
        else if (minutes > 0)
            return `il y a ${minutes} minute${minutes > 1 ? 's' : ''}`;
        else return `il y a ${seconds} seconde${seconds > 1 ? 's' : ''}`;
    }

    /**
     * This api fetches the ip address of the user.
     */
    fetchIp() {
        return this.http.get<{ ip: string }>('https://api.ipify.org?format=json');
    }

    get ip(): string | null {
        return this.ip$;
    }

    set ip(ip: string) {
        this.ip$ = ip;
    }
}
