import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class StorageService {
	private configPath = "~/oci/config";

	constructor() {
	}

}
