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
    formatDate(localDateTime: number[], humanReadable = false): string {
        const [year, month, day, hour, minute, second, nanosecond] = localDateTime;
        const date = new Date(year, month - 1, day, hour, minute, second, nanosecond / 1000000);

        if (!humanReadable) {
            return date.toLocaleString();
        }

        const now = new Date();
        const diff = now.getTime() - date.getTime();

        const seconds = Math.floor(diff / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const days = Math.floor(hours / 24);

        if (days > 0) {
            return `${days} day${days > 1 ? 's' : ''} ago`;
        } else if (hours > 0) {
            return `${hours} hour${hours > 1 ? 's' : ''} ago`;
        } else if (minutes > 0) {
            return `${minutes} minute${minutes > 1 ? 's' : ''} ago`;
        } else {
            return `${seconds} second${seconds > 1 ? 's' : ''} ago`;
        }
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
