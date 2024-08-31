import {Injectable} from '@angular/core';
import {UtilsService} from "./utils.service";

@Injectable({
    providedIn: 'root'
})
export class AppInitializerService {

    constructor(private utils: UtilsService) {
    }

    /**
     * Fetch the IP address of the client.
     * @returns {() => Promise<any>}
     */
    getUserIP(): Promise<any> {
        return new Promise((resolve, reject) => {
            this.utils.fetchIp().subscribe({
                next: (response) => {
                    this.utils.ip = response.ip;
                    resolve(response);
                },
                error: (error) => reject(error)
            });
        });
    }

}
