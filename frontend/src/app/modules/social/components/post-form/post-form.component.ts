import {Component} from '@angular/core';
import {UtilsService} from "../../../../services/utils.service";
import {AlertType} from "../../../shared/utils/alert-type";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormsService} from "../../../../services/forms.service";
import {PostsService} from "../../services/posts.service";
import {NotificationType} from "../../../shared/utils/notification-type";

@Component({
    selector: 'app-post-form',
    templateUrl: './post-form.component.html',
    styleUrl: './post-form.component.css'
})
export class PostFormComponent {
    postForm: FormGroup;
    imagePreview?: string;
    content = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(500), Validators.pattern(/^[a-zA-Z0-9\s]+$/)]);
    image = new FormControl(null);

    constructor(private utils: UtilsService, private fb: FormBuilder,
                private forms: FormsService, private posts: PostsService) {
        this.postForm = this.fb.group({
            content: this.content,
            image: this.image
        });
    }


    onImagePicked(event: Event): void {
        const target = event.target as HTMLInputElement;
        const image = (target.files as FileList)[0];
        if (target && image) {
            const img = new Image();
            img.src = window.URL.createObjectURL(image);
            img.onload = () => {
                try {
                    const imageSize = (image.size / 1024);
                    const isInvalid = img.width > 500 || img.height > 500 || img.width < 100 || img.height < 100;
                    const isTooBig = imageSize > 3000;
                    if (isInvalid)
                        throw new Error('Les dimensions de l\'image doivent être comprises entre 100x100 et 500x500');
                    if (isTooBig)
                        throw new Error('La taille de l\'image ne doit pas dépasser 3Mo');

                    const reader = new FileReader();
                    reader.onload = () => this.imagePreview = reader.result as string;
                    reader.readAsDataURL(image);

                    this.postForm.patchValue({image});
                } catch (e: any) {
                    this.utils.alert(e.message, AlertType.ERROR);
                }
            };
        }
    }

    async submitPost() {
        // if (this.forms.checkFormValidity(this.postForm)) {
        //     const success = await this.posts.createPost(this.postForm.value)
        //         .then(() => {
        //             this.utils.alert('Post créé avec succès', AlertType.SUCCESS);
        //             this.utils.playSound(NotificationType.POST);
        //             return true;
        //         })
        //         .catch((err) => {
        //             this.utils.alert(err.error.error, AlertType.ERROR);
        //             return false;
        //         });
        //
        //     if (success)
        //         this.postForm.reset();
        // }

        // stimulate waiting for the server response
        return new Promise((resolve) => {
            setTimeout(() => {
                this.postForm.reset();
                resolve(true);
            }, 2000);
        });

    }
}
