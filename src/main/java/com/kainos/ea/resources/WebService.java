package com.kainos.ea.resources;

import com.kainos.ea.dao.EmployeeDAO;
import com.kainos.ea.db.Connector;
import com.kainos.ea.model.Employee;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

@Path("/api")
@Api("Engineering Academy Dropwizard API")
public class WebService {
    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmp() {
        Connector con = new Connector();
        Connection c = con.getConnection();
        EmployeeDAO d = new EmployeeDAO();
        Collection col = null;
        try {
            col = d.getAll(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Response
                .status(Response.Status.OK)
                .entity(col.toString())
                .build();
    }

    @GET
    @Path("/employees/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmp(@PathParam("id") int id) {
        Connector con = new Connector();
        Connection c = con.getConnection();
        EmployeeDAO d = new EmployeeDAO();
        String m = null;
        try {
            m = d.getEmployee(id, c).toString();
        } catch (SQLException ex) {
            m = "Employee "+id+" not found";
        } catch (NullPointerException ex) {
            m = "Employee "+id+" not found";
        };

        return Response
                .status(Response.Status.OK)
                .entity(m)
                .build();
    }

    @POST
    @Path("/employees/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMsg(Employee e) {
        Connector con = new Connector();
        Connection c = con.getConnection();
        EmployeeDAO d = new EmployeeDAO();
        try {
            d.insertEmployee(e, c);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(e.toString()+" has been added to the database loloollools")
                .build();
    }

}
