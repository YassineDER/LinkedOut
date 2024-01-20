package org.ichat.backend.model.assemblers;

import org.ichat.backend.controller.UserController;
import org.ichat.backend.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getUser_id())).withSelfRel());
        // TODO: Add link to enable 2fa, change password...
    }

    @Override
    public CollectionModel<EntityModel<User>> toCollectionModel(Iterable<? extends User> users) {
        return RepresentationModelAssembler.super.toCollectionModel(users);
    }
}
