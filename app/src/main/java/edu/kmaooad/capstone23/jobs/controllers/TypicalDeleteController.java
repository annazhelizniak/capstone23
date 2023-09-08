package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class TypicalDeleteController<T1, T2> {
    @Inject
    CommandHandler<T1, T2> commandHandler;

    @DELETE
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JobDeleted.class)) }),
            @APIResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class)) }),
            @APIResponse(responseCode = "500")
    })
    public Response delete(T1 command) {
        try {
            Result<T2> result = commandHandler.handle(command);

            if (!result.isSuccess()) {
                System.out.println(Response.status(400).entity(result.toError()).build());
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).build();
        }
    }
}
