import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {Post} from "../../../models/social/post";

@Injectable()
export class PostsService {
    api = environment.hostUrl + '/posts';

    constructor(private http: HttpClient) {
    }

    getLatestPosts() {
        return this.http.get<Post[]>(this.api + '/latest');
    }

    getPostById(id: string) {
        return this.http.get<Post>(this.api + '/' + id);
    }

    createPost(post: Post): Promise<boolean> {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve(true);
            }, 1000);

            // this.http.post<Post>(this.api, post)
            //     .subscribe({
            //         next: () => resolve(true),
            //         error: (error) => reject(error)
            //     });
        });
    }
}
