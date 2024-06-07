package org.ichat.backend.model.assemblers;

import lombok.NonNull;
import org.ichat.backend.controller.SocialController;
import org.ichat.backend.model.tables.social.Post;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {

    @Override
    public @NonNull EntityModel<Post> toModel(@NonNull Post entity) {
        return EntityModel.of(entity, linkTo(methodOn(SocialController.class)).withSelfRel());
    }
}
