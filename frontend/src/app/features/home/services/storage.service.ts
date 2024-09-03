import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {StorageResponse} from "../../shared/utils/storage.response";

@Injectable()
export class StorageService {
    api = environment.hostUrl;
    OCI_API = "https://objectstorage.eu-paris-1.oraclecloud.com";

    constructor(private http: HttpClient) {
    }

    get OCI_URL() {
        return this.OCI_API;
    }

    fetchPAR(){
        return new Promise<StorageResponse>((resolve, reject) => {
            this.http.get<StorageResponse>(this.api + '/api/storage/generate-par')
                .subscribe({next: (res) => resolve(res), error: (e) => reject(e)});
        });
    }

    uploadImage(file: File) {

    }
}
