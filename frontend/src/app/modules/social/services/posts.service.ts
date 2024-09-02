import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {Post} from "../../../models/social/post";
import {Page} from '../../shared/utils/page';

@Injectable()
export class PostsService {
    api = environment.hostUrl + '/api/social';

    constructor(private http: HttpClient) {
    }

    getPosts(page: number, size: number, sort: string) {
        return new Promise<Page<Post>>((resolve, reject) =>
            this.http.get<Page<Post>>(this.api + '/posts', {
                params: {
                    page: page.toString(),
                    size: size.toString(),
                    sort: sort
                }
            }).subscribe({
                next: (posts) => resolve(posts),
                error: (error) => reject(error)
            })
        );
    }

    getPost(id: string): Promise<Post> {
        return new Promise<Post>((resolve, reject) => {
            this.http.get<Post>(this.api + '/posts/' + id)
                .subscribe({
                    next: (post) => resolve(post),
                    error: (error) => reject(error)
                });
        });
    }

    createPost(post: { description: string, image_b64: string }): Promise<Post> {
        return new Promise((resolve, reject) => {
            this.http.post<Post>(this.api + '/posts', post)
                .subscribe({
                    next: (res) => resolve(res),
                    error: (error) => reject(error)
                });
        });
    }

}
