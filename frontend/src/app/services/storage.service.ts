import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UploadsService {
    api = localStorage.getItem("PAR")

    constructor(private http: HttpClient) {
    }

    uploadImage(image: File) {
        const formData = new FormData();
        formData.append('image', image);

    }
}
