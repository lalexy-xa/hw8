package org.example.services;

import org.example.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String INSERT_CLIENT_STATMENT =
            "INSERT INTO client ( \"NAME\") VALUES (?)";
    private static final String GET_CLIENT_STATMENT =
            "SELECT NAME FROM client WHERE ID=?";
    private static final String UPDATE_CLIENT_STATMENT =
            "UPDATE CLIENT SET NAME = ? WHERE ID=?";
    private static final String DELETE_CLIENT_STATMENT =
            "DELETE FROM client WHERE ID=?";
    private static final String GETALL_CLIENT_STATMENT =
            "SELECT * FROM client";

    private Connection connection;

    public ClientService(Connection connection) throws SQLException {
        this.connection=connection;
    }

    public Long create(String name) throws Exception {
        if(name.length()<2){
            throw new Exception("name is too short");
        }
        PreparedStatement preparedStatement=connection.prepareStatement(INSERT_CLIENT_STATMENT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()){
            return generatedKeys.getLong(1);
        }else {
            return null;
        }
    }

    public String getById(long id) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_STATMENT);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("NAME");
        }else{
            throw new Exception("id does not exists");
        }
    }
    public void setName(long id, String name) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_STATMENT);
        preparedStatement.setLong(2, id);
        preparedStatement.setString(1, name);
        int result = preparedStatement.executeUpdate();
        if (result==0){
            throw new Exception("id does nit exists");
        }

    }
    public void deleteById(long id) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_STATMENT);
        preparedStatement.setLong(1, id);
        int result = preparedStatement.executeUpdate();
        if (result==0){
            throw new Exception("id does nit exists");
        }

    }
    public List<Client> listAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GETALL_CLIENT_STATMENT);
        List<Client> clients = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Client client = new Client();
            client.setId(resultSet.getLong(1));
            client.setName(resultSet.getString(2));
            clients.add(client);
        }

        return clients;
    }
}
