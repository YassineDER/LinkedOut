import {Component, EventEmitter, Output} from '@angular/core';
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
    @Output() postCreated: EventEmitter<void> = new EventEmitter<void>();
    postForm: FormGroup;
    imagePreview?: string;
    content = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(500), Validators.pattern(/^[a-zA-ZÀ-ÿ0-9\s.,;:!?'"()\-]{5,500}$/)]);
    image = new FormControl(null);

    constructor(private utils: UtilsService, private fb: FormBuilder,
                private forms: FormsService, private posts: PostsService) {
        this.postForm = this.fb.group({
            description: this.content,
            image_b64: this.image
        });
    }

    /**
     * Handle image picked event and display image preview
     * @param event The event triggered by the image picker
     */
    onImagePicked(event: Event): void {
        const MAX_WIDTH = 2048;
        const MAX_HEIGHT = 2048;
        const MAX_SIZE = 2000;
        const MIN_WIDTH = 600;
        const MIN_HEIGHT = 600;

        const target = event.target as HTMLInputElement;
        const image = (target.files as FileList)[0];
        if (target && image) {
            const img = new Image();
            img.src = window.URL.createObjectURL(image);
            img.onload = () => {
                try {
                    const imageSize = (image.size / 1024);
                    const isInvalid = img.width > MAX_WIDTH || img.height > MAX_HEIGHT || img.width < MIN_WIDTH || img.height < MIN_HEIGHT;
                    const isTooBig = imageSize > MAX_SIZE;
                    if (isInvalid)
                        throw new Error("Les dimensions de l'image doivent être comprises entre " + MIN_WIDTH + "x" + MIN_HEIGHT + " et " + MAX_WIDTH + "x" + MAX_HEIGHT + " pixels");
                    if (isTooBig)
                        throw new Error('La taille de l\'image ne doit pas dépasser ' + MAX_SIZE + ' Ko');

                    const reader = new FileReader();
                    reader.onload = () => {
                        const img_value = reader.result as string;
                        this.imagePreview = img_value;
                        this.postForm.patchValue({ image_b64: img_value.slice(img_value.indexOf(',') + 1) });
                    };
                    reader.readAsDataURL(image);
                } catch (e: any) {
                    this.utils.alert(e.message, AlertType.ERROR);
                }
            };
        }
    }

    /**
     * Submit post form and create a new post
     * @returns {Promise<void>} The promise that resolves when the post is created
     */
    async submitPost(): Promise<void> {
        if (this.forms.checkFormValidity(this.postForm)) {
            const success = await this.posts.createPost(this.postForm.value)
                .then(() => {
                    this.utils.alert('Post créé avec succès', AlertType.SUCCESS);
                    this.utils.playSound(NotificationType.POST);
                    return true;
                })
                .catch((err) => {
                    this.utils.alert(err.error.error, AlertType.ERROR);
                    return false;
                });

            if (success) {
                this.postForm.reset();
                this.imagePreview = undefined;
                this.postCreated.emit();
            }
        }
    }
}
