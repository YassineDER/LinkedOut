import {CanActivateFn} from '@angular/router';
import {inject} from '@angular/core';
import {StorageService} from '../services/storage.service';
import {UtilsService} from "../../../services/utils.service";
import {AlertType} from "../../shared/utils/alert-type";
import {HttpClientError} from "../../shared/utils/http-client.error";
import { AuthService } from '../../../services/auth.service';

export const PARGuard: CanActivateFn = async (route, state) => {
    const storage = inject(StorageService);
    const utils = inject(UtilsService);
    const auth = inject(AuthService);

    const PAR = localStorage.getItem('PAR');
    const expires = localStorage.getItem('PAR_EXPIRES');

    if (!PAR || (expires && new Date(expires) < new Date())) {
        const resp = await storage.fetchPAR()
            .catch((e: HttpClientError) => {
                if(e.status === 400)
                    auth.logout();
                else utils.alert(e.error.error, AlertType.ERROR)
            });

        if (typeof resp !== 'object')
            return false;

        localStorage.setItem('PAR', storage.OCI_URL + resp.par_URL);
        localStorage.setItem('PAR_EXPIRES', utils.formatDate(resp.expires));
    }

    return true;
};
